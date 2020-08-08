package com.ducluanxutrieu.tikiapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ducluanxutrieu.tikiapp.data.home.HomeRepository;
import com.ducluanxutrieu.tikiapp.data.models.BannerModel;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private HomeRepository repository = new HomeRepository();

    public LiveData<List<BannerModel>> listBanner = repository.bannerList;

    public void getAllData() {
        getListBanner();
    }

    private void getListBanner() {
        repository.getListBanner();
    }
}