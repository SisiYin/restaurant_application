package com.example.restaurantapplication.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.restaurantapplication.ui.components.FilterChip
import com.example.restaurantapplication.ui.components.RecipeItem
import com.example.restaurantapplication.ui.components.RecipeList
import com.example.restaurantapplication.ui.components.SetMenuCard
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import com.example.restaurantapplication.viewmodel.SetMenusViewModel


@Composable
fun MenuScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    recipesViewModel: RecipesViewModel,
    //userViewModel: UserViewModel
    setMenusViewModel: SetMenusViewModel = viewModel()
) {
    val categories = listOf("Salad","Main Dish", "Sushi", "Dessert","Drink", "Set Menus")
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchWord by remember { mutableStateOf("") }

    // Fetch recipes
    LaunchedEffect(Unit) {
        recipesViewModel.fetchAllRecipes()
    }

    LaunchedEffect(selectedCategory) {
        if (selectedCategory != null) {
            recipesViewModel.fetchRecipesByDiet(selectedCategory)
        }
    }


    val filteredRecipes = recipesViewModel.allRecipes.filter {
        it.title.contains(searchWord, ignoreCase = true)
    }
    Log.d("RecipeScreen", "Rendering RecipeScreen")
    // Layout
    Column(modifier = modifier.padding(16.dp)) {

        OutlinedTextField(
            value = searchWord,
            onValueChange = { searchWord = it },
            placeholder = { Text("Search Recipe") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxSize()) {
            // 左侧分类列表
            Column(
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight()
                    .padding(start = 16.dp, top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                categories.forEach { category ->
                    Text(
                        text = category,
                        modifier = Modifier
                            .clickable { selectedCategory = category }
                            .padding(vertical = 8.dp),
                        style = if (category == selectedCategory) {
                            MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6200EE)
                            )
                        } else {
                            MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                }
            }


            // 右侧菜品列表：当选中 "Set Menus" → 显示 A/B 套餐；否则显示菜品
            if (selectedCategory == "Set Menus") {
                // 套餐列表页
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(setMenusViewModel.sets, key = { it.id }) { set ->
                        SetMenuCard(
                            set = set,
                            navController = navController,
                            setMenusViewModel = setMenusViewModel,
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    items(filteredRecipes) { recipe ->
                        RecipeItem(recipe = recipe, navController = navController, recipesViewModel = recipesViewModel)
                    }
                }
            }

        }
    }
}