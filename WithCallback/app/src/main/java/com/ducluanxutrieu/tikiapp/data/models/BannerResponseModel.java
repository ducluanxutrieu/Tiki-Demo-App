package com.ducluanxutrieu.tikiapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerResponseModel {

    @SerializedName("data")
    @Expose
    private ArrayList<BannerModel> bannerList = null;

    public ArrayList<BannerModel> getBannerList() {
        return bannerList;
    }

    public void setBannerList(ArrayList<BannerModel> bannerList) {
        this.bannerList = bannerList;
    }
}