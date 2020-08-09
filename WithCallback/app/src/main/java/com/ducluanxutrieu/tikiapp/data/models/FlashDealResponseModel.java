package com.ducluanxutrieu.tikiapp.data.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlashDealResponseModel {

    @SerializedName("data")
    @Expose
    private ArrayList<FlashDealModel> dealModelList = null;

    public ArrayList<FlashDealModel> getDealModelList() {
        return dealModelList;
    }

    public void setDealModelList(ArrayList<FlashDealModel> dealModelList) {
        this.dealModelList = dealModelList;
    }

}