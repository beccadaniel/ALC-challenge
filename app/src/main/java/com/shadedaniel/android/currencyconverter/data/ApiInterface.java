package com.shadedaniel.android.currencyconverter.data;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by becca on 11/1/17.
 */

public interface ApiInterface {
    static final String BASE_URL = "https://min-api.cryptocompare.com/";
   // https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=BTC,USD,EUR
    @GET("data/pricemulti?fsyms=ETH,BTC&tsyms=EUR,USD,NGN,CHF,KWD,ZAR,EGP,GBP,PEN,BRL,INR,JPY,JMD,CAD,GHS,OMR,SGD,UAH,AED,RON")
    Call<JsonObject> getExchangeRate();
}
