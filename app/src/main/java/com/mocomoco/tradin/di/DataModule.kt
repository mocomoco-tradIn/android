package com.mocomoco.tradin.di

import com.mocomoco.tradin.data.data.repository.RefreshTokenRepository
import com.mocomoco.tradin.data.data.resource.local.PreferenceService
import com.mocomoco.tradin.data.data.resource.remote.ApiHeaderInterceptor
import com.mocomoco.tradin.data.data.resource.remote.RefreshTokenService
import com.mocomoco.tradin.data.data.resource.remote.RetrofitService
import com.mocomoco.tradin.data.data.resource.remote.apis.AuthApi
import com.mocomoco.tradin.data.data.resource.remote.apis.FeedApi
import com.mocomoco.tradin.data.data.resource.remote.apis.ProductApi
import com.mocomoco.tradin.data.data.resource.remote.apis.RefreshTokenApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(apiHeaderInterceptor: ApiHeaderInterceptor): RetrofitService {
        return RetrofitService(apiHeaderInterceptor)
    }

    @Provides
    @Singleton
    fun provideApiHeaderInterceptor(
        preferenceService: PreferenceService,
        refreshTokenApi: RefreshTokenApi
    ): ApiHeaderInterceptor {
        return ApiHeaderInterceptor(preferenceService, refreshTokenApi)
    }

    @Provides
    @Singleton
    fun provideRefreshTokenService(): RefreshTokenService {
        return RefreshTokenService()
    }

    @Provides
    @Singleton
    fun provideRefreshTokenApi(refreshTokenService: RefreshTokenService): RefreshTokenApi {
        return refreshTokenService.retrofit.create(RefreshTokenApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSignupApi(retrofitService: RetrofitService): AuthApi {
        return retrofitService.retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofitService: RetrofitService): ProductApi {
        return retrofitService.retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFeedApi(retrofitService: RetrofitService): FeedApi {
        return retrofitService.retrofit.create(FeedApi::class.java)
    }
}

