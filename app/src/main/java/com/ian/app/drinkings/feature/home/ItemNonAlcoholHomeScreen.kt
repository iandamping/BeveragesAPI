package com.ian.app.drinkings.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ian.app.drinkings.R
import com.ian.app.drinkings.core.domain.model.NonAlcoholDrink
import com.ian.app.drinkings.ui.theme.MontserratFont

@Composable
fun ItemNonAlcoholHomeScreen(modifier: Modifier = Modifier, data: NonAlcoholDrink) {
    Card(modifier = modifier.size(size = 200.dp), elevation = CardDefaults.cardElevation(4.dp)) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .height(175.dp)
                    .fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current).data(data.strDrinkThumb)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder_image),
                error = painterResource(id = R.drawable.ic_no_data),
                contentDescription = data.strDrink
            )

            Text(
                text = data.strDrink ?: "No data available",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = MontserratFont,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
