package com.shadedaniel.android.currencyconverter.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by becca on 11/1/17.
 */

public class ExchangeRate implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ExchangeRate createFromParcel(Parcel in) {
            return new ExchangeRate(in);
        }

        public ExchangeRate[] newArray(int size) {
            return new ExchangeRate[size];
        }
    };

    public ExchangeRate(Parcel in) {
        this.currencyName = in.readString();
        this.currencyRep = in.readString();
        this.btc = in.readDouble();
        this.eTH = in.readDouble();
    }

    public String currencyName;
    public String currencyRep;
    public double btc;
    public double eTH;

    public ExchangeRate(String currencyName, String currencyRep, double btc, double eTH) {
        this.currencyName = currencyName;
        this.currencyRep = currencyRep;
        this.btc = btc;
        this.eTH = eTH;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "currencyName='" + currencyName + '\'' +
                ", currencyRep='" + currencyRep + '\'' +
                ", btc=" + btc +
                ", eTH=" + eTH +
                '}';
    }
}
