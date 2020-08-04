package com.example.tikiapp.data.home

import com.example.tikiapp.MainActivity
import com.example.tikiapp.data.Result
import com.example.tikiapp.data.models.BannerResponseModel
import com.example.tikiapp.data.models.FlashDealResponseModel
import com.example.tikiapp.data.models.QuickLinkResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeRepository {
    fun getListBanner(onComplete: (Result<BannerResponseModel>) -> Unit){
        MainActivity.serviceRetrofit.getBannerList()
            .enqueue(object : Callback<BannerResponseModel> {
                override fun onFailure(call: Call<BannerResponseModel>, t: Throwable) {
                    onComplete(Result.Error(IOException("Error", t)))
                }

                override fun onResponse(
                    call: Call<BannerResponseModel>,
                    response: Response<BannerResponseModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val repo : BannerResponseModel = response.body()!!
                        onComplete(Result.Success(repo))
                    }else {
                        onComplete(Result.Error(IOException("Error" + response.errorBody().toString())))
                    }
                }
            }
            )
    }

    fun getQuickLink(onComplete: (Result<QuickLinkResponseModel>) -> Unit) {
        MainActivity.serviceRetrofit.getQuickLink()
            .enqueue(object : Callback<QuickLinkResponseModel> {
                override fun onFailure(call: Call<QuickLinkResponseModel>, t: Throwable) {
                    onComplete(Result.Error(IOException("Error", t)))
                }

                override fun onResponse(
                    call: Call<QuickLinkResponseModel>,
                    response: Response<QuickLinkResponseModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        onComplete(Result.Success(response.body()!!))
                    }else {
                        onComplete(Result.Error(IOException("Error" + response.errorBody().toString())))
                    }
                }
            }
            )
    }

    fun getFlashDeal(onComplete: (Result<FlashDealResponseModel>) -> Unit) {
        MainActivity.serviceRetrofit.getFlashDeal()
            .enqueue(object : Callback<FlashDealResponseModel> {
                override fun onFailure(call: Call<FlashDealResponseModel>, t: Throwable) {
                    onComplete(Result.Error(IOException("Error", t)))
                }

                override fun onResponse(
                    call: Call<FlashDealResponseModel>,
                    response: Response<FlashDealResponseModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        onComplete(Result.Success(response.body()!!))
                    }else {
                        onComplete(Result.Error(IOException("Error" + response.errorBody().toString())))
                    }
                }
            }
            )
    }
}