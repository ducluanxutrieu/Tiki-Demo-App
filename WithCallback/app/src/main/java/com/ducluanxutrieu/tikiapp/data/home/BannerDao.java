package com.ducluanxutrieu.tikiapp.data.home;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ducluanxutrieu.tikiapp.data.models.BannerModel;

import java.util.ArrayList;
import java.util.List;

@Dao
interface BannerDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllBanner(ArrayList<BannerModel> bannerList);

    @Query("DELETE FROM banner_table")
    void deleteAllBanner();

    @Query("SELECT * FROM banner_table")
    LiveData<List<BannerModel>> allNotes();
}
