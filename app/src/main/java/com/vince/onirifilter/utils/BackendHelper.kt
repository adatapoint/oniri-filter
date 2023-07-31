package com.vince.onirifilter.utils

import com.vince.onirifilter.R
import com.vince.onirifilter.domain.model.DreamType

class BackendHelper {

    companion object {
        // Since we won't be adding or subtracting this ranges
        // they are more useful as Strings
        private const val range1 = "1"
        private const val range2 = "2"
        private const val range3 = "3"
        private const val range4 = "4"
        private const val range5 = "5"
        val range: List<String> = listOf(range1, range2, range3, range4, range5)

        val dreamTypes: List<DreamType> = listOf(
            DreamType(id = 1, type = "Dream", R.drawable.ic_dream),
            DreamType(id = 2, type = "Nightmare", R.drawable.ic_nightmare),
        )

        val yesOrNoList: Map<Int, String> = mapOf(
            0 to "No",
            1 to "Yes",
        )
    }
}
