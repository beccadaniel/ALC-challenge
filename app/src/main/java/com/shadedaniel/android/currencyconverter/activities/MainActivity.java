package com.shadedaniel.android.currencyconverter.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.shadedaniel.android.currencyconverter.R;
import com.shadedaniel.android.currencyconverter.adapter.RecyclerAdapter;
import com.shadedaniel.android.currencyconverter.data.ApiInterface;
import com.shadedaniel.android.currencyconverter.data.ExchangeRate;
import com.shadedaniel.android.currencyconverter.data.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();

    public static String[] currencyName = {"Euro", "US Dollar", "Naira", "Swiss Franc",
            "Kuwaiti Dinar", "Rand", "Pound Sterling", "Nuevo Sol", "Brazillian Real",
            "Indian Rupee", "Yen", "Jamaican Dollar", "Canadian Dollar", "Ghana Cedi",
            "Omani Rial", "Singapore Dollar", "Hryvnia", "UAE Dirham", "Romanian Leu", "Egypt"};

    public static String[] currencyRep = {"EUR", "USD", "NGN", "CHF", "KWD", "ZAR",
            "GBP", "PEN", "BRL", "INR", "JPY", "JMD", "CAD", "GHS", "OMR", "SGD",
            "UAH", "AED", "RON", "EGP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.card_list);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

      getValues();
    }

    public void getValues(){

        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
       apiInterface.getExchangeRate().enqueue(new Callback<JsonObject>() {
           @Override
           public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
               JsonObject jsonObject = response.body();
               for (int i = 0; i < 20 ; i++){

                   exchangeRates.add(new ExchangeRate(
                           currencyName[i],
                           currencyRep[i],
                           jsonObject.get("BTC").getAsJsonObject().get(currencyRep[i]).getAsDouble(),
                           jsonObject.get("ETH").getAsJsonObject().get(currencyRep[i]).getAsDouble()
                   ));
               }

               RecyclerAdapter recyclerAdapter = new RecyclerAdapter(exchangeRates);
               recyclerView.setAdapter(recyclerAdapter);

           }

           @Override
           public void onFailure(Call<JsonObject> call, Throwable t) {

           }
       });
    }
}