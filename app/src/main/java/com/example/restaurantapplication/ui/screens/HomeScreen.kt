package com.example.restaurantapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    recipesViewModel: RecipesViewModel,
){
    val categories = listOf("Meat","Vegetarian","Seafood","Salad","Dessert","Drink")
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchWord by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        recipesViewModel.fetchAllRecipes()
    }

    val recipes = recipesViewModel.allRecipes
    println("DEBUG: Recipes count = ${recipes.size}")


    // Layout
//    Column(modifier = modifier.padding(16.dp)) {
//
//        OutlinedTextField(
//            value = searchWord,
//            onValueChange = { searchWord = it },
//            placeholder = { Text("Search Recipe") },
//            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        FlowRow(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly,
//        ) {
//            categories.forEach { category ->
//                FilterChip(
//                    category = category,
//                    selected = selectedCategory == category, // Check if the category is selected
//                    onClick = {
//                        selectedCategory = if (selectedCategory == category) null.toString() else category // Toggle category selection
//                    }
//                )
//            }
//        }
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//        ) {
//            Text("Restaurant Video Placeholder")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        RecipeSection("Newest", recipes, navController, recipesViewModel)
//        RecipeSection("Popular", recipes, navController, recipesViewModel)
//        RecipeSection("Recommended", recipes, navController, recipesViewModel)
//    }

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
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->
                    FilterChip(
                        category = category,
                        selected = selectedCategory == category,
                        onClick = {
                            selectedCategory =
                                if (selectedCategory == category) null else category
                        }
                    )
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Text("Restaurant Video Placeholder")
            }
        }

        item {
            RecipeSection("Newest", recipes, navController, recipesViewModel)
        }

        item {
            RecipeSection("Popular", recipes.take(3), navController, recipesViewModel)
        }

        item {
            RecipeSection("Recommended", recipes.take(3), navController, recipesViewModel)
        }
    }
}