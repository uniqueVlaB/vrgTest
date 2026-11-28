package com.example.vrgtest.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import kotlin.random.Random

object CategoryDestination : NavigationDestination {
    override val route = "category"
    override val titleRes = R.string.category_title
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    onNewsClick: (NewsItem) -> Unit = {},
    bottomBar: @Composable () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            NewsTopAppBar(
                title = stringResource(CategoryDestination.titleRes)
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
            CategorySelector()
            Spacer(Modifier.height(8.dp))
            NewsList(
                newsList = listOf(newsItem, newsItem, newsItem, newsItem),
                onItemClick = onNewsClick
            )
        }
    }
}

@Composable
fun CategorySelector() {
    // TODO: replace with viewModel
    var currentCategoryType by remember { mutableStateOf(CategoryType.General) }

    val categories = listOf(
        CategoryItem(
            type = CategoryType.General,
            imageRes = R.drawable.general_category
        ),
        CategoryItem(
            type = CategoryType.Business,
            imageRes = R.drawable.business_category
        ),
        CategoryItem(
            type = CategoryType.Sports,
            imageRes = R.drawable.sports_category
        ),
        CategoryItem(
            type = CategoryType.Entertainment,
            imageRes = R.drawable.entertainment_category
        ),
        CategoryItem(
            type = CategoryType.Health,
            imageRes = R.drawable.health_category
        ),
        CategoryItem(
            type = CategoryType.Science,
            imageRes = R.drawable.science_category
        ),
        CategoryItem(
            type = CategoryType.Technology,
            imageRes = R.drawable.technology_category
        )
    )

    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(2),
        horizontalItemSpacing = 8.dp,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.height(88.dp)
    ) {
        items(categories) { categoryItem ->
            CategorySelectItem(
                categoryItem = categoryItem,
                isSelected = currentCategoryType == categoryItem.type,
                onClick = { currentCategoryType = it }
            )
        }
    }
}

@Composable
fun CategorySelectItem(
    categoryItem: CategoryItem,
    isSelected: Boolean,
    onClick: (CategoryType) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = {onClick(categoryItem.type)}
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color =
                            if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                            }
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Image(
                painter = painterResource(categoryItem.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize().aspectRatio(1.3f)
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        color =
                            if (isSelected) {
                                if (isSystemInDarkTheme()) {
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                } else {
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                                }
                            } else {
                                MaterialTheme.colorScheme.scrim.copy(alpha = 0.3f)
                            }
                    )
            )
            Text(
                text = categoryItem.type.name,
                color = Color.White.copy(alpha = 0.9f),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun CategoryScreenPreview() {
    VrgTestTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        CategoryScreen { }
    }
}

// TODO: move to viewModel
data class CategoryItem(
    val type: CategoryType,
    @field:DrawableRes val imageRes: Int
)

enum class CategoryType {
    General,
    Business,
    Sports,
    Entertainment,
    Health,
    Science,
    Technology,

}