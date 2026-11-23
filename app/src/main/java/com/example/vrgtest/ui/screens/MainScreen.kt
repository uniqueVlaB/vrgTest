package com.example.vrgtest.ui.screens

import androidx.compose.runtime.Composable
import com.example.vrgtest.R
import com.example.vrgtest.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen(){

}