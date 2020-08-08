package com.ducluanxutrieu.tikiapp.data.home;

import androidx.lifecycle.LiveData;

import com.ducluanxutrieu.tikiapp.data.RetrofitClient;
import com.ducluanxutrieu.tikiapp.data.RetrofitService;
import com.ducluanxutrieu.tikiapp.data.models.BannerModel;
import com.ducluanxutrieu.tikiapp.data.models.BannerResponseModel;
import com.ducluanxutrieu.tikiapp.utiu.Executor;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private TikiDatabase tikiDatabase = TikiDatabase.getInstance();
    private BannerDao bannerDao = tikiDatabase.bannerDao();
    private RetrofitService networkService = RetrofitClient.getRetrofitInstance();

    public LiveData<List<BannerModel>> bannerList = bannerDao.allNotes();

    public void getListBanner(){
        networkService.getBannerList().enqueue(new Callback<BannerResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<BannerResponseModel> call, @NotNull final Response<BannerResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        Executor.BACKGROUND.submit(new Runnable() {
                            @Override
                            public void run() {
                                bannerDao.insertAllBanner(response.body().getData());
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BannerResponseModel> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
