/*
 * Copyright (C) 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tikiapp.data.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tikiapp.data.models.BannerModel


/***
 * Very small database that will hold one title
 */
@Dao
interface BannerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTitle(banner: BannerModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBanner(list: ArrayList<BannerModel>)

    @Query("DELETE FROM banner_table")
    suspend fun deleteAllBanner()

    @get:Query("SELECT * FROM banner_table")
    val allNotes: LiveData<List<BannerModel>>
}

/**
 * TitleDatabase provides a reference to the dao to repositories
 */
@Database(entities = [BannerModel::class], version = 1, exportSchema = false)
abstract class TikiDatabase : RoomDatabase() {
    abstract val bannerDao: BannerDao
}

private lateinit var INSTANCE: TikiDatabase

/**
 * Instantiate a database from a context.
 */
fun getDatabase(context: Context): TikiDatabase {
    synchronized(TikiDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                    .databaseBuilder(
                            context.applicationContext,
                            TikiDatabase::class.java,
                            "tiki_demo_db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
    return INSTANCE
}
