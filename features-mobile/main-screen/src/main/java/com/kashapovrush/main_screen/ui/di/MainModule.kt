package com.kashapovrush.main_screen.ui.di

import android.content.Context
import com.kashapovrush.network.api.ApiFactory
import com.kashapovrush.network.api.ApiService
import com.kashapovrush.utils.preferenceManager.PreferencesManager
import com.kashapovrush.utils.preferenceManager.PreferencesManagerImpl
import dagger.Module
import dagger.Provides

@Module
object MainModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }

    @Provides
    fun providePreference(context: Context): PreferencesManager {
        return PreferencesManagerImpl(context)
    }
}