package com.vince.onirifilter.scenes

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vince.onirifilter.data.CallResult.Failure
import com.vince.onirifilter.data.CallResult.Success
import com.vince.onirifilter.domain.model.DreamType
import com.vince.onirifilter.ui.components.DreamsSearchBar
import com.vince.onirifilter.ui.components.PeriodSelector
import com.vince.onirifilter.ui.components.SectionCard
import com.vince.onirifilter.ui.components.SectionContainer
import com.vince.onirifilter.ui.components.SectionHeader
import com.vince.onirifilter.ui.components.SelectableOption
import com.vince.onirifilter.ui.components.TopBar
import com.vince.onirifilter.ui.theme.BackgroundColor
import com.vince.onirifilter.ui.theme.SectionBackgroundColor
import com.vince.onirifilter.utils.ConstantsHelper.EMPTY
import com.vince.onirifilter.utils.ConstantsHelper.NO_VALUE
import com.vince.onirifilter.utils.showToast
import org.koin.compose.koinInject

@Composable
fun MainScreen(
    onWhatIsThisClick: () -> Unit,
    onBackClick: () -> Unit,
    onSearch: (String) -> Unit,
) {
    val context: Context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewModel: MainViewModel = koinInject()
    val listState = rememberLazyListState()

    // Filter registration
    var dreamTypes by remember { mutableStateOf(emptyList<DreamType>()) }
    var selectedDreamType by remember { mutableStateOf(DreamType(NO_VALUE, EMPTY)) }
    var rangeList by remember { mutableStateOf(emptyList<String>()) }
    var dreamLengthSelection by remember { mutableStateOf(EMPTY) }
    var sleepQualitySelection by remember { mutableStateOf(EMPTY) }
    var personallyInDreamSelection by remember { mutableStateOf(NO_VALUE) }
    var emotionMoodSelection by remember { mutableStateOf(NO_VALUE) }

    fun getRangeForFilters() {
        viewModel.getRange().observe(lifecycleOwner) { result ->
            when (result) {
                is Failure -> Log.e("MAIN", "Failing ${result.t}")
                is Success -> rangeList = result.data
            }
        }
    }

    fun getDreamTypes() {
        viewModel.getDreamTypes().observe(lifecycleOwner) { result ->
            when (result) {
                is Failure -> Log.e("MAIN", "Failing ${result.t}")
                is Success -> dreamTypes = result.data
            }
        }
    }

    // Initial data requests
    LaunchedEffect(Unit) { getRangeForFilters() }
    LaunchedEffect(Unit) { getDreamTypes() }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = BackgroundColor)
                .padding(paddingValues)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .padding(bottom = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TopBar { onBackClick.invoke() }
            Text(text = "Filters", fontSize = 36.sp, color = Color.White)
            DreamsSearchBar(onSearch = { query -> onSearch.invoke(query) })
            PeriodSelector(
                title = "Period",
                onWhatIsThisClick = { onWhatIsThisClick.invoke() },
                initialDate = "December 18th, 2019",
                finalDate = "January 9th, 2020"
            )

            // Symbols within the dream
            SectionContainer {
                SectionHeader(sectionTitle = "Symbols") {
                    onWhatIsThisClick.invoke()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = SectionBackgroundColor)
                        .padding(8.dp)
                        .clickable { context.showToast("Adding symbols...") }
                ) {
                    Text(text = "+ Add symbols", color = Color.White)
                }
            }

            // Dream type
            SectionCard(
                title = "Type",
                onWhatIsThisClick = { onWhatIsThisClick.invoke() }
            ) {
                LazyRow(
                    state = listState,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(dreamTypes.size, key = { index -> index }) { position ->
                        val dreamType = dreamTypes[position]
                        SelectableOption(
                            id = dreamType,
                            size = 120.dp,
                            isSelected = selectedDreamType == dreamType,
                            onClick = { selectedValue ->
                                selectedDreamType = if (selectedDreamType == dreamType) {
                                    DreamType(NO_VALUE, EMPTY)
                                } else {
                                    selectedValue as DreamType
                                }
                            }
                        ) { isSelected, selectedDreamType ->
                            Text(
                                text = (selectedDreamType as DreamType).type,
                                color = if (isSelected) Color.Black else Color.White
                            )
                        }
                    }
                }
            }
            SectionContainer {
                SectionHeader(sectionTitle = "Type") {
                    onWhatIsThisClick.invoke()
                }
            }

            // Rate
            SectionContainer {
                SectionHeader(sectionTitle = "Rate") {
                    onWhatIsThisClick.invoke()
                }

                if (selectedDreamType.id != NO_VALUE) {

                } else {

                }
            }

            // Emotions
            SectionContainer {
                SectionHeader(sectionTitle = "Emotions") {
                    onWhatIsThisClick.invoke()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Advanced", fontSize = 36.sp, color = Color.White)

            // Dream length
            SectionCard(
                title = "Dream length",
                scaleLimits = Pair("Very short", "Very long"),
                onWhatIsThisClick = { onWhatIsThisClick.invoke() },
            ) {
                LazyRow(
                    state = listState,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(rangeList.size, key = { index -> index }) { position ->
                        val value = rangeList[position]
                        SelectableOption(
                            id = value,
                            size = 48.dp,
                            isSelected = dreamLengthSelection == value,
                            onClick = { id ->
                                dreamLengthSelection = if (dreamLengthSelection == id) {
                                    EMPTY
                                } else {
                                    id as String
                                }
                            }
                        ) { isSelected, id ->
                            Text(text = id.toString(), color = if (isSelected) Color.Black else Color.White)
                        }
                    }
                }
            }

            // Sleep quality
            SectionCard(
                title = "Sleep quality",
                scaleLimits = Pair("Very bad", "Very good"),
                onWhatIsThisClick = { onWhatIsThisClick.invoke() },
            ) {
                LazyRow(
                    state = listState,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(rangeList.size, key = { index -> index }) { position ->
                        val value = rangeList[position]
                        SelectableOption(
                            id = value,
                            size = 48.dp,
                            isSelected = sleepQualitySelection == value,
                            onClick = { id ->
                                sleepQualitySelection = if (sleepQualitySelection == id) {
                                    EMPTY
                                } else {
                                    id as String
                                }
                            }
                        ) { isSelected, id ->
                            Text(text = id.toString(), color = if (isSelected) Color.Black else Color.White)
                        }
                    }
                }
            }
        }
    }
}
