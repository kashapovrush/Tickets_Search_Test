package com.kashapovrush.main_screen.ui.di

import android.content.Context
import androidx.core.view.KeyEventDispatcher
import com.kashapovrush.main_screen.ui.MainFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Component.Factory

@Component(modules = [MainModule::class, ViewModelModule::class])
interface MainComponent {

    fun inject(fragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MainComponent
    }
}