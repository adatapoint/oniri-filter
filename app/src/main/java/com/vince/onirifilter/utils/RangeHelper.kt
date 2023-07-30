package com.vince.onirifilter.utils

import com.vince.onirifilter.domain.model.DreamType

class RangeHelper {

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
            DreamType(id = 1, type = "Dream"),
            DreamType(id = 2, type = "Nightmare")
        )
    }
}
