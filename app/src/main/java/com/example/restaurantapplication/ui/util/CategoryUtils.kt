package com.example.restaurantapplication.ui.util

import com.example.restaurantapplication.data.model.DietCategory
import com.example.restaurantapplication.data.model.Recipe

fun Recipe.inCategory(cat: DietCategory): Boolean = when (cat) {
    DietCategory.MainDish -> diets.any { it.equals("Main Dish", ignoreCase = true) }
    DietCategory.Sushi    -> diets.any { it.equals("Sushi", ignoreCase = true) }
    DietCategory.Dessert  -> diets.any { it.equals("Dessert", ignoreCase = true) }
}

fun DietCategory.label(): String = when (this) {
    DietCategory.MainDish -> "Main Dish"
    DietCategory.Sushi    -> "Sushi"
    DietCategory.Dessert  -> "Dessert"
}
