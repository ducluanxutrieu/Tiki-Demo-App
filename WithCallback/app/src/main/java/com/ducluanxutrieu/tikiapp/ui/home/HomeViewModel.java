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
import com.ducluanxutrieu.tikiapp.utiu.SimpleCallback;
import com.ducluanxutrieu.tikiapp.utiu.UIUtiu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private final HomeRepository repository;
    public final LiveData<List<BannerModel>> listBanner;

    private final MutableLiveData<ArrayList<QuickLinkModel>> _listQuickLink = new MutableLiveData<>();
    final LiveData<ArrayList<QuickLinkModel>> listQuickLink = _listQuickLink;

    private final MutableLiveData<ArrayList<FlashDealModel>> _listFlashDeal = new MutableLiveData<>();
    final LiveData<ArrayList<FlashDealModel>> listFlashDeal = _listFlashDeal;

    boolean isBannerRequested = false;
    boolean isQuickLinkRequested = false;

    public HomeViewModel(HomeRepository repository) {
        this.repository = repository;
        listBanner = repository.bannerList;
    }


    public void getAllData() {
        isBannerRequested = false;
        isQuickLinkRequested = false;
        Log.i("HomeViewModel", Calendar.getInstance().getTime().getTime() + "");
        getListBanner();
        Log.i("HomeViewModel", Calendar.getInstance().getTime().getTime() + "");
        getListQuickLink();
    }

    private void getListBanner() {
        new Thread(() -> repository.getListBanner(new SimpleCallback() {
            @Override
            public void onCompleted() {
                isBannerRequested = true;
                getListFlashDeal();
            }

            @Override
            public void onError(Throwable cause) {
                isBannerRequested = true;
                getListFlashDeal();
            }
        })).start();
    }

    private void getListQuickLink() {
        _listQuickLink.setValue(new ArrayList<>());
        new Thread(() -> repository.getQuickLink(new QuickLinkCallback() {
            @Override
            public void onCompleted(ArrayList<ArrayList<QuickLinkModel>> quickLinkModels) {
                _listQuickLink.postValue(mixQuickLinkData(quickLinkModels));
                isQuickLinkRequested = true;
                getListFlashDeal();
            }

            @Override
            public void onError(Throwable cause) {
                UIUtiu.showToast(cause.getMessage());
                _listQuickLink.postValue(null);
                isQuickLinkRequested = true;
                getListFlashDeal();
            }
        })).start();
    }

    private void getListFlashDeal() {
        //Call Banner API + Quick Link API in parallel , after both of them finish call Flash Deal API
        if (isBannerRequested && isQuickLinkRequested) {
            Log.i("HomeViewModel", Calendar.getInstance().getTime().getTime() + "FlashDeal");
            _listFlashDeal.setValue(new ArrayList<>());
            new Thread(() -> repository.getFlashDeal(new FlashDealCallback() {
                @Override
                public void onCompleted(FlashDealResponseModel quickLinkModels) {
                    _listFlashDeal.postValue(quickLinkModels.getDealModelList());
                }

                @Override
                public void onError(Throwable cause) {
                    UIUtiu.showToast(cause.getMessage());
                    _listFlashDeal.postValue(null);
                }
            })).start();
        }
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