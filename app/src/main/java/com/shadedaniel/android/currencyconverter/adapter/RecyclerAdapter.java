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

/**
 * Created by becca on 10/31/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<ExchangeRate> exchangeRates;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView currencyName, currencyRep, btcConverted, ethConverted;
        public MyViewHolder(View view) {
            super(view);
            currencyName = (TextView) view.findViewById(R.id.currency_name_view);
            currencyRep = (TextView) view.findViewById(R.id.currency_rep_view);
            btcConverted = (TextView) view.findViewById(R.id.btc_converted_view);
            ethConverted = (TextView) view.findViewById(R.id.eth_converted_view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), ConversionActivity.class);
                    Bundle b = new Bundle();
                    b.putDouble("btcConversion", Double.valueOf(btcConverted.getText().toString()));
                    b.putDouble("ethConversion", Double.valueOf(ethConverted.getText().toString()));
                    intent.putExtras(b);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public RecyclerAdapter(ArrayList<ExchangeRate> data ) {
        this.exchangeRates = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.currencyName.setText(exchangeRates.get(position).currencyName);
        holder.currencyRep.setText(exchangeRates.get(position).currencyRep);
        holder.btcConverted.setText(exchangeRates.get(position).btc + "");
        holder.ethConverted.setText(exchangeRates.get(position).eTH + "");
    }

    @Override
    public int getItemCount() {
        return exchangeRates.size();
    }
}
