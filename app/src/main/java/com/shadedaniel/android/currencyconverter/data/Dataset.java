package com.shadedaniel.android.currencyconverter.data;

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

    public static boolean[] getChecked() {
        boolean[] checked = new boolean[20];
        checked[0] = true;
        return checked;
    }
}
