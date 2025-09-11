package com.example.restaurantapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantapplication.ui.components.FilterChip
import com.example.restaurantapplication.ui.components.RecipeSection
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import com.example.restaurantapplication.R
import com.example.restaurantapplication.ui.components.LocalVideoPlayer


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    recipesViewModel: RecipesViewModel,
){
    var searchWord by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        recipesViewModel.fetchAllRecipes()
    }

    val recipes = recipesViewModel.allRecipes
    println("DEBUG: Recipes count = ${recipes.size}")

    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = searchWord,
                onValueChange = { searchWord = it },
                placeholder = { Text("Search Recipe") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            LocalVideoPlayer(
                rawResId = R.raw.restaurant_intro,        // 你的文件名 restaurant.mp4
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),                // 跟你原来保持一致
                autoPlay = true,
                loop = true,
                mute = true,                        // 餐厅 App 常见静音循环
                useController = false               // 不显示控制条
            )
        }

        item {
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { navController.navigate("menu") },
                    modifier = Modifier.weight(1f)
                ) { Text("View full menu") }

                OutlinedButton(
                    onClick = { navController.navigate("menu") /* 或 set menus 列表 */ },
                    modifier = Modifier.weight(1f)
                ) { Text("Set menus") }
            }
        }

        item {
            RecipeSection("Newest", recipes.take(4), navController, recipesViewModel)
        }

        item {
            RecipeSection("Popular", recipes.shuffled().take(6), navController, recipesViewModel)
        }



    }
}