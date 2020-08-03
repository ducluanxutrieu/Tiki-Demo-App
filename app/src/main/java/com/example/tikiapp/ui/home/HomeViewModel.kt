package com.example.tikiapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tikiapp.MainActivity
import com.example.tikiapp.models.BannerResponseModel
import com.example.tikiapp.models.QuickLinkModel
import com.example.tikiapp.models.QuickLinkResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val bannerAdapter = BannerAdapter()
    val quickLinkAdapter = QuickLinkAdapter()

    init {
        getListBanner()
        getQuickLink()
    }

    private fun getListBanner(){
        MainActivity.serviceRetrofit.getBannerList()
            .enqueue(object : Callback<BannerResponseModel> {
                override fun onFailure(call: Call<BannerResponseModel>, t: Throwable) {
                    Log.e("HomeViewModel", t.message ?: "", t)
                }

                override fun onResponse(
                    call: Call<BannerResponseModel>,
                    response: Response<BannerResponseModel>
                ) {
                    if (response.isSuccessful){
                        bannerAdapter.setData(response.body()?.bannerList ?: ArrayList())
                    }
                }

            }
        )
    }

    private fun getQuickLink(){
        MainActivity.serviceRetrofit.getQuickLink()
            .enqueue(object : Callback<QuickLinkResponseModel> {
                override fun onFailure(call: Call<QuickLinkResponseModel>, t: Throwable) {
                    Log.e("HomeViewModel", t.message ?: "", t)
                }

                override fun onResponse(
                    call: Call<QuickLinkResponseModel>,
                    response: Response<QuickLinkResponseModel>
                ) {
                    if (response.isSuccessful){
                        quickLinkAdapter.setData(mixQuickLinkData(response.body()?.quickLink ?: ArrayList()))
                    }
                }

            }
            )
    }

    private fun mixQuickLinkData(listResponse: ArrayList<ArrayList<QuickLinkModel>>): ArrayList<QuickLinkModel> {
        val sizeMax = if (listResponse[0].size > listResponse[1].size) listResponse[0].size else listResponse[1].size
        val listQuickLinkModel = ArrayList<QuickLinkModel>()
        for (i in 0..sizeMax){
            if (i < listResponse[0].size){
                listQuickLinkModel.add(listResponse[0][i])
            }
            if (i < listResponse[1].size){
                listQuickLinkModel.add(listResponse[1][i])
            }
        }
        return listQuickLinkModel
    }
}