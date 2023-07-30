package com.vince.onirifilter.injection

import com.vince.onirifilter.scenes.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    viewModel { MainViewModel(get(), get()) }
}