package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantapplication.model.Recipe
import com.example.restaurantapplication.viewmodel.RecipesViewModel


@Composable
fun RecipeSection (
    title: String,
    recipes: List<Recipe>,
    navController: NavController,
    recipesViewModel: RecipesViewModel
){
    Column{
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recipes) { recipe ->
                RecipeItem(recipe, navController, recipesViewModel)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}