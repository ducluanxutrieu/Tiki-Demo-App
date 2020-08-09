package com.ducluanxutrieu.tikiapp.utiu;

import android.content.Context;
import android.widget.Toast;

public class UIUtiu {
    public static String getString(int stringId) {
        Context context = GlobalApplication.appContext;
        return context.getString(stringId);
    }

    public static void showToast(String toast) {
        Context context = GlobalApplication.appContext;
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
