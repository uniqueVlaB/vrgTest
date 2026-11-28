package com.example.vrgtest.ui.screens

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Launch
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.vrgtest.NewsTopAppBar
import com.example.vrgtest.R
import com.example.vrgtest.data.models.NewsItem
import com.example.vrgtest.data.models.NewsSource
import com.example.vrgtest.ui.navigation.NavigationDestination
import com.example.vrgtest.ui.theme.VrgTestTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object NewsDetailsDestination : NavigationDestination {
    override val route = "news_details"
    override val titleRes = R.string.news_details_title
}

@Composable
fun NewsDetailsScreen(
    newsItem: NewsItem?,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {}
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    Scaffold(
        modifier = modifier,
        topBar = {
            NewsTopAppBar(
                title = stringResource(NewsDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp,
                canLaunch = true,
                launch = {
                    newsItem?.let { nonNullNewsItem ->
                        openLink(nonNullNewsItem.url, uriHandler, context)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            if(newsItem != null) NewsBody(newsItem)
            else ErrorBody("News item param is null")
        }
    }
}

@Composable
fun NewsBody(newsItem: NewsItem, modifier: Modifier = Modifier) {
    Column(modifier) {
        NewsTitle(title = newsItem.title)
        NewsHeaderInfo(
            sourceName = newsItem.source.name,
            author = newsItem.author,
            publishedAt = newsItem.publishedAt
        )
        Spacer(Modifier.height(16.dp))
        NewsDescription(description = newsItem.description)
        NewsImage(imageUrl = newsItem.urlToImage)
        NewsContent(content = newsItem.content)
    }
}

@Composable
fun NewsTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier.padding(8.dp)
    )
}

@Composable
fun NewsHeaderInfo(
    sourceName: String,
    author: String,
    publishedAt: LocalDateTime,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = sourceName,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "by $author",
                style = MaterialTheme.typography.labelMedium
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
                text = "${publishedAt.date} ${publishedAt.time}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun NewsDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify,
        modifier = modifier.padding(horizontal = 8.dp)
    )
}

@Composable
fun NewsImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .fillMaxWidth()
            .height(196.dp)
            .padding(vertical = 8.dp),
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
}

@Composable
fun NewsContent(
    content: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = content,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Justify,
        modifier = modifier.padding(horizontal = 8.dp)
    )
}

@Composable
fun ErrorBody(reason: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Failed to load news details",
            color = Color.Red
        )
        Text(text = reason)
    }
}

@Preview
@Composable
fun NewsDetailsScreenPreview() {
    VrgTestTheme {
        val newsItem = NewsItem(
            source = NewsSource(
                id = "cnn",
                name = "CNN"
            ),
            author = "Christopher Lamb",
            title = "Pope Francis battled visions of the devil during a two-year spiritual crisis, diaries reveal - CNN",
            description = "Pope Francis suffered a spiritual crisis during his papacy in which he saw visions of the devil and hell while struggling with how to exercise power, newly published diaries of the late pontiff reveal.",
            url = "https://www.aljazeera.com/news/2026/11/28/israeli-strike-on-doctors-without-borders-clinic-in-gaza-kills-two-staff",
            urlToImage = "https://www.aljazeera.com/wp-content/uploads/2026/11/ap_6b04fd12611e1-1795489042.jpg?resize=1920%2C1440",
            publishedAt = Instant.parse("2026-11-24T06:02:04Z")
                .toLocalDateTime(TimeZone.currentSystemDefault()),
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ex commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        )
        NewsDetailsScreen(newsItem)
    }
}

private fun openLink(url: String, uriHandler: UriHandler, context: Context) {
    if (url.isNotEmpty()) {
        try {
            uriHandler.openUri(url)
        } catch (_: IllegalArgumentException) {
            Toast.makeText(context, "Invalid link", Toast.LENGTH_SHORT).show()
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(context, "No browser available", Toast.LENGTH_SHORT).show()
        }
    }
}