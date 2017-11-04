package com.shadedaniel.android.currencyconverter.data;

import java.util.ArrayList;

/**
 * Created by becca on 11/1/17.
 */

public class Dataset {

    public static String[] currencyName = {"Naira", "US Dollar", "Euro", "Swiss Franc",
            "Kuwaiti Dinar", "Rand", "Pound Sterling", "Nuevo Sol", "Brazillian Real",
            "Indian Rupee", "Yen", "Jamaican Dollar", "Canadian Dollar", "Ghana Cedi",
            "Omani Rial", "Singapore Dollar", "Hryvnia", "UAE Dirham", "Romanian Leu", "Egypt"};

    public static String[] currencyRep = {"NGN", "USD", "EUR", "CHF", "KWD", "ZAR",
            "GBP", "PEN", "BRL", "INR", "JPY", "JMD", "CAD", "GHS", "OMR", "SGD",
            "UAH", "AED", "RON", "EGP"};

    public static String[] list = {"EUR", "CHF", "KWD", "ZAR",
            "GBP", "PEN", "BRL", "INR", "JPY", "JMD", "CAD", "GHS", "OMR", "SGD",
            "UAH", "AED", "RON", "EGP"};

    ArrayList items = new ArrayList();

    public ArrayList getItems() {
        items.add("EUR");
        items.add("CHF");
        items.add("KWD");
        items.add("ZAR");
        items.add("GBP");
        items.add("PEN");
        items.add("BRL");
        items.add("INR");
        items.add("JPY");
        items.add("JMD");
        items.add("CAD");
        items.add("GHS");
        items.add("OMR");
        items.add("SGD");
        items.add("UAH");
        items.add("AED");
        items.add("RON");
        items.add("EGP");

        return items;
    }
}
