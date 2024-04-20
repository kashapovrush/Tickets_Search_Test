package com.kashapovrush.tickets_screen.di

import android.content.Context
import com.kashapovrush.tickets_screen.ui.TicketsFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class, TicketsModule::class])
interface TicketsComponent {

    fun inject(fragment: TicketsFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): TicketsComponent
    }
}