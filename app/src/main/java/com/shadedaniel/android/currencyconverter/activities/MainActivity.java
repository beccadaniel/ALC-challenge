package com.shadedaniel.android.currencyconverter.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.JsonObject;
import com.shadedaniel.android.currencyconverter.R;
import com.shadedaniel.android.currencyconverter.adapter.RecyclerAdapter;
import com.shadedaniel.android.currencyconverter.data.ApiInterface;
import com.shadedaniel.android.currencyconverter.data.Dataset;
import com.shadedaniel.android.currencyconverter.data.ExchangeRate;
import com.shadedaniel.android.currencyconverter.data.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shadedaniel.android.currencyconverter.data.Dataset.currencyName;
import static com.shadedaniel.android.currencyconverter.data.Dataset.currencyRep;


public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
    ArrayList<ExchangeRate> initialExchangeCards = new ArrayList<>();
    FloatingActionButton fab;
    RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.card_list);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkingConnection();

        fab = (FloatingActionButton) findViewById(R.id.add_cards);
        if (isConnectionAvailable(this)) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });
        }
    }

    public void checkingConnection() {
        if (isConnectionAvailable(this))
            getResponse();
        else {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            checkingConnection();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    }).create();
            dialog.show();

        }
    }

    public void showDialog() {
        String[] list = Dataset.list;
        final ArrayList selectedItems = new ArrayList();

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("select any currency")
                .setMultiChoiceItems(list, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selectedItems.add(indexSelected);
                            initialExchangeCards.add(exchangeRates.get(indexSelected));

                        } else if (selectedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            selectedItems.remove(Integer.valueOf(indexSelected));
                            initialExchangeCards.remove(exchangeRates.get(indexSelected));
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        recyclerAdapter.notifyItemInserted(recyclerAdapter.getItemCount());
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).create();
        dialog.show();

    }


    public void getResponse() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....\nPlease wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        apiInterface.getExchangeRate().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                JsonObject jsonObject = response.body();
                for (int i = 0; i < 20; i++) {

                    if (i < 2)
                        initialExchangeCards.add(new ExchangeRate(
                                currencyName[i],
                                currencyRep[i],
                                jsonObject.get("BTC").getAsJsonObject().get(currencyRep[i]).getAsDouble(),
                                jsonObject.get("ETH").getAsJsonObject().get(currencyRep[i]).getAsDouble()
                        ));
                    else {

                        exchangeRates.add(new ExchangeRate(
                                currencyName[i],
                                currencyRep[i],
                                jsonObject.get("BTC").getAsJsonObject().get(currencyRep[i]).getAsDouble(),
                                jsonObject.get("ETH").getAsJsonObject().get(currencyRep[i]).getAsDouble()
                        ));
                    }

                    recyclerAdapter = new RecyclerAdapter(initialExchangeCards);
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
    }

    // checks if device is connected to the internet
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}