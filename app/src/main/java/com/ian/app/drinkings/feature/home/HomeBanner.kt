package com.ian.app.drinkings.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ian.app.drinkings.R
import com.ian.app.drinkings.ui.theme.BeveragesApiTheme

@Composable
fun HomeBanner(
    modifier: Modifier = Modifier,
    userSearch: String,
    onBeverageSearch: (String) -> Unit
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.img_beverages_banner),
            contentDescription = "banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )

        HomeSearch(userSearch = userSearch, onBeverageSearch = onBeverageSearch)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBannerContentPreview() {
    BeveragesApiTheme {
        HomeBanner(userSearch = "", onBeverageSearch = {})
    }
}
