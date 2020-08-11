package com.ducluanxutrieu.tikiapp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ducluanxutrieu.tikiapp.data.home.HomeRepository;
import com.ducluanxutrieu.tikiapp.data.models.BannerModel;
import com.ducluanxutrieu.tikiapp.data.models.FlashDealModel;
import com.ducluanxutrieu.tikiapp.data.models.FlashDealResponseModel;
import com.ducluanxutrieu.tikiapp.data.models.QuickLinkModel;
import com.ducluanxutrieu.tikiapp.utiu.CustomThreadPoolManager;
import com.ducluanxutrieu.tikiapp.utiu.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private final HomeRepository repository;
    public final LiveData<List<BannerModel>> listBanner;
    private CustomThreadPoolManager mCustomThreadPoolManager;

    private final MutableLiveData<ArrayList<QuickLinkModel>> _listQuickLink = new MutableLiveData<>();
    final LiveData<ArrayList<QuickLinkModel>> listQuickLink = _listQuickLink;

    private final MutableLiveData<ArrayList<FlashDealModel>> _listFlashDeal = new MutableLiveData<>();
    final LiveData<ArrayList<FlashDealModel>> listFlashDeal = _listFlashDeal;

    public HomeViewModel(HomeRepository repository) {
        this.repository = repository;
        this.listBanner = repository.bannerList;
        this.mCustomThreadPoolManager = CustomThreadPoolManager.getsInstance();
    }

    public void getAllData() {
        getListBanner();
        getListQuickLink();
        getListFlashDeal();
    }

    private void getListBanner() {
        Log.i("HomeViewModel", Calendar.getInstance().getTime().getTime() + " Banner");
        repository.getListBanner();
    }

    private void getListQuickLink() {
        Log.i("HomeViewModel", Calendar.getInstance().getTime().getTime() + " QuickLink");
        _listQuickLink.postValue(new ArrayList<>());
        repository.getQuickLink(new QuickLinkCallback() {
            @Override
            public void onCompleted(ArrayList<ArrayList<QuickLinkModel>> quickLinkModels) {
                mCustomThreadPoolManager.addRunnable(() -> {
                    ArrayList<QuickLinkModel> result = mixQuickLinkData(quickLinkModels);
                    _listQuickLink.postValue(result);
                    Log.i("HomeViewModel", Calendar.getInstance().getTime().getTime() + " QuickLink complete");
                });
            }

            @Override
            public void onError(Throwable cause) {
                Util.showToast(cause.getMessage());
                _listQuickLink.postValue(null);
            }
        });
    }

    private void getListFlashDeal() {
        Log.i("HomeViewModel", Calendar.getInstance().getTime().getTime() + " FlashDeal");
        _listFlashDeal.postValue(new ArrayList<>());
        repository.getFlashDeal(new FlashDealCallback() {
            @Override
            public void onCompleted(FlashDealResponseModel quickLinkModels) {
                _listFlashDeal.postValue(quickLinkModels.getDealModelList());
                Log.i("HomeViewModel", Calendar.getInstance().getTime().getTime() + " FlashDeal Complete");
            }

            @Override
            public void onError(Throwable cause) {
                Util.showToast(cause.getMessage());
                _listFlashDeal.postValue(null);
            }
        });
    }

    private ArrayList<QuickLinkModel> mixQuickLinkData(ArrayList<ArrayList<QuickLinkModel>> listResponse) {
        int sizeMax = Math.max(listResponse.get(0).size(), listResponse.get(1).size());
        ArrayList<QuickLinkModel> listQuickLinkModel = new ArrayList<>();
        for (int i = 0; i < sizeMax; i++) {
            if (i < listResponse.get(0).size()) {
                listQuickLinkModel.add(listResponse.get(0).get(i));
            }
            if (i < listResponse.get(1).size()) {
                listQuickLinkModel.add(listResponse.get(1).get(i));
            }
        }
        return listQuickLinkModel;
    }
}