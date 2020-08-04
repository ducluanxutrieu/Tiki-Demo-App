package com.example.tikiapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tikiapp.data.Result
import com.example.tikiapp.data.home.HomeRepository
import com.example.tikiapp.data.models.BannerModel
import com.example.tikiapp.data.models.FlashDealModel
import com.example.tikiapp.data.models.QuickLinkModel


class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val _listBanner = MutableLiveData<ArrayList<BannerModel>>()
    val listBanner: LiveData<ArrayList<BannerModel>> = _listBanner

    private val _listQuickLink = MutableLiveData<ArrayList<QuickLinkModel>>()
    val listQuickLink: LiveData<ArrayList<QuickLinkModel>> = _listQuickLink

    private val _listFlashDeal = MutableLiveData<ArrayList<FlashDealModel>>()
    val listFlashDeal: LiveData<ArrayList<FlashDealModel>> = _listFlashDeal

    fun getAllData(){
        getListBanner()
    }

    private fun getListBanner(){
        _listBanner.value = ArrayList()
        homeRepository.getListBanner {
            if (it is Result.Success){
                _listBanner.value = it.data.bannerList
                getListQuickLink()
            }else {
                _listBanner.value = ArrayList()
            }
        }
    }

    private fun getListQuickLink(){
        _listQuickLink.value = ArrayList()
        homeRepository.getQuickLink {
            if (it is Result.Success){
                _listQuickLink.value = mixQuickLinkData(it.data.quickLink)
                getListFlashDeal()
            }else {
                _listQuickLink.value = ArrayList()
            }
        }
    }

    private fun getListFlashDeal(){
        _listFlashDeal.value = ArrayList()
        homeRepository.getFlashDeal {
            if (it is Result.Success){
                _listFlashDeal.value = it.data.flashDealList
            }else {
                _listFlashDeal.value = ArrayList()
            }
        }
    }

    private fun mixQuickLinkData(listResponse: ArrayList<ArrayList<QuickLinkModel>>): ArrayList<QuickLinkModel> {
        val sizeMax =
            if (listResponse[0].size > listResponse[1].size) listResponse[0].size else listResponse[1].size
        val listQuickLinkModel = ArrayList<QuickLinkModel>()
        for (i in 0..sizeMax) {
            if (i < listResponse[0].size) {
                listQuickLinkModel.add(listResponse[0][i])
            }
            if (i < listResponse[1].size) {
                listQuickLinkModel.add(listResponse[1][i])
            }
        }
        return listQuickLinkModel
    }
}