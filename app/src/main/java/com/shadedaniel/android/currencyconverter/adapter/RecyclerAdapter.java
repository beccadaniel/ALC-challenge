package com.shadedaniel.android.currencyconverter.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shadedaniel.android.currencyconverter.R;
import com.shadedaniel.android.currencyconverter.activities.ConversionActivity;
import com.shadedaniel.android.currencyconverter.data.ExchangeRate;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by becca on 10/31/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<ExchangeRate> exchangeRates;


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView currencyName, currencyRep, btcConverted, ethConverted;

        MyViewHolder(View view) {
            super(view);
            currencyName = view.findViewById(R.id.currency_name_view);
            currencyRep = view.findViewById(R.id.currency_rep_view);
            btcConverted = view.findViewById(R.id.btc_converted_view);
            ethConverted = view.findViewById(R.id.eth_converted_view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), ConversionActivity.class);
                    Bundle b = new Bundle();
                    b.putDouble("btcConversion", Double.valueOf(btcConverted.getText().toString()));
                    b.putDouble("ethConversion", Double.valueOf(ethConverted.getText().toString()));
                    b.putString("currencyRep", currencyRep.getText().toString());
                    intent.putExtras(b);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public RecyclerAdapter(ArrayList<ExchangeRate> data) {
        this.exchangeRates = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.currencyName.setText(exchangeRates.get(position).currencyName);
        holder.currencyRep.setText(exchangeRates.get(position).currencyRep);
        holder.btcConverted.setText(String.format(Locale.ENGLISH, "%.2f", exchangeRates.get(position).btc));
        holder.ethConverted.setText(String.format(Locale.ENGLISH, "%.2f", exchangeRates.get(position).eTH));
    }

    @Override
    public int getItemCount() {
        return exchangeRates.size();
    }
}
