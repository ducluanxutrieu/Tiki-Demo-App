package com.example.tikiapp.util

import com.example.tikiapp.data.models.BannerResponseModel
import com.example.tikiapp.data.models.FlashDealResponseModel
import com.example.tikiapp.data.models.QuickLinkResponseModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ServiceRetrofit {
    @GET("v2/home/banners/v2")
    suspend fun getBannerList(): BannerResponseModel

    @GET("shopping/v2/widgets/quick_link")
    suspend fun getQuickLink(): QuickLinkResponseModel

    @GET("v2/widget/deals/hot")
    suspend fun getFlashDeal(): FlashDealResponseModel
}

private val service: ServiceRetrofit by lazy {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(logging)
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.tiki.vn/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(ServiceRetrofit::class.java)
}

fun getNetworkService() = service

private var interceptor: Interceptor = Interceptor { chain ->
    val newRequest: Request = chain.request().newBuilder()
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .method(chain.request().method, chain.request().body)
        .build()

    chain.proceed(newRequest)
}