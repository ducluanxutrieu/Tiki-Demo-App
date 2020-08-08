package com.example.tikiapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tikiapp.data.Result
import com.example.tikiapp.data.home.HomeRepository
import com.example.tikiapp.data.models.FlashDealModel
import com.example.tikiapp.data.models.QuickLinkModel
import com.example.tikiapp.util.ToastUtil
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    val listBanner = repository.bannerList

    private val _listQuickLink = MutableLiveData<ArrayList<QuickLinkModel>>()
    val listQuickLink: LiveData<ArrayList<QuickLinkModel>> = _listQuickLink

    private val _listFlashDeal = MutableLiveData<ArrayList<FlashDealModel>>()
    val listFlashDeal: LiveData<ArrayList<FlashDealModel>> = _listFlashDeal

    fun getAllData() {
        viewModelScope.launch {
            val listRequest = listOf(
                async { getListBanner() },
                async { getListQuickLink() }
            )
            listRequest.awaitAll()
            getListFlashDeal()
        }
    }

    private suspend fun getListBanner() {
        try {
            repository.getListBanner()
        } catch (error: Exception) {
            ToastUtil.showToast(error.message ?: "")
        }
    }

    private suspend fun getListQuickLink() {
        _listQuickLink.value = ArrayList()
        try {
            val result = repository.getQuickLink()
            if (result is Result.Success) {
                delay(2_000)
                _listQuickLink.value = mixQuickLinkData(result.data.quickLink)
            } else {
                _listQuickLink.value = null
            }
        } catch (error: Exception) {
            ToastUtil.showToast(error.message ?: "")
            _listQuickLink.value = null
        }
    }

    private suspend fun getListFlashDeal() {
        _listFlashDeal.value = ArrayList()
        try {
            val result = repository.getFlashDeal()
            if (result is Result.Success) {
                delay(2_000)
                _listFlashDeal.value = result.data.flashDealList
            } else {
                _listFlashDeal.value = null
            }
        } catch (error: Exception) {
            ToastUtil.showToast(error.message ?: "")
            _listFlashDeal.value = null
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