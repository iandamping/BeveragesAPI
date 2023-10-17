package com.ian.app.drinkings.feature.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ian.app.drinkings.ui.theme.BeveragesApiTheme
import com.ian.app.drinkings.viewmodel.HomeAlcoholViewModel
import com.ian.app.drinkings.viewmodel.HomeNonAlcoholViewModel
import com.ian.app.drinkings.viewmodel.HomeSearchViewModel
import com.ian.app.drinkings.ui.theme.MontserratFont

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeAlcoholViewModel: HomeAlcoholViewModel = hiltViewModel(),
    homeNonAlcoholViewModel: HomeNonAlcoholViewModel = hiltViewModel(),
    homeSearchViewModel: HomeSearchViewModel = hiltViewModel(),
    selectedBeverageId: (Int) -> Unit,
) {
    val homeAlcoholUiState by homeAlcoholViewModel.homeAlcoholUiState.collectAsState()
    val homeNonAlcoholUiState by homeNonAlcoholViewModel.homeNonAlcoholUiState.collectAsState()
    val searchBeverageUiState by homeSearchViewModel.uiSearchBeverageState.collectAsState()

    val listState = rememberLazyListState()

    LazyColumn(modifier = modifier, state = listState) {
        item {
            HomeBanner(
                userSearch = searchBeverageUiState,
                onBeverageSearch = homeSearchViewModel::searchBeverage
            )
        }

        item {
            Text(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                text = "Alcohol",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = MontserratFont,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )
            HomeAlcoholContent(modifier = Modifier.padding(8.dp), alcoholUiHomeState = homeAlcoholUiState)
        }
        item {
            Text(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                text = "Non Alcohol",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = MontserratFont,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )
            HomeNonAlcoholContent(modifier = Modifier.padding(8.dp), nonAlcoholUiHomeState = homeNonAlcoholUiState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    BeveragesApiTheme {
        HomeScreen(selectedBeverageId = {})
    }
}
