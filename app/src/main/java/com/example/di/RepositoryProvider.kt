package com.example.di


import com.example.network.ApiInterface
import com.example.repo.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryProvider {

    @Singleton
    @Provides
    fun provideRepository(
        apiInterface: ApiInterface,
        @Named("appId") appId: String,
        @Named("ContentType") contentType: String,
        @Named("Lang") lang: String,
        @Named("DeviceType") deviceType: String
    ) = ArticleRepository(
        api = apiInterface,
        appId = appId, contentType = contentType, deviceType = deviceType, lang = lang
    )
}