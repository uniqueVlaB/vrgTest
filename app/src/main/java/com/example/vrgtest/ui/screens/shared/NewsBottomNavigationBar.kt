package com.example.vrgtest.ui.screens.shared

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vrgtest.ui.navigation.NavigationDestination
import com.example.vrgtest.ui.screens.CategoryDestination
import com.example.vrgtest.ui.screens.HomeDestination
import com.example.vrgtest.ui.theme.VrgTestTheme


data class NavigationItemContent(
    val icon: ImageVector,
    val title: String,
    val navigationDestination: NavigationDestination
)

@Composable
fun NewsBottomNavigationBar(
    currentRoute: String?,
    onNavigateToDestination: (NavigationDestination) -> Unit,
    modifier: Modifier = Modifier
){

    val navigationItemContentList = listOf(
        NavigationItemContent(
            icon = Icons.Filled.Home,
            title = "Home",
            navigationDestination = HomeDestination
        ),
        NavigationItemContent(
            icon = Icons.Filled.Category,
            title = "Category",
            navigationDestination = CategoryDestination
        )
    )
    NavigationBar(modifier = modifier.height(80.dp)) {
        for ((icon, title, navigationDestination) in navigationItemContentList) {
            NavigationBarItem(
                selected = currentRoute == navigationDestination.route,
                onClick = { onNavigateToDestination(navigationDestination) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = title
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun NewsBottomNavigationBarPreview() {
    VrgTestTheme {
        NewsBottomNavigationBar(
            currentRoute = HomeDestination.route,
            onNavigateToDestination = {}
        )
    }
}