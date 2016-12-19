package com.devcli.finance_eq.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by prashantkoshta on 12/17/16.
 */

public class Calculators {
    @SerializedName("data")
    @Expose
    private List<Calculator> listOfCals;


    public void setListOfCals(List<Calculator> listOfCals) {
        this.listOfCals = listOfCals;
    }

    public List<Calculator> getListOfCals() {
        return listOfCals;
    }


    public Calculators(List<Calculator> listOfCals) {
        this.listOfCals = listOfCals;
    }
}
