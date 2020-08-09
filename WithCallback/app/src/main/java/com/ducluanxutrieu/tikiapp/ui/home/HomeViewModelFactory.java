package com.ducluanxutrieu.tikiapp.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ducluanxutrieu.tikiapp.data.RetrofitClient;
import com.ducluanxutrieu.tikiapp.data.RetrofitService;
import com.ducluanxutrieu.tikiapp.data.TikiDatabase;
import com.ducluanxutrieu.tikiapp.data.home.BannerDao;
import com.ducluanxutrieu.tikiapp.data.home.HomeRepository;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    public HomeViewModelFactory(TikiDatabase database) {
        this.database = database;
    }

    private final TikiDatabase database;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        BannerDao bannerDao = database.bannerDao();
        RetrofitService networkService = RetrofitClient.getRetrofitInstance();

        if (modelClass.isAssignableFrom(HomeViewModel.class)){
            return (T) new HomeViewModel(new HomeRepository(bannerDao, networkService));
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
