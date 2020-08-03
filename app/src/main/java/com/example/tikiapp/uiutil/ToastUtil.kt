package com.uit.party.util

import android.widget.Toast
import com.example.tikiapp.uiutil.GlobalApplication

object ToastUtil {
    fun showToast(toast: String){
        val context =  GlobalApplication.appContext
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }
}