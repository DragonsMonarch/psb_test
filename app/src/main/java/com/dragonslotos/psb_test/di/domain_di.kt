package com.dragonslotos.psb_test.di

import com.dragonslotos.psb_test.domain.usecases.GetRates
import com.dragonslotos.psb_test.domain.usecases.SharedPreferences
import org.koin.dsl.module

val domain_module = module {
    factory<GetRates> { GetRates(get()) }
    factory<SharedPreferences> { SharedPreferences(get()) }
}