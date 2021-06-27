package com.example.di


import com.example.machinetest.BuildConfig
import com.example.network.ApiInterface


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkProvider {

    private const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/"

    @Singleton
    @Provides
    @Named("appId")
    fun getAppId() = "c5555ca5-304f-4f7a-918f-bc6963b55c5e"


    @Singleton
    @Provides
    @Named("ContentType")
    fun getContentType() = "application/json"

    @Singleton
    @Provides
    @Named("Lang")
    fun getLang() = "en"

    @Singleton
    @Provides
    @Named("DeviceType")
    fun getDeviceType() = "Android"

    @Singleton
    @Provides
    fun provideClient(): ApiInterface {

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(3, 40, TimeUnit.SECONDS))
            .retryOnConnectionFailure(true)
            //add below line only if you want to log you API request (OPTIONAL)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}