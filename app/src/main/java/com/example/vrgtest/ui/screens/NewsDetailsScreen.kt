package com.example.vrgtest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.vrgtest.NewsTopAppBar
import com.example.vrgtest.R
import com.example.vrgtest.ui.navigation.NavigationDestination

object NewsDetailsDestination : NavigationDestination {
    override val route = "news_details"
    override val titleRes = R.string.news_details_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {}
){
    Scaffold(
        modifier = modifier,
        topBar = {
            NewsTopAppBar(
                title = stringResource(NewsDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) { }
    }
}