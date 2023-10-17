package com.ian.app.drinkings.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HighlightOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ian.app.drinkings.R
import com.ian.app.drinkings.ui.theme.BeveragesApiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearch(
    modifier: Modifier = Modifier,
    userSearch: String,
    onBeverageSearch: (String) -> Unit
) {
    SearchBar(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp),
        query = userSearch,
        onQueryChange = { query ->
            onBeverageSearch.invoke(query)
        },
        onSearch = { search ->
            // invoke when click search on keyboard
            onBeverageSearch.invoke(search)
        },
        active = false,
        onActiveChange = { },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    onBeverageSearch.invoke("")
                },
                imageVector = Icons.Default.HighlightOff,
                contentDescription = stringResource(R.string.clear_search)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            CompositionLocalProvider(LocalContentColor provides LocalContentColor.current.copy(alpha = 0.4f)) {
                Text(
                    text = stringResource(R.string.search_beverage),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        },
        shape = MaterialTheme.shapes.large,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun HomeSearchContentPreview() {
    BeveragesApiTheme {
        HomeSearch(userSearch = "", onBeverageSearch = {})
    }
}
