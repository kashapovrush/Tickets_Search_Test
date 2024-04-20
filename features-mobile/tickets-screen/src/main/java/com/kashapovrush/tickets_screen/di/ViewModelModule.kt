package com.kashapovrush.tickets_screen.di

import androidx.lifecycle.ViewModel
import com.kashapovrush.common.di.ViewModuleKey
import com.kashapovrush.common.viewModel.CommonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModuleKey(CommonViewModel::class)
    @Binds
    fun bindViewModel(viewModel: CommonViewModel): ViewModel
}