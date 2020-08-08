package com.example.tikiapp.util


object StringUtil {

    fun getString(stringId: Int): String {
        val context =  GlobalApplication.appContext
        return context?.getString(stringId) ?: ""
    }
}// prevent init Util class
