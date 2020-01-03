package com.baf.musafir.phoenix.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtility {

    public static final Double getlatitude(String string) {
        String[] arrSplit = null;
        try {
             arrSplit = string.split(",");
        } catch (Exception e) {

        }
        Double lat=Double.parseDouble(arrSplit[0]);
        return lat;
    }
    public static final Double getlongitude(String string) {
        String[] arrSplit = null;
        try {
            arrSplit = string.split(",");
        } catch (Exception e) {

        }
        Double longitude=Double.parseDouble(arrSplit[1]);
        return longitude;
    }

    public static final String getYear(String string) {
        String[] arrSplit = null;
        try {
            arrSplit = string.split(" ");
        } catch (Exception e) {

        }
        return arrSplit[2];
    }
    public static final String getMonth(String string) {
        String[] arrSplit = null;
        try {
            arrSplit = string.split(" ");
        } catch (Exception e) {

        }
        return arrSplit[1];
    }
    public static final String getDay(String string) {
        String[] arrSplit = null;
        try {
            arrSplit = string.split(" ");
        } catch (Exception e) {

        }
        return arrSplit[0];
    }

}
