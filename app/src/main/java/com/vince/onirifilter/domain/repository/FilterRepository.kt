package com.vince.onirifilter.domain.repository

import com.vince.onirifilter.data.CallResult
import com.vince.onirifilter.domain.model.DreamType

interface FilterRepository {
    fun getRange(): CallResult<List<String>>

    fun getDreamTypes(): CallResult<List<DreamType>>
}
