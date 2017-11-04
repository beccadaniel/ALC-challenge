package com.shadedaniel.android.currencyconverter.data;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by becca on 11/1/17.
 */

public interface ApiInterface {
    String BASE_URL = "https://min-api.cryptocompare.com/";

    @GET("data/pricemulti?fsyms=ETH,BTC&tsyms=NGN,USD,EUR,CHF,KWD,ZAR,EGP,GBP,PEN,BRL,INR,JPY,JMD,CAD,GHS,OMR,SGD,UAH,AED,RON")
    Call<JsonObject> getExchangeRate();
}
