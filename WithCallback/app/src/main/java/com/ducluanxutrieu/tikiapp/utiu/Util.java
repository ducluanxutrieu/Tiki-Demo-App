package com.ducluanxutrieu.tikiapp.utiu;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {
    public static final String LOG_TAG = "BackgroundThread";
    public static final int MESSAGE_ID = 1;
    public static final String MESSAGE_BODY = "MESSAGE_BODY";
    public static final String EMPTY_MESSAGE = "<EMPTY_MESSAGE>";

    public static String getReadableTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static Message createMessage(int id, String dataString) {
        Bundle bundle = new Bundle();
        bundle.putString(Util.MESSAGE_BODY, dataString);
        Message message = new Message();
        message.what = id;
        message.setData(bundle);

        return message;
    }

    public static String getString(int stringId) {
        Context context = GlobalApplication.appContext;
        return context.getString(stringId);
    }

    public static void showToast(String toast) {
        Context context = GlobalApplication.appContext;
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
