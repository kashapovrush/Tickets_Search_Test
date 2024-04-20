package com.kashapovrush.country_selected_screen.di

import android.content.Context
import com.kashapovrush.country_selected_screen.ui.CountrySelectedFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CountrySelectedModule::class, ViewModelModule::class])
interface CountrySelectedComponent {


    fun inject(fragment: CountrySelectedFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): CountrySelectedComponent
    }
}