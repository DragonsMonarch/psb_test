package com.dragonslotos.psb_test.di

import com.dragonslotos.psb_test.presenter.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val app_module = module { viewModel<MainViewModel> { MainViewModel(get(), get()) } }