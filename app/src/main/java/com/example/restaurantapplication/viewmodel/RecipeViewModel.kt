package com.example.restaurantapplication.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapplication.data.model.FsDish
import com.example.restaurantapplication.data.model.Recipe
import com.example.restaurantapplication.data.model.RecipeDetail
import com.example.restaurantapplication.data.model.toRecipe
import com.example.restaurantapplication.data.model.toRecipeDetail
import kotlinx.coroutines.launch
import com.google.firebase.ktx.Firebase          // 选这个（ktx 版）
import com.google.firebase.firestore.ktx.firestore
import kotlinx.coroutines.tasks.await


class RecipesViewModel:  ViewModel()  {
    var allRecipes = mutableStateListOf<Recipe>()
        private set


    var recipeDetail = mutableStateOf<RecipeDetail?>(null)
        private set

//    fun fetchAllRecipes() {
//        viewModelScope.launch {
//            try {
//                allRecipes.clear()
//                allRecipes.addAll(FakeRecipesDataSource.recipes)
//                Log.d("RecipesViewModel", "Successfully loaded fake recipes")
//            } catch (e: Exception) {
//                Log.e("RecipesViewModel", "Failed to load fake recipes: ${e.message}")
//            }
//        }
//    }
//
//    fun fetchRecipesByDiet(diet: String?) {
//        viewModelScope.launch {
//            try {
//                allRecipes.clear()
//                allRecipes.addAll(
//                    FakeRecipesDataSource.recipes.filter {
//                        diet.isNullOrBlank() || it.diets.any { d -> d.equals(diet, ignoreCase = true) }
//                    }
//                )
//                Log.d("RecipesViewModel", "Successfully load fake recipe list")
//            } catch (e: Exception) {
//                Log.d("ERROR", "Failed to load fake recipes by diet: ${e.message}")            }
//        }
//    }
//
//    fun fetchRecipeDetail(recipeId: Int) {
//        viewModelScope.launch {
//            try {
//                recipeDetail.value = FakeRecipesDataSource.recipeDetails[recipeId]
//                Log.d("RecipesViewModel", "Successfully loaded fake recipe detail")
//            } catch (e: Exception) {
//                Log.d("ERROR", "Failed to loaded fake recipe details: ${e.message}")
//            }
//        }
//    }

    private val dishesCol = Firebase.firestore
        .collection("restaurant")           // 单数
        .document("okiniiri")
        .collection("dishes")

    /** 拉全量（按标题排序） */
    fun fetchAllRecipes() {
        viewModelScope.launch {
            try {
                val snap = dishesCol.orderBy("title").get().await()
                val list = snap.documents.mapNotNull { it.toObject(FsDish::class.java)?.toRecipe() }
                allRecipes.clear()
                allRecipes.addAll(list)
                Log.d("RecipesViewModel", "Loaded ${list.size} recipes from Firestore")
            } catch (e: Exception) {
                Log.e("RecipesViewModel", "Failed to load recipes: ${e.message}", e)
            }
        }
    }

    /** 按分类过滤（用 whereArrayContains 直接在服务端过滤） */
    fun fetchRecipesByDiet(diet: String?) {
        viewModelScope.launch {
            try {
                if (diet.isNullOrBlank() || diet == "Set Menus") {
                    fetchAllRecipes()
                    return@launch
                }
                val q = dishesCol.whereArrayContains("diets", diet)
                val snap = q.get().await()
                val list = snap.documents.mapNotNull { it.toObject(FsDish::class.java)?.toRecipe() }
                allRecipes.clear()
                allRecipes.addAll(list)
                Log.d("RecipesViewModel", "Loaded ${list.size} recipes for diet=$diet")
            } catch (e: Exception) {
                Log.e("RecipesViewModel", "Failed to load recipes by diet: ${e.message}", e)
            }
        }
    }

    /** 读取详情（文档 ID 就是数字字符串，如 "101"） */
    fun fetchRecipeDetail(recipeId: Int) {
        viewModelScope.launch {
            try {
                val doc = dishesCol.document(recipeId.toString()).get().await()
                val fs = doc.toObject(FsDish::class.java)
                recipeDetail.value = fs?.toRecipeDetail()
                Log.d("RecipesViewModel", "Loaded detail for $recipeId")
            } catch (e: Exception) {
                Log.e("RecipesViewModel", "Failed to load detail: ${e.message}", e)
            }
        }
    }
}