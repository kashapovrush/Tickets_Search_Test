package com.kashapovrush.country_selected_screen.di

import android.content.Context
import com.kashapovrush.network.api.ApiFactory
import com.kashapovrush.network.api.ApiService
import com.kashapovrush.utils.preferenceManager.PreferencesManager
import com.kashapovrush.utils.preferenceManager.PreferencesManagerImpl
import dagger.Module
import dagger.Provides

@Module
object CountrySelectedModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }

    @Provides
    fun providePreferences(context: Context): PreferencesManager {
        return PreferencesManagerImpl(context)
    }
}