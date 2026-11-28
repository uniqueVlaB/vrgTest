package com.example.vrgtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vrgtest.data.models.NewsItem
import com.example.vrgtest.ui.screens.CategoryDestination
import com.example.vrgtest.ui.screens.CategoryScreen
import com.example.vrgtest.ui.screens.HomeDestination
import com.example.vrgtest.ui.screens.HomeScreen
import com.example.vrgtest.ui.screens.NewsDetailsDestination
import com.example.vrgtest.ui.screens.NewsDetailsScreen
import com.example.vrgtest.ui.screens.shared.NewsBottomNavigationBar

@Composable
fun NewsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // TODO: replace with viewModel 
    var selectedNewsItem by remember { mutableStateOf<NewsItem?>(null) }

    val onNavigateToDestination: (NavigationDestination) -> Unit = { destination ->
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                bottomBar = {
                    NewsBottomNavigationBar(
                        currentRoute = currentRoute,
                        onNavigateToDestination = onNavigateToDestination
                    )
                },
                onNewsClick = { newsItem ->
                    selectedNewsItem = newsItem
                    navController.navigate(NewsDetailsDestination.route)
                }
            )
        }
        composable(route = CategoryDestination.route) {
            CategoryScreen(
                bottomBar = {
                    NewsBottomNavigationBar(
                        currentRoute = currentRoute,
                        onNavigateToDestination = onNavigateToDestination
                    )
                },
                onNewsClick = { newsItem ->
                    selectedNewsItem = newsItem
                    navController.navigate(NewsDetailsDestination.route)
                }
            )
        }
        composable(route = NewsDetailsDestination.route) {
            NewsDetailsScreen(
                newsItem = selectedNewsItem,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}