package com.devcli.finance_eq.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prashantkoshta on 12/8/16.
 */

public class Calculator implements Parcelable{
    @SerializedName("type")
    private String type;
    @SerializedName("action")
    private String action;
    @SerializedName("pagename")
    private String pagename;
    @SerializedName("pageurl")
    private String pageurl;
    @SerializedName("descriptions")
    private String descriptions;
    @SerializedName("frequency")
    private ArrayList<String> frequency;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public ArrayList<String> getFrequency() {
        return frequency;
    }

    public void setFrequency(ArrayList<String> frequency) {
        this.frequency = frequency;
    }



    @Override
    public String toString() {
        return this.type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.action);
        dest.writeString(this.pagename);
        dest.writeString(this.descriptions);


        dest.writeList(this.frequency);

    }

    public Calculator(Parcel in){
        this.type = in.readString();
        this.action = in.readString();
        this.pagename = in.readString();
        this.descriptions = in.readString();

        this.frequency = new ArrayList<String>();
        this.frequency = in.readArrayList(String.class.getClassLoader());
    }

    public static final  Parcelable.Creator<Calculator> CREATOR = new Parcelable.Creator<Calculator>(){

        public Calculator createFromParcel(Parcel in){
            return new Calculator(in);
        }

        public Calculator[] newArray(int size){
            return new Calculator[size];
        }

    };
}

