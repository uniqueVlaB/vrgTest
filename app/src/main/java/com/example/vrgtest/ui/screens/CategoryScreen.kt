package com.example.vrgtest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.vrgtest.NewsTopAppBar
import com.example.vrgtest.R
import com.example.vrgtest.ui.navigation.NavigationDestination

object CategoryDestination : NavigationDestination {
    override val route = "category"
    override val titleRes = R.string.category_title
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    bottomBar: @Composable () -> Unit = {}
){
    Scaffold(
        modifier = modifier,
        topBar = {
            NewsTopAppBar(
                title = stringResource(CategoryDestination.titleRes),
                canNavigateBack = false
            )
        },
        bottomBar = bottomBar
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) { }
    }
}