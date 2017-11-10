package com.shadedaniel.android.currencyconverter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shadedaniel.android.currencyconverter.R;

public class ConversionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_conversion);

        final Bundle b = getIntent().getExtras();
        final String currencyRep = b.getString("currencyRep");
        final double btcConversion = b.getDouble("btcConversion");
        final double ethConversion = b.getDouble("ethConversion");

        final TextView tv1 = (TextView) findViewById(R.id.other_crypto);
        final TextView tv2 = (TextView) findViewById(R.id.btc_to_other);
        final TextView tv3 = (TextView) findViewById(R.id.eth_to_other);

        tv1.setText(currencyRep + " - BTC/ETH");
        tv2.setText("BTC - " + currencyRep + "/ETH");
        tv3.setText("ETH - " + currencyRep + "/BTC");

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCard(currencyRep, "To BTC:", "To ETH:");
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCard("BTC", "To " + currencyRep + ":", "ETH");
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCard("ETH", "To " + currencyRep + ":", "To BTC:");
            }
        });

        Button calculate = (Button) findViewById(R.id.calculate_amount);
        calculate.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                TextView ctv = (TextView) findViewById(R.id.currency_text_view);
                TextView btv = (TextView) findViewById(R.id.currency1_text_view);
                final TextView btcView = (TextView) findViewById(R.id.btc_text_view);
                final TextView ethView = (TextView) findViewById(R.id.eth_text_view);
                final EditText amountView = (EditText) findViewById(R.id.amount_text_view);
                if (!(amountView.getText().toString().isEmpty())) {
                    if (btv.getText() == "BTC") {
                        btcView.setText(String.valueOf(Double.valueOf(amountView.getText().toString()) / btcConversion));
                        ethView.setText(String.valueOf(Double.valueOf(amountView.getText().toString()) / ethConversion));
                        return;
                    }
                    if (ctv.getText() == "BTC") {
                        btcView.setText(String.valueOf(Double.valueOf(amountView.getText().toString()) * btcConversion));
                        ethView.setText(String.valueOf(Double.valueOf(amountView.getText().toString()) * (ethConversion / btcConversion)));
                        return;
                    }
                    btcView.setText(String.valueOf(Double.valueOf(amountView.getText().toString()) * ethConversion));
                    ethView.setText(String.valueOf(Double.valueOf(amountView.getText().toString()) * (btcConversion / ethConversion)));
                }
            }
        });
    }

    public void showCard(String currency, String btc, String eth) {
        TextView ctv = (TextView) findViewById(R.id.currency_text_view);
        TextView btv = (TextView) findViewById(R.id.currency1_text_view);
        TextView etv = (TextView) findViewById(R.id.currency2_text_view);

        TextView btcView = (TextView) findViewById(R.id.btc_text_view);
        TextView ethView = (TextView) findViewById(R.id.eth_text_view);
        EditText amountView = (EditText) findViewById(R.id.amount_text_view);

        btcView.setText("");
        ethView.setText("");
        amountView.setText("");

        ctv.setText(currency);
        btv.setText(btc);
        etv.setText(eth);
        View v = findViewById(R.id.card);
        v.setVisibility(View.VISIBLE);
    }
}