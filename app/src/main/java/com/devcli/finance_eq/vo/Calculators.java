package com.devcli.finance_eq.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashantkoshta on 12/17/16.
 */

public class Calculators implements Parcelable {

    @SerializedName("listOfCals")
    private List<Calculator> listOfCals;

    public List<Calculator> getListOfCals() {
        return listOfCals;
    }

    public void setListOfCals(List<Calculator> listOfCals) {
        this.listOfCals = listOfCals;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.listOfCals);

    }

    public Calculators(Parcel in){
        this.listOfCals = new ArrayList<Calculator>();
        this.listOfCals = in.readArrayList(Calculator.class.getClassLoader());
    }

    public static final  Parcelable.Creator<Calculators> CREATOR = new Parcelable.Creator<Calculators>(){

        public Calculators createFromParcel(Parcel in){
            return new Calculators(in);
        }

        public Calculators[] newArray(int size){
            return new Calculators[size];
        }

    };





}
