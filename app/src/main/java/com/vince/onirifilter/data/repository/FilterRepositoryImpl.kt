package com.vince.onirifilter.data.repository

import com.vince.onirifilter.data.CallResult
import com.vince.onirifilter.data.service.FilterService
import com.vince.onirifilter.domain.model.DreamType
import com.vince.onirifilter.domain.repository.FilterRepository

class FilterRepositoryImpl(private val filterService: FilterService) : FilterRepository {

    override fun getRange(): CallResult<List<String>> = CallResult.success(filterService.getRange())

    override fun getDreamTypes(): CallResult<List<DreamType>> = CallResult.success(filterService.getDreamTypes())

    override fun getYesOrNoList(): CallResult<Map<Int, String>> = CallResult.success(filterService.getYesOrNoList())
}
