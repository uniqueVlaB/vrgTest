package com.example.vrgtest.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vrgtest.NewsTopAppBar
import com.example.vrgtest.R
import com.example.vrgtest.data.models.NewsItem
import com.example.vrgtest.data.models.NewsSource
import com.example.vrgtest.ui.navigation.NavigationDestination
import com.example.vrgtest.ui.screens.shared.NewsList
import com.example.vrgtest.ui.theme.VrgTestTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNewsClick: (NewsItem) -> Unit = {},
    bottomBar: @Composable () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            NewsTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false
            )
        },
        bottomBar = bottomBar
    ) { innerPadding ->
        val newsItem = NewsItem(
            source = NewsSource(
                id = "cnn",
                name = "CNN"
            ),
            author = "Christopher Lamb",
            title = "Pope Francis battled visions of the devil during a two-year spiritual crisis, diaries reveal - CNN",
            description = "Pope Francis suffered a spiritual crisis during his papacy in which he saw visions of the devil and hell while struggling with how to exercise power, newly published diaries of the late pontiff reveal.",
            url = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ex commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            urlToImage = "https://www.aljazeera.com/wp-content/uploads/2026/11/ap_6b04fd12611e1-1795489042.jpg?resize=1920%2C1440",
            publishedAt = Instant.parse("2026-11-24T06:02:04Z").toLocalDateTime(TimeZone.currentSystemDefault()),
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ex commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        )
        Column(Modifier.padding(innerPadding)) {
            SearchBar()
            NewsList(
                newsList = listOf(newsItem, newsItem, newsItem, newsItem)
            )
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Box(
        modifier.padding(horizontal = 8.dp).padding(bottom = 8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier.size(28.dp))
            OutlinedTextField(
                value = "uiState.query",
                onValueChange = {"viewModel.updateQuery(it)"},
                singleLine = true,
                trailingIcon = {
                    if ("uiState.query".isNotEmpty()) {
                        IconButton(onClick = {"viewModel.resetQuery"}) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_9_pro")
@Composable
fun HomeScreenPreview(){
    VrgTestTheme() {
       HomeScreen()
    }
}