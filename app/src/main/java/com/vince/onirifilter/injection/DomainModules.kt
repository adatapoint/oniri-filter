package com.vince.onirifilter.injection

import com.vince.onirifilter.data.repository.FilterRepositoryImpl
import com.vince.onirifilter.domain.repository.FilterRepository
import com.vince.onirifilter.domain.usecase.GetDreamTypesUseCase
import com.vince.onirifilter.domain.usecase.GetRangeUseCase
import com.vince.onirifilter.domain.usecase.GetYesOrNoListUseCase
import org.koin.dsl.module

val domainModule = module {

    // Repositories
    single<FilterRepository> { FilterRepositoryImpl(get()) }

    // UseCases
    single { GetRangeUseCase(get()) }
    single { GetDreamTypesUseCase(get()) }
    single { GetYesOrNoListUseCase(get()) }
}
