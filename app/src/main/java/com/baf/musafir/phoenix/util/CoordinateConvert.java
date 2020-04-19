package com.baf.musafir.phoenix.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class CoordinateConvert {

    public static String convertDmsToLatLong(String latiTude,String longiTude) {
        double doub_lat=Double.parseDouble(latiTude);
        double doub_long=Double.parseDouble(longiTude);
        String dmd_n=covertingDegree(doub_lat)+" "+covertingMinute(doub_lat)+" "+covertingSec(doub_lat);
        System.out.println("if  " + latiTude+"     "+longiTude);
        System.out.println("if  " + covertingDegree(doub_lat));
        System.out.println("if  " + covertingMinute(doub_lat));
        System.out.println("if  " + covertingSec(doub_lat));
        String dmd_e=covertingDegree(doub_long)+" "+covertingMinute(doub_long)+" "+covertingSec(doub_long);

        return "N "+dmd_n+"   E "+dmd_e;

    }




    public static String covertingDegree(double decimal_degree) {

        int d_part_int;
        d_part_int = (int) decimal_degree;
        return ""+d_part_int;

    }

    public static String covertingMinute(double decimal_degree) {

        int d_part_int;
        int m_part_int;
        d_part_int = (int) decimal_degree;
        m_part_int = (int) ((decimal_degree - d_part_int) * 60);
        return ""+intToString(m_part_int,2);

    }

    public static String covertingSec(double decimal_degree) {

        double s_part;
        int s_part_int;
        int d_part_int;
        int m_part_int;
        d_part_int = (int) decimal_degree;
        m_part_int = (int) ((decimal_degree - d_part_int) * 60);
        s_part = (decimal_degree - d_part_int - roundTwoDecimals(m_part_int) / 60) * 3600;
        s_part=roundTwoDecimals(s_part);
        s_part_int=(int)s_part;
        return ""+intToString(s_part_int,2);

    }

    static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.parseDouble(twoDForm.format(d));
    }
    public static double roundDecimals(double d) {
        DecimalFormat df = new DecimalFormat("#.######");
        df.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(df.format(d));
    }


    public static String intToString(int num, int digits) {
        assert digits > 0 : "Invalid number of digits";

        // create variable length array of zeros
        char[] zeros = new char[digits];
        Arrays.fill(zeros, '0');
        // format number as String
        DecimalFormat df = new DecimalFormat(String.valueOf(zeros));

        return df.format(num);
    }

    public static final String dmdtoLatlong(String dmsInput) {

        String dmsString = dmsInput.replaceAll("\\s+","");
        double degree,minute,sec,totalsec,frac_latlong;
        double latiTude = 0;
            if(dmsString.length()>=6){
                degree=Double.parseDouble(dmsString.substring(0,2));
                minute=Double.parseDouble(dmsString.substring(2,4));
                sec=Double.parseDouble(dmsString.substring(4,dmsString.length()));
                 totalsec = minute * 60 + sec;
                 frac_latlong=totalsec/3600;
                latiTude=degree+frac_latlong;
            }


        return Double.toString(roundDecimals(latiTude));
    }
}
