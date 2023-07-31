package com.vince.onirifilter.scenes

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vince.onirifilter.R
import com.vince.onirifilter.data.CallResult.Failure
import com.vince.onirifilter.data.CallResult.Success
import com.vince.onirifilter.domain.model.DreamType
import com.vince.onirifilter.ui.components.FilterSearchBar
import com.vince.onirifilter.ui.components.PeriodSelector
import com.vince.onirifilter.ui.components.SectionCard
import com.vince.onirifilter.ui.components.SectionContainer
import com.vince.onirifilter.ui.components.SectionHeader
import com.vince.onirifilter.ui.components.SelectableOption
import com.vince.onirifilter.ui.components.TopBar
import com.vince.onirifilter.ui.theme.BackgroundColor
import com.vince.onirifilter.ui.theme.OptionBackgroundColor
import com.vince.onirifilter.ui.theme.SectionBackgroundColor
import com.vince.onirifilter.ui.theme.WhiteWithAlpha
import com.vince.onirifilter.ui.theme.bodyMedium
import com.vince.onirifilter.ui.theme.titleLarge
import com.vince.onirifilter.ui.theme.titleMedium
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
    var yesOrNoList by remember { mutableStateOf(emptyMap<Int, String>()) }
    var selectedDreamType by remember { mutableStateOf(DreamType(NO_VALUE, EMPTY, NO_VALUE)) }
    var rangeList by remember { mutableStateOf(emptyList<String>()) }
    var dreamLengthSelection by remember { mutableStateOf(EMPTY) }
    var rateSelection by remember { mutableStateOf(EMPTY) }
    var sleepQualitySelection by remember { mutableStateOf(EMPTY) }
    var personallyInDreamSelection by remember { mutableStateOf(EMPTY) }
    var emotionMoodSelection by remember { mutableStateOf(EMPTY) }

    fun resetScreen() {
        selectedDreamType = DreamType(NO_VALUE, EMPTY, NO_VALUE)
        rateSelection = EMPTY
        dreamLengthSelection = EMPTY
        sleepQualitySelection = EMPTY
        personallyInDreamSelection = EMPTY
        emotionMoodSelection = EMPTY
    }

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

    fun getYesOrNoList() {
        viewModel.getYesOrNoList().observe(lifecycleOwner) { result ->
            when (result) {
                is Failure -> Log.e("MAIN", "Failing ${result.t}")
                is Success -> yesOrNoList = result.data
            }
        }
    }

    // Initial data requests
    LaunchedEffect(Unit) { getRangeForFilters() }
    LaunchedEffect(Unit) { getDreamTypes() }
    LaunchedEffect(Unit) { getYesOrNoList() }

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(0.6f),
                    text = "Filters",
                    color = White,
                    style = titleLarge
                )
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .background(color = SectionBackgroundColor)
                        .size(50.dp)
                        .clickable { resetScreen() }
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_cancel),
                        tint = WhiteWithAlpha,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    modifier = Modifier
                        .weight(0.25f)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .fillMaxSize()
                        .background(color = White)
                        .clickable { context.showToast("Applying filters...") }
                        .padding(8.dp),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(id = R.drawable.ic_checkmark),
                        contentDescription = null,
                        tint = Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(style = titleMedium, text = "Filter", color = Black)
                }

            }

            // Sort by
            SectionContainer(
                modifier = Modifier
                    .wrapContentHeight()
                    .clickable { context.showToast("Sorting by...") }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically
                ) {
                    Row(modifier = Modifier.weight(0.5f)) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .align(CenterVertically),
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier.align(CenterVertically),
                            text = "Sort by",
                            color = White,
                            style = titleMedium
                        )
                    }
                    Row(modifier = Modifier.weight(0.5f)) {
                        Text(
                            modifier = Modifier.align(CenterVertically),
                            text = "Date (most recent)",
                            color = WhiteWithAlpha,
                            style = titleMedium
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            modifier = Modifier
                                .size(10.dp)
                                .align(CenterVertically),
                            painter = painterResource(id = R.drawable.ic_expand),
                            contentDescription = null
                        )
                    }
                }
            }
            FilterSearchBar(onSearch = { query -> onSearch.invoke(query) })
            PeriodSelector(
                title = "Period",
                onWhatIsThisClick = { onWhatIsThisClick.invoke() },
                initialDate = "December 18th, 2019",
                finalDate = "January 9th, 2020",
                onPickDateClick = { context.showToast("Pick a date...") }
            )

            // Symbols within the dream
            SectionContainer(
                header = {
                    SectionHeader(sectionTitle = "Symbols") {
                        onWhatIsThisClick.invoke()
                    }
                }
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = OptionBackgroundColor)
                        .padding(horizontal = 16.dp)
                        .clickable { context.showToast("Adding symbols...") },
                    verticalArrangement = Arrangement.Center
                ) { Text(text = "+ Add symbols", color = WhiteWithAlpha, style = titleMedium) }
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
                                    DreamType(NO_VALUE, EMPTY, NO_VALUE)
                                } else {
                                    selectedValue as DreamType
                                }
                            }
                        ) { isSelected, selectedDreamType ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = (selectedDreamType as DreamType).icon),
                                    contentDescription = null,
                                    tint = if (isSelected) Black else WhiteWithAlpha,
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = selectedDreamType.type,
                                    color = if (isSelected) Black else WhiteWithAlpha,
                                    style = titleMedium
                                )
                            }
                        }
                    }
                }
            }

            // Rate
            if (selectedDreamType.id != NO_VALUE) {
                SectionCard(
                    title = "Rate",
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
                                isSelected = rateSelection == value,
                                onClick = { id ->
                                    rateSelection = if (rateSelection == id) {
                                        EMPTY
                                    } else {
                                        id as String
                                    }
                                }
                            ) { isSelected, id ->
                                Text(
                                    style = bodyMedium,
                                    text = id.toString(),
                                    color = if (isSelected) Black else WhiteWithAlpha
                                )
                            }
                        }
                    }
                }
            } else {
                SectionContainer(
                    header = {
                        SectionHeader(sectionTitle = "Rate") {
                            onWhatIsThisClick.invoke()
                        }
                    }
                ) {
                    Text(
                        text = "You need to choose a type in order to filter by rate.",
                        color = WhiteWithAlpha,
                        style = bodyMedium
                    )
                }
            }

            // Emotions
            SectionContainer(
                header = {
                    SectionHeader(sectionTitle = "Emotions") {
                        onWhatIsThisClick.invoke()
                    }
                }
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = OptionBackgroundColor)
                        .padding(horizontal = 8.dp)
                        .clickable { context.showToast("Adding emotions...") },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) { Text(text = "+ Add emotions", color = White) }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Advanced", color = White, style = titleLarge)

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
                            Text(text = id.toString(), color = if (isSelected) Black else WhiteWithAlpha)
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
                            Text(text = id.toString(), color = if (isSelected) Black else WhiteWithAlpha)
                        }
                    }
                }
            }

            // Personally in dream
            SectionCard(
                title = "Personally in dream",
                onWhatIsThisClick = { onWhatIsThisClick.invoke() },
            ) {
                LazyRow(
                    state = listState,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    yesOrNoList.forEach { value ->
                        item {
                            SelectableOption(
                                id = value.value,
                                height = 48.dp,
                                width = 120.dp,
                                isSelected = personallyInDreamSelection == value.value,
                                onClick = { id ->
                                    personallyInDreamSelection = if (personallyInDreamSelection == id as String) {
                                        EMPTY
                                    } else {
                                        id
                                    }
                                }
                            ) { isSelected, id ->
                                Text(text = id.toString(), color = if (isSelected) Black else WhiteWithAlpha)
                            }
                        }

                    }
                }
            }

            // Emotions correspond to mood
            SectionCard(
                title = "Emotions correspond to mood",
                onWhatIsThisClick = { onWhatIsThisClick.invoke() },
            ) {
                LazyRow(
                    state = listState,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    yesOrNoList.forEach { value ->
                        item {
                            SelectableOption(
                                id = value.value,
                                height = 48.dp,
                                width = 120.dp,
                                isSelected = emotionMoodSelection == value.value,
                                onClick = { id ->
                                    emotionMoodSelection = if (emotionMoodSelection == id as String) {
                                        EMPTY
                                    } else {
                                        id
                                    }
                                }
                            ) { isSelected, id ->
                                Text(text = id.toString(), color = if (isSelected) Black else WhiteWithAlpha)
                            }
                        }
                    }
                }
            }
        }
    }
}
