package com.ducluanxutrieu.tikiapp.utiu;
import android.os.Process;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

public class BackgroundThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        int sTag = 1;
        thread.setName("CustomThread" + sTag);
        thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);

        // A exception handler is created to log the exception from threads
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NotNull Thread thread, @NotNull Throwable ex) {
                Log.e("Util.LOG_TAG", thread.getName() + " encountered an error: " + ex.getMessage());
            }
        });
        return thread;
    }
}