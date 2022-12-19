package com.example.cointrack.networks

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val cryptocurrency_api_key = "fae43423-c927-4123-b5e7-e4a1ee701c47"
const val base_url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/"



fun getInstance():Retrofit{
    val  logging :HttpLoggingInterceptor=HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val  httpClient:OkHttpClient.Builder= OkHttpClient.Builder()
    httpClient.addInterceptor(logging)
    httpClient.addInterceptor{
            chain ->
        val request: Request = chain.request().newBuilder().addHeader("X-CMC_PRO_API_KEY", cryptocurrency_api_key).build()
        chain.proceed(request)
    }


    val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    return retrofit
}





object NetworkService {
    val serviceApi : ServiceApi by lazy {
        getInstance().create(ServiceApi::class.java)
    }
}