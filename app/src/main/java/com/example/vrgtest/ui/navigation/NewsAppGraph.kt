package com.example.vrgtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vrgtest.ui.screens.CategoryDestination
import com.example.vrgtest.ui.screens.CategoryScreen
import com.example.vrgtest.ui.screens.HomeDestination
import com.example.vrgtest.ui.screens.HomeScreen
import com.example.vrgtest.ui.screens.NewsDetailsDestination
import com.example.vrgtest.ui.screens.NewsDetailsScreen

@Composable
fun NewsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(

            )
        }
        composable(route = CategoryDestination.route) {
            CategoryScreen(

            )
        }
        composable(route = NewsDetailsDestination.route) {
            NewsDetailsScreen(

            )
        }
    }
}