package com.example.vrgtest.ui.screens

import androidx.compose.runtime.Composable
import com.example.vrgtest.R
import com.example.vrgtest.ui.navigation.NavigationDestination

object CategoryDestination : NavigationDestination {
    override val route = "category"
    override val titleRes = R.string.category_title
}

@Composable
fun CategoryScreen(){

}