package com.ducluanxutrieu.tikiapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerResponseModel {

    @SerializedName("data")
    @Expose
    private ArrayList<BannerModel> data = null;

    public ArrayList<BannerModel> getData() {
        return data;
    }

    public void setData(ArrayList<BannerModel> data) {
        this.data = data;
    }
}