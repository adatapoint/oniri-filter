package com.vince.onirifilter.data.service

import com.vince.onirifilter.utils.BackendHelper

class FilterService {
    fun getRange() = BackendHelper.range
    fun getDreamTypes() = BackendHelper.dreamTypes
    fun getYesOrNoList() = BackendHelper.yesOrNoList
}
