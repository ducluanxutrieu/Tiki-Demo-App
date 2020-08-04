package com.example.tikiapp.uiutil

import com.example.tikiapp.data.models.BannerResponseModel
import com.example.tikiapp.data.models.FlashDealResponseModel
import com.example.tikiapp.data.models.QuickLinkResponseModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ServiceRetrofit{
    @GET("v2/home/banners/v2")
    fun getBannerList() : Call<BannerResponseModel>

    @GET("shopping/v2/widgets/quick_link")
    fun getQuickLink() : Call<QuickLinkResponseModel>

    @GET("v2/widget/deals/hot")
    fun getFlashDeal() : Call<FlashDealResponseModel>
}

class SetupConnectToServer {
    fun setupConnect(): ServiceRetrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(logging)
            .build()


        val builder = Retrofit.Builder()
            .baseUrl("https://api.tiki.vn/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()

        return retrofit.create(ServiceRetrofit::class.java)
    }

    private var interceptor: Interceptor = Interceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .method(chain.request().method, chain.request().body)
            .build()

        chain.proceed(newRequest)
    }
}