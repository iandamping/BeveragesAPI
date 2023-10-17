package com.ian.app.drinkings.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ian.app.drinkings.core.domain.model.AlcoholDrink
import com.ian.app.drinkings.feature.shimmer.ShimmerAnimation
import com.ian.app.drinkings.state.UiHomeState

@Composable
fun HomeAlcoholContent(
    modifier: Modifier = Modifier,
    alcoholUiHomeState: UiHomeState<List<AlcoholDrink>>
) {
    val state = rememberLazyListState()
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        flingBehavior = rememberSnapFlingBehavior(
            lazyListState = state
        )
    ) {
        when (alcoholUiHomeState) {
            is UiHomeState.Error -> item { Text(text = "Error : ${alcoholUiHomeState.errorMessage}") }
            UiHomeState.Loading -> items(6) {
                ShimmerAnimation { brush ->
                    Spacer(
                        modifier = Modifier
                            .clip(RoundedCornerShape(percent = 10))
                            .size(200.dp)
                            .background(brush = brush),
                    )
                }
            }

            is UiHomeState.Success -> items(alcoholUiHomeState.data) { singleItem ->
                ItemAlcoholHomeScreen(data = singleItem)
            }
        }
    }
}