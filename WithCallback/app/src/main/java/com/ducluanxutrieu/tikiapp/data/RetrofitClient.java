package com.ducluanxutrieu.tikiapp.data;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.tiki.vn/";

    public static RetrofitService getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            //setup Interceptor
            Interceptor interceptor = chain -> {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .method(chain.request().method(), chain.request().body())
                        .build();
                return chain.proceed(newRequest);
            };

            //setup OkHttp
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RetrofitService.class);
    }
}