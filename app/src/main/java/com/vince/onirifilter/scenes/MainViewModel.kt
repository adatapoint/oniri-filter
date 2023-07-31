package com.vince.onirifilter.scenes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vince.onirifilter.data.CallResult
import com.vince.onirifilter.domain.model.DreamType
import com.vince.onirifilter.domain.usecase.GetDreamTypesUseCase
import com.vince.onirifilter.domain.usecase.GetRangeUseCase
import com.vince.onirifilter.domain.usecase.GetYesOrNoListUseCase

class MainViewModel(
    private val getRangeUseCase: GetRangeUseCase,
    private val getDreamTypesUseCase: GetDreamTypesUseCase,
    private val getYesOrNoListUseCase: GetYesOrNoListUseCase,
) : ViewModel() {

    fun getRange(): LiveData<CallResult<List<String>>> = liveData { emit(getRangeUseCase()) }

    fun getDreamTypes(): LiveData<CallResult<List<DreamType>>> = liveData { emit(getDreamTypesUseCase()) }

    fun getYesOrNoList(): LiveData<CallResult<Map<Int, String>>> = liveData { emit(getYesOrNoListUseCase()) }
}
