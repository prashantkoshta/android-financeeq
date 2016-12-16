package com.devcli.finance_eq.utils;

import java.text.NumberFormat;

/**
 * Created by prashantkoshta on 12/15/16.
 */

public class Calculation {

    public static String formatNumberToString(double d){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(d);
    }

    public static double parseFromString(String s){
        double n =0;
        try{
            if(s != null && !s.isEmpty()){
                n = Double.parseDouble(s);
            }
        }catch(ArithmeticException e){

        }
        return n;

    }


    public static double simpleInterest(double p, double r, double t, String R){

        switch (R.toLowerCase()){
            case "quarters":
                t = t * (1/4); // 4 quarters in 1 year.
                break;
            case "months":
                t = t * (1/12); // 12 months in 1 year.
                break;
            case "weeks":
                t = t * (1/ 52); // 52 weeks in 1 year.
                break;
            case "days (365/year)":
                t = t * (1 /365);
                break;
            case "days (360/year)":
                t = t * (1/360);
                break;
            case "year":
                t= t;
                break;
            default:
                t = t;
                break;
        }

        r = r/100;

        return (p * r * t);
    }


    public static double compoundInterest(double p, double r, double t, String compundperiod){
        //A = P(1 + r/n)^nt
        double n = 0;
        switch (compundperiod){
            case "Continuously":
                n = 1;
                break;
            case "Daily (365/Year)":
                t  = 365;
                break;
            case "Daily (360/Year)":
                n = 360;
                break;
            case "Weekly (52/Year)":
                n = 52;
                break;
            case "Bi-Weekly (26/Year)":
               n = 26;
                break;
            case "Semi-Monthly (24/Year)":
                n = 24;
                break;
            case "Monthly (12/Year)":
                n = 12;
                break;
            case "Bi-Monthly (6/Year)":
                n = 6;
                break;
            case "Quarterly (4/Year)":
                n = 4;
                break;
            case "Semi-Annually (2/Year)":
                n = 2;
                break;
            case "Annually (1/Year)":
                n = 1;
                break;
            default:
                n = 1;
                break;

        }

        r = r/100;

        double a = p * Math.pow((1 + r / n), n * t);
        return  a;
    }

    public static double homeLoan(double pv, double r, double n, String s){
        double pmt =0;
        r = r /100;
        r = r / 12;
        switch (s.toLowerCase()){
            case "months":
                break;
            case "year":
                n  = n * 12;
                break;
            default:
                n = 12;
                break;

        }
        pmt = (pv * r * Math.pow((1 + r), n)) / (Math.pow((1 + r), n) - 1);
        return  pmt;
    }
}
