package com.ducluanxutrieu.tikiapp.data;

import com.ducluanxutrieu.tikiapp.data.models.BannerResponseModel;
import com.ducluanxutrieu.tikiapp.data.models.FlashDealResponseModel;
import com.ducluanxutrieu.tikiapp.data.models.QuickLinkResponseModel;

import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitService {
    @GET("v2/home/banners/v2")
    Call<BannerResponseModel> getBannerList();

    @GET("shopping/v2/widgets/quick_link")
    Call<QuickLinkResponseModel> getQuickLink();

    @GET("v2/widget/deals/hot")
    Call<FlashDealResponseModel> getFlashDeal();
}