package com.ducluanxutrieu.tikiapp.ui.home;

import com.ducluanxutrieu.tikiapp.data.models.FlashDealResponseModel;

public interface FlashDealCallback {
    void onCompleted(FlashDealResponseModel flashDealResponseModel);
    void onError(Throwable cause);
}