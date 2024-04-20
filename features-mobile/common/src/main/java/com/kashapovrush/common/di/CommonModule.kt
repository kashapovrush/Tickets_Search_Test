package com.kashapovrush.common.di

import com.kashapovrush.network.api.ApiFactory
import com.kashapovrush.network.api.ApiService
import dagger.Module
import dagger.Provides

@Module
object CommonModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}