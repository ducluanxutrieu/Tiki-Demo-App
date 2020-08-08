package com.ducluanxutrieu.tikiapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuickLinkResponseModel {

    @SerializedName("data")
    @Expose
    private ArrayList<ArrayList<QuickLinkModel>> data = null;

    public ArrayList<ArrayList<QuickLinkModel>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<QuickLinkModel>> data) {
        this.data = data;
    }
}