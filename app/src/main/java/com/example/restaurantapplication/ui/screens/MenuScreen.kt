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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import com.example.restaurantapplication.ui.components.DishMenuCard
import com.example.restaurantapplication.ui.components.SetMenuCard
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import com.example.restaurantapplication.viewmodel.SetMenusViewModel
import com.example.restaurantapplication.viewmodel.UserViewModel


@Composable
fun MenuScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    recipesViewModel: RecipesViewModel,
    userViewModel: UserViewModel,
    setMenusViewModel: SetMenusViewModel = viewModel()
) {
    val categories = listOf("Salad","Main Dish", "Sushi", "Dessert","Drink","Set Menus", "Seafood","Vegan","Pork","Beef","Chicken")
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchWord by remember { mutableStateOf("") }
    val favoriteIds by userViewModel.favoriteIds.collectAsState()


    // Fetch recipes
    LaunchedEffect(Unit) {
        recipesViewModel.fetchAllRecipes()
    }

    val recipes = recipesViewModel.allRecipes
    val filteredRecipes by remember(searchWord, selectedCategory) {
        derivedStateOf {
            val normCat = selectedCategory?.trim()?.lowercase()
            recipes
                .filter { it.title.contains(searchWord, ignoreCase = true) }
                .filter { r ->
                    when {
                        normCat == null -> true
                        normCat == "set menus" -> false
                        else -> r.diets.any { it.trim().lowercase() == normCat }
                    }
                }
        }
    }

    Log.d("RecipeScreen", "Rendering RecipeScreen")
    // Layout
    Column(modifier = modifier.padding(16.dp)) {

        OutlinedTextField(
            value = searchWord,
            onValueChange = { searchWord = it },
            placeholder = { Text("Search Dish") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxSize()) {
            // 左侧分类列表
            LazyColumn(
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight()
                    .padding(start = 16.dp, top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item{
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
                        .weight(1f),
//                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    items(filteredRecipes) { recipe ->
                        DishMenuCard(recipe = recipe, navController = navController, recipesViewModel = recipesViewModel, userViewModel = userViewModel,isFavorite = recipe.id in favoriteIds,
                            onToggleFavorite = { userViewModel.toggleFavorite(recipe.id) },
                            onClick = {
                                recipesViewModel.getById(recipe.id)
                                navController.navigate("recipes/${recipe.id}")
                            })
                    }
                }
            }

        }
    }
}