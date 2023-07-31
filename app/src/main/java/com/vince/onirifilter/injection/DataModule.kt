package com.vince.onirifilter.injection

import com.vince.onirifilter.data.service.FilterService
import org.koin.dsl.module

val dataModule = module {
    single { FilterService() }
}
