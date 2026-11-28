package com.example.vrgtest.ui.screens.shared

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.vrgtest.R
import com.example.vrgtest.data.models.NewsItem
import com.example.vrgtest.data.models.NewsSource
import com.example.vrgtest.ui.theme.VrgTestTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.toLocalDateTime

@Composable
fun NewsList(
    newsList: List<NewsItem>,
    onItemClick: (NewsItem) -> Unit,
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier
            .padding(horizontal = 8.dp)
    ){
        items(newsList){ newsItem ->
            NewsCard(
                newsItem,
                onClick = {onItemClick(newsItem)},
                Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun NewsCard(
    newsItem: NewsItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Card(
        onClick = onClick,
        modifier
    ) {
        Column() {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(newsItem.urlToImage)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Image(
                        painter = painterResource(R.drawable.image_not_available),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f)) {
                    Text(
                        text = newsItem.source.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                    )
                    Text(
                        text = "by " + newsItem.author,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                    )
                }
                Spacer(Modifier.width(8.dp))
                Row {
                    Icon(
                        imageVector = Icons.Filled.History,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "${newsItem.publishedAt.date} ${newsItem.publishedAt.time}",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
        Text(
            text = newsItem.title,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun NewsListPreview() {
    VrgTestTheme() {
        val newsItem = NewsItem(
            source = NewsSource(
                id = "cnn",
                name = "CNN"
            ),
            author = "Christopher Lamb",
            title = "Pope Francis battled visions of the devil during a two-year spiritual crisis, diaries reveal - CNN",
            description = "Pope Francis suffered a spiritual crisis during his papacy in which he saw visions of the devil and hell while struggling with how to exercise power, newly published diaries of the late pontiff reveal.",
            url = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            urlToImage =  "https://media.cnn.com/api/v1/images/stellar/prod/230607175730-07-pope-francis-lead-2013.jpg?c=16x9&q=w_800,c_fill",
            publishedAt = Instant.parse("2026-11-24T06:02:04Z").toLocalDateTime(TimeZone.currentSystemDefault()),
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        )
        NewsList(
            listOf(newsItem, newsItem, newsItem, newsItem,),
            onItemClick = {}
        )
    }
}

