package com.ducluanxutrieu.tikiapp.ui.home;

import com.ducluanxutrieu.tikiapp.data.models.QuickLinkModel;

import java.util.ArrayList;

public interface QuickLinkCallback {
    void onCompleted(ArrayList<ArrayList<QuickLinkModel>> quickLinkModels);
    void onError(Throwable cause);
}