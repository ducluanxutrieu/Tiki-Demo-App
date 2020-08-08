package com.ducluanxutrieu.tikiapp.utiu;

import android.content.Context;
import android.widget.Toast;

public class UIUtiu {
    Context context = GlobalApplication.appContext;
    String getString(int stringId){
        return context.getString(stringId);
    }

    void showToast(String toast){
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
