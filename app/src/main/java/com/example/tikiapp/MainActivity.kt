package com.example.tikiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tikiapp.uiutil.SetupConnectToServer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        internal var TOKEN_ACCESS: String = ""
        val serviceRetrofit = SetupConnectToServer().setupConnect()
    }
}