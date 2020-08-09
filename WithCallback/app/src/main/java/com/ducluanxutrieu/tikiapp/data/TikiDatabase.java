package com.ducluanxutrieu.tikiapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ducluanxutrieu.tikiapp.data.home.BannerDao;
import com.ducluanxutrieu.tikiapp.data.models.BannerModel;

@Database(entities = {BannerModel.class}, version = 1, exportSchema = false)
public abstract class TikiDatabase extends RoomDatabase{
    public abstract BannerDao bannerDao();
    static private TikiDatabase INSTANCE;

    public static TikiDatabase getInstance(Context context){
        synchronized (TikiDatabase.class){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                        context,
                        TikiDatabase.class,
                        "tiki_demo_db"
                )
                    .fallbackToDestructiveMigration()
                        .build();
            }

            return INSTANCE;
        }
    }
}


