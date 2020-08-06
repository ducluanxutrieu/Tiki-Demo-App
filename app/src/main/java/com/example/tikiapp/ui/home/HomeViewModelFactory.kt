package com.example.tikiapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tikiapp.data.home.HomeRepository
import com.example.tikiapp.data.home.TikiDatabase
import com.example.tikiapp.util.getNetworkService

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class HomeViewModelFactory(private val database: TikiDatabase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                    HomeRepository(getNetworkService(), database.bannerDao)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}