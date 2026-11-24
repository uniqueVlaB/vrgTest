package com.example.vrgtest.ui.screens

import androidx.compose.runtime.Composable
import com.example.vrgtest.R
import com.example.vrgtest.ui.navigation.NavigationDestination

object NewsDetailsDestination : NavigationDestination {
    override val route = "news_details"
    override val titleRes = R.string.news_details_title
}

@Composable
fun NewsDetailsScreen(){

}