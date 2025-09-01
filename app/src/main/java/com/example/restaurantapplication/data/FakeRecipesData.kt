package com.example.restaurantapplication.data

import com.example.restaurantapplication.model.Ingredient
import com.example.restaurantapplication.model.Recipe
import com.example.restaurantapplication.model.RecipeDetail


object FakeRecipesData {
    val recipes = listOf(
        Recipe(id = 1, title = "Stewed Chicken", image = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg"),
        Recipe(id = 2, title = "Stir-fried Beef", image = "https://www.themealdb.com/images/media/meals/1529444830.jpg"),
        Recipe(id = 3, title = "Classic Sushi", image = "https://www.themealdb.com/images/media/meals/g046bb1663960946.jpg"),
        Recipe(id = 4, title = "Fresh Garden Salad", image = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg"),
        Recipe(id = 5, title = "Seafood Platter", image = "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Recipe(id = 6, title = "Refreshing Lemon Drink", image = "https://www.themealdb.com/images/media/meals/ysxwuq1487323065.jpg") ,
        Recipe(id = 7, title = "Chocolate Lava Cake", image = "https://www.themealdb.com/images/media/meals/tyywsw1505930373.jpg")
    )

    val recipeDetails = mapOf(
        1 to RecipeDetail(
            id = 1,
            title = "Stewed Chicken",
            image = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
            summary = "Juicy grilled chicken served with seasonal vegetables.",
            pricePerServing = 15.99f,
            diets = listOf("Meat"),
            extendedIngredients = listOf(
                Ingredient("Chicken Breast"),
                Ingredient("Olive Oil"),
                Ingredient("Garlic"),
                Ingredient("Salt"),
                Ingredient("Pepper")
            )
        ),
        2 to RecipeDetail(
            id = 2,
            title = "Stir-fried Beef",
            image = "https://www.themealdb.com/images/media/meals/1529444830.jpg",
            summary = "Tender beef steak cooked to perfection with a side of mashed potatoes.",
            pricePerServing = 24.99f,
            diets = listOf("Meat"),
            extendedIngredients = listOf(
                Ingredient("Beef Steak"),
                Ingredient("Butter"),
                Ingredient("Garlic"),
                Ingredient("Rosemary")
            )
        ),
        3 to RecipeDetail(
            id = 3,
            title = "Classic Sushi",
            image = "https://www.themealdb.com/images/media/meals/g046bb1663960946.jpg",
            summary = "Traditional Japanese sushi rolls with fresh fish and rice.",
            pricePerServing = 12.50f,
            diets = listOf("Sushi"),
            extendedIngredients = listOf(
                Ingredient("Rice"),
                Ingredient("Nori"),
                Ingredient("Salmon"),
                Ingredient("Tuna"),
                Ingredient("Avocado")
            )
        ),
        4 to RecipeDetail(
            id = 4,
            title = "Fresh Garden Salad",
            image = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
            summary = "A light and refreshing garden salad with a tangy dressing.",
            pricePerServing = 8.99f,
            diets = listOf("Salad"),
            extendedIngredients = listOf(
                Ingredient("Lettuce"),
                Ingredient("Tomato"),
                Ingredient("Cucumber"),
                Ingredient("Olive Oil"),
                Ingredient("Lemon Juice")
            )
        ),
        5 to RecipeDetail(
            id = 5,
            title = "Seafood Platter",
            image = "https://www.themealdb.com/images/media/meals/1548772327.jpg",
            summary = "A luxurious platter of fresh seafood including prawns, crab, and lobster.",
            pricePerServing = 35.00f,
            diets = listOf("Seafood"),
            extendedIngredients = listOf(
                Ingredient("Prawns"),
                Ingredient("Crab"),
                Ingredient("Lobster"),
                Ingredient("Butter Sauce")
            )
        ),
        6 to RecipeDetail(
            id = 6,
            title = "Refreshing Lemon Drink",
            image = "https://www.themealdb.com/images/media/meals/ysxwuq1487323065.jpg",
            summary = "A chilled lemon drink to refresh your senses.",
            pricePerServing = 4.50f,
            diets = listOf("Drink"),
            extendedIngredients = listOf(
                Ingredient("Lemon"),
                Ingredient("Sugar"),
                Ingredient("Water"),
                Ingredient("Ice")
            )
        ),
        7 to RecipeDetail(
            id = 7,
            title = "Chocolate Lava Cake",
            image = "https://www.themealdb.com/images/media/meals/tyywsw1505930373.jpg",
            summary = "A rich and gooey chocolate dessert with a molten center.",
            pricePerServing = 6.99f,
            diets = listOf("Dessert"),
            extendedIngredients = listOf(
                Ingredient("Chocolate"),
                Ingredient("Butter"),
                Ingredient("Sugar"),
                Ingredient("Eggs"),
                Ingredient("Flour")
            )
        )
    )
}
