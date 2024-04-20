package com.kashapovrush.main_screen.ui.di

import androidx.lifecycle.ViewModel
import com.kashapovrush.common.di.ViewModuleKey
import com.kashapovrush.common.viewModel.CommonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModuleKey(CommonViewModel::class)
    fun bindHeadlinesViewModel(viewModel: CommonViewModel): ViewModel
}