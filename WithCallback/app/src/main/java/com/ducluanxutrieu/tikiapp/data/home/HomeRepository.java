package com.ducluanxutrieu.tikiapp.data.home;

import androidx.lifecycle.LiveData;

import com.ducluanxutrieu.tikiapp.data.RetrofitService;
import com.ducluanxutrieu.tikiapp.data.models.BannerModel;
import com.ducluanxutrieu.tikiapp.data.models.BannerResponseModel;
import com.ducluanxutrieu.tikiapp.data.models.FlashDealResponseModel;
import com.ducluanxutrieu.tikiapp.data.models.QuickLinkResponseModel;
import com.ducluanxutrieu.tikiapp.ui.home.FlashDealCallback;
import com.ducluanxutrieu.tikiapp.ui.home.QuickLinkCallback;
import com.ducluanxutrieu.tikiapp.utiu.CustomThreadPoolManager;
import com.ducluanxutrieu.tikiapp.utiu.ResultError;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private final BannerDao bannerDao;
    private final RetrofitService networkService;
    public final LiveData<List<BannerModel>> bannerList;
    private final CustomThreadPoolManager customThreadPoolManager;

    public HomeRepository(BannerDao bannerDao, RetrofitService networkService) {
        this.bannerDao = bannerDao;
        this.networkService = networkService;
        this.bannerList = bannerDao.allNotes();
        this.customThreadPoolManager = CustomThreadPoolManager.getsInstance();
    }


    public void getListBanner() {
        customThreadPoolManager.addRunnable(() -> networkService.getBannerList().enqueue(new Callback<BannerResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<BannerResponseModel> call, @NotNull final Response<BannerResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    saveDataToDB(response.body().getBannerList());
                }
            }

            @Override
            public void onFailure(@NotNull Call<BannerResponseModel> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        }));
    }

    public void getQuickLink(final QuickLinkCallback quickLinkCallback) {
        customThreadPoolManager.addRunnable(() -> networkService.getQuickLink().enqueue(new Callback<QuickLinkResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<QuickLinkResponseModel> call, @NotNull final Response<QuickLinkResponseModel> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        quickLinkCallback.onCompleted(response.body().getData());
                    } else {
                        quickLinkCallback.onError(new ResultError("Unable to get Quick Link", null));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    quickLinkCallback.onError(new ResultError("Unable to get Quick Link", e));
                }
            }

            @Override
            public void onFailure(@NotNull Call<QuickLinkResponseModel> call, @NotNull Throwable t) {
                quickLinkCallback.onError(new ResultError("Unable to get Quick Link", t.getCause()));
            }
        }));
    }

    public void getFlashDeal(final FlashDealCallback callback) {
        customThreadPoolManager.addRunnable(() -> networkService.getFlashDeal().enqueue(new Callback<FlashDealResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<FlashDealResponseModel> call, @NotNull final Response<FlashDealResponseModel> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        callback.onCompleted(response.body());
                    } else {
                        callback.onError(new ResultError("Unable to get Flash Deal", null));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onError(new ResultError("Unable to get Flash Deal", e));
                }
            }

            @Override
            public void onFailure(@NotNull Call<FlashDealResponseModel> call, @NotNull Throwable t) {
                callback.onError(new ResultError("Unable to get Flash Deal", t.getCause()));
            }
        }));
    }

    private void saveDataToDB(final ArrayList<BannerModel> data) {
        customThreadPoolManager.addRunnable(() -> {
            bannerDao.deleteAllBanner();
            bannerDao.insertAllBanner(data);
        });
    }
}
