package com.example.tikiapp.data.home

import androidx.lifecycle.LiveData
import com.example.tikiapp.data.Result
import com.example.tikiapp.data.models.BannerModel
import com.example.tikiapp.data.models.FlashDealResponseModel
import com.example.tikiapp.data.models.QuickLinkResponseModel
import com.example.tikiapp.util.ServiceRetrofit

class HomeRepository(
    private val networkService: ServiceRetrofit,
    private val bannerDao: BannerDao
) {
    val bannerList: LiveData<List<BannerModel>> = bannerDao.allNotes

    suspend fun getListBanner() {
        try {
            val result = networkService.getBannerList()
            bannerDao.deleteAllBanner()
            bannerDao.insertAllBanner(result.bannerList)
        }catch (cause : Throwable){
            Result.Error(Exception(cause))
        }
    }

    suspend fun getQuickLink(): Result<QuickLinkResponseModel>{
        return try {
            Result.Success(networkService.getQuickLink())
        }catch (cause: Throwable){
            Result.Error(Exception(cause))
        }
    }

    suspend fun getFlashDeal(): Result<FlashDealResponseModel> {
        return try {
            Result.Success(networkService.getFlashDeal())
        }catch (cause: Throwable){
            Result.Error(Exception(cause))
        }
    }
}