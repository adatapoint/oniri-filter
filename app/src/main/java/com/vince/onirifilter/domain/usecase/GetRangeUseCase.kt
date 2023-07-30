package com.vince.onirifilter.domain.usecase

import com.vince.onirifilter.domain.repository.FilterRepository

class GetRangeUseCase(private val filterRepository: FilterRepository) {
    operator fun invoke() = filterRepository.getRange()
}
