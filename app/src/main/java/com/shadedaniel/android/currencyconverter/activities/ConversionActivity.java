package com.shadedaniel.android.currencyconverter.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Button calculate = (Button) findViewById(R.id.calculate_amount);
        calculate.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Bundle b = getIntent().getExtras();
                TextView btcView = (TextView) findViewById(R.id.btc_text_view);
                TextView ethView = (TextView) findViewById(R.id.eth_text_view);
                EditText amountView = (EditText) findViewById(R.id.amount_text_view);

                btcView.setText(String.valueOf(Double.valueOf(amountView.getText().toString())/b.getDouble("btcConversion")));
                ethView.setText(String.valueOf(Double.valueOf(amountView.getText().toString())/b.getDouble("ethConversion")));
            }
        });
    }
}
