package com.example.restaurantapplication.data

import com.example.restaurantapplication.model.Ingredient
import com.example.restaurantapplication.model.Recipe
import com.example.restaurantapplication.model.RecipeDetail


object FakeRecipesData {
    val recipes = listOf(
        Recipe(id = 1, title = "Stewed Chicken", diets = listOf("Main Dish","Meat"),image = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg"),
        Recipe(id = 2, title = "Stir-fried Beef", diets = listOf("Main Dish","Meat"),image = "https://www.themealdb.com/images/media/meals/1529444830.jpg"),
        Recipe(id = 3, title = "Classic Sushi", diets = listOf("Sushi"),image = "https://www.themealdb.com/images/media/meals/g046bb1663960946.jpg"),
        Recipe(id = 4, title = "Avocado Salad", diets = listOf("Salad", "Seafood"),image = "https://www.themealdb.com/images/media/meals/1549542994.jpg"),
        Recipe(id = 5, title = "Seafood Platter", diets = listOf("Main Dish","Seafood"),image = "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Recipe(id = 6, title = "Refreshing Lemon Drink", diets = listOf("Drink"),image = "https://picjumbo.com/wp-content/uploads/lemon-drink-free-photo-2210x1473.jpg") ,
        Recipe(id = 7, title = "Chocolate Lava Cake", diets = listOf("Dessert"),image = "https://www.themealdb.com/images/media/meals/tyywsw1505930373.jpg"),
        Recipe(id = 8, title = "Caesar Salad", diets = listOf("Salad"),image = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg"),
        Recipe(id = 9, title = "Latte", diets = listOf("Drink"),image = "https://recipesnobs.com/wp-content/uploads/2016/05/coffee-latte.jpg"),
        Recipe(id = 10, title = "Strawberries Romanoff", diets = listOf("Dessert"),image = "https://www.themealdb.com/images/media/meals/oe8rg51699014028.jpg")
    )

    val recipeDetails = mapOf(
        1 to RecipeDetail(
            id = 1,
            title = "Stewed Chicken",
            image = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
            summary = "Juicy grilled chicken served with seasonal vegetables.",
            pricePerServing = 15.99f,
            diets = listOf("Main Dish","Meat"),
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
            diets = listOf("Main Dish","Meat"),
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
            title = "Avocado Salad",
            image = "https://www.themealdb.com/images/media/meals/1549542994.jpg",
            summary = "A creamy and healthy avocado salad with fresh greens and lime dressing.",
            pricePerServing = 9.99f,
            diets = listOf("Salad", "Seafood"),
            extendedIngredients = listOf(
                Ingredient("Avocado"),
                Ingredient("Lettuce"),
                Ingredient("Cherry Tomatoes"),
                Ingredient("Lime Juice"),
                Ingredient("Olive Oil")
            )
        ),
        5 to RecipeDetail(
            id = 5,
            title = "Seafood Platter",
            image = "https://www.themealdb.com/images/media/meals/1548772327.jpg",
            summary = "A luxurious platter of fresh seafood including prawns, crab, and lobster.",
            pricePerServing = 35.00f,
            diets = listOf("Main Dish","Seafood"),
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
            image = "https://picjumbo.com/wp-content/uploads/lemon-drink-free-photo-2210x1473.jpg",
            summary = "A chilled lemon drink to refresh your senses.",
            pricePerServing = 4.50f,
            diets = listOf("Drink"),
            extendedIngredients = emptyList()

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
        ),
        8 to RecipeDetail(
            id = 8,
            title = "Caesar Salad",
            image = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
            summary = "A classic Caesar salad with crispy croutons and creamy dressing.",
            pricePerServing = 9.50f,
            diets = listOf("Salad"),
            extendedIngredients = listOf(
                Ingredient("Romaine Lettuce"), Ingredient("Croutons"), Ingredient("Parmesan"), Ingredient("Caesar Dressing")
            )
        ),
        9 to RecipeDetail(
            id = 9,
            title = "Latte",
            image = "https://recipesnobs.com/wp-content/uploads/2016/05/coffee-latte.jpg",
            summary = "Smooth and creamy coffee latte.",
            pricePerServing = 4.50f,
            diets = listOf("Drink"),
            extendedIngredients = emptyList()
        ),
        10 to RecipeDetail(
            id = 10,
            title = "Strawberries Romanoff",
            image = "https://www.themealdb.com/images/media/meals/oe8rg51699014028.jpg",
            summary = "A creamy cheesecake with a buttery biscuit base.",
            pricePerServing = 7.99f,
            diets = listOf("Dessert"),
            extendedIngredients = listOf(
                Ingredient("Strawberry"), Ingredient("Sugar"), Ingredient("Cream"), Ingredient("Sour Cream"), Ingredient("Grand Marnier")
            )
        )
    )
}
