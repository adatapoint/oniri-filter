package com.vince.onirifilter.data.repository

import com.vince.onirifilter.data.CallResult
import com.vince.onirifilter.domain.model.DreamType
import com.vince.onirifilter.domain.repository.FilterRepository
import com.vince.onirifilter.utils.RangeHelper

class FilterRepositoryImpl : FilterRepository {

    override fun getRange(): CallResult<List<String>> = CallResult.success(RangeHelper.range)

    override fun getDreamTypes(): CallResult<List<DreamType>> = CallResult.success(RangeHelper.dreamTypes)
}
