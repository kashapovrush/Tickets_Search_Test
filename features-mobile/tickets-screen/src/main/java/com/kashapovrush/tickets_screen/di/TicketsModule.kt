package com.kashapovrush.tickets_screen.di

import com.kashapovrush.network.api.ApiFactory
import com.kashapovrush.network.api.ApiService
import dagger.Module
import dagger.Provides

@Module
object TicketsModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}