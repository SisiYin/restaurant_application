package com.example.restaurantapplication.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapplication.model.Recipe
import com.example.restaurantapplication.model.RecipeDetail
import kotlinx.coroutines.launch
import com.example.restaurantapplication.data.FakeRecipesData

class RecipesViewModel:  ViewModel()  {
    var allRecipes = mutableStateListOf<Recipe>()
        private set


    var recipeDetail = mutableStateOf<RecipeDetail?>(null)
        private set

    fun fetchAllRecipes() {
        viewModelScope.launch {
            try {
                allRecipes.clear()
                allRecipes.addAll(FakeRecipesData.recipes)
                Log.d("RecipesViewModel", "Successfully loaded fake recipes")
            } catch (e: Exception) {
                Log.e("RecipesViewModel", "Failed to load fake recipes: ${e.message}")
            }
        }
    }

    fun fetchRecipesByDiet(diet: String?) {
        viewModelScope.launch {
            try {
                allRecipes.clear()
                allRecipes.addAll(
                    FakeRecipesData.recipes.filter {
                        diet.isNullOrBlank() || it.diets.any { d -> d.equals(diet, ignoreCase = true) }
                    }
                )
                Log.d("RecipesViewModel", "Successfully load fake recipe list")
            } catch (e: Exception) {
                Log.d("ERROR", "Failed to load fake recipes by diet: ${e.message}")            }
        }
    }

    fun fetchRecipeDetail(recipeId: Int) {
        viewModelScope.launch {
            try {
                recipeDetail.value = FakeRecipesData.recipeDetails[recipeId]
                Log.d("RecipesViewModel", "Successfully loaded fake recipe detail")
            } catch (e: Exception) {
                Log.d("ERROR", "Failed to loaded fake recipe details: ${e.message}")
            }
        }
    }
}