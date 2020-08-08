package com.ducluanxutrieu.tikiapp.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlashDealResponseModel {

    @SerializedName("data")
    @Expose
    private ArrayList<FlashDealModel> data = null;
    @SerializedName("tabs")
    @Expose
    private ArrayList<ProductTab> productTabs = null;
    @SerializedName("version")
    @Expose
    private String version;

    public ArrayList<FlashDealModel> getData() {
        return data;
    }

    public void setData(ArrayList<FlashDealModel> data) {
        this.data = data;
    }

    public ArrayList<ProductTab> getProductTabs() {
        return productTabs;
    }

    public void setProductTabs(ArrayList<ProductTab> productTabs) {
        this.productTabs = productTabs;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}