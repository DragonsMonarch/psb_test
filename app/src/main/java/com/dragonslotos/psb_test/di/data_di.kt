package com.dragonslotos.psb_test.di

import com.dragonslotos.psb_test.data.repository.GetDataRepository
import com.dragonslotos.psb_test.domain.repository.DataRepository
import org.koin.dsl.module

val data_module = module {
    factory<DataRepository> { GetDataRepository(get()) }
}