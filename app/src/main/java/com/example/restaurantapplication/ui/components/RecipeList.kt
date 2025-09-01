package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.restaurantapplication.model.Recipe
import com.example.restaurantapplication.viewmodel.RecipesViewModel

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    navController: NavController,
    recipesViewModel: RecipesViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(recipes) { recipe ->
            RecipeItem(recipe,navController,recipesViewModel)
        }
    }
}