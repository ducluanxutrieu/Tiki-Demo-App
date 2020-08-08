package com.ducluanxutrieu.tikiapp.data.home;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ducluanxutrieu.tikiapp.data.models.BannerModel;
import com.ducluanxutrieu.tikiapp.utiu.GlobalApplication;

@Database(entities = {BannerModel.class}, version = 1, exportSchema = false)
abstract class TikiDatabase extends RoomDatabase{
    abstract BannerDao bannerDao();
    static private TikiDatabase INSTANCE;

    static TikiDatabase getInstance(){
        synchronized (TikiDatabase.class){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                        new GlobalApplication().getApplicationContext(),
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


