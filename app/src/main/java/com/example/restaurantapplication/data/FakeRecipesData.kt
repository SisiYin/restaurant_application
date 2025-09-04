package com.example.restaurantapplication.data

import com.example.restaurantapplication.model.Ingredient
import com.example.restaurantapplication.model.Recipe
import com.example.restaurantapplication.model.RecipeDetail


object FakeRecipesData {
    val recipes = listOf(
        Recipe(id = 1, title = "Stewed Chicken", diets = listOf("Main Dish","Meat"), image = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg"),
        Recipe(id = 2, title = "Stir-fried Beef", diets = listOf("Main Dish","Meat"), image = "https://www.themealdb.com/images/media/meals/1529444830.jpg"),
        Recipe(id = 3, title = "Classic Sushi", diets = listOf("Sushi"),image = "https://www.themealdb.com/images/media/meals/g046bb1663960946.jpg"),
        Recipe(id = 4, title = "Avocado Salad", diets = listOf("Salad", "Seafood"), image = "https://www.themealdb.com/images/media/meals/1549542994.jpg"),
        Recipe(id = 5, title = "Seafood Platter", diets = listOf("Main Dish","Seafood"), image = "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Recipe(id = 6, title = "Refreshing Lemon Drink", diets = listOf("Drink"), image = "https://picjumbo.com/wp-content/uploads/lemon-drink-free-photo-2210x1473.jpg") ,
        Recipe(id = 7, title = "Chocolate Lava Cake", diets = listOf("Dessert"), image = "https://www.themealdb.com/images/media/meals/tyywsw1505930373.jpg"),
        Recipe(id = 8, title = "Caesar Salad", diets = listOf("Salad"), image = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg"),
        Recipe(id = 9, title = "Latte", diets = listOf("Drink"), image = "https://recipesnobs.com/wp-content/uploads/2016/05/coffee-latte.jpg"),
        Recipe(id = 10, title = "Strawberries Romanoff", diets = listOf("Dessert"), image = "https://www.themealdb.com/images/media/meals/oe8rg51699014028.jpg"),
        Recipe(id = 101, title ="Salmon Nigiri", diets = listOf("Sushi"), image = "https://loremflickr.com/640/480/salmon,nigiri,sushi?lock=3101"),
        Recipe(id = 102, title ="Tuna Maki", diets = listOf("Sushi"), image = "https://loremflickr.com/640/480/tuna,maki,sushi?lock=3102"),
        Recipe(id = 103, title ="California Roll", diets = listOf("Sushi"), image = "https://loremflickr.com/640/480/california,roll,sushi?lock=3103"),
        Recipe(104, "Eel (Unagi) Roll", diets = listOf("Sushi"), image ="https://loremflickr.com/640/480/unagi,roll,sushi?lock=3104"),
        Recipe(105, "Shrimp Tempura Roll", diets = listOf("Sushi"), image ="https://loremflickr.com/640/480/shrimp,tempura,roll,sushi?lock=3105"),
        Recipe(301, "Mapo Tofu", diets = listOf("Main Dish"), image ="https://loremflickr.com/640/480/mapo,tofu?lock=3301"),
        Recipe(302, "Beef Noodle Soup", diets = listOf("Main Dish"), image ="https://loremflickr.com/640/480/beef,noodle,soup?lock=3302"),
        Recipe(303, "Sweet & Sour Pork", diets = listOf("Main Dish"), image ="https://loremflickr.com/640/480/sweet,sour,pork?lock=3303"),
        Recipe(304, "Kung Pao Chicken", diets = listOf("Main Dish","Meat"), image ="https://loremflickr.com/640/480/kungpao,chicken?lock=3304"),
        Recipe(305, "Grilled Salmon", diets = listOf("Main Dish","Meat"), image ="https://loremflickr.com/640/480/grilled,salmon?lock=3305"),
        Recipe(201, "Egg Tart", diets = listOf("Dessert"), image ="https://loremflickr.com/640/480/egg,tart?lock=3201"),
        Recipe(202, "Sweet Dumplings", diets = listOf("Dessert"), image ="https://loremflickr.com/640/480/sweet,dumpling,dessert?lock=3202"),
        Recipe(203, "Custard Bun", diets = listOf("Dessert"), image ="https://loremflickr.com/640/480/custard,bun?lock=3203"),
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
        ),
        101 to RecipeDetail(
            id = 101,
            title = "Salmon Nigiri",
            image = "https://loremflickr.com/640/480/salmon,nigiri,sushi?lock=3101",
            summary = "Classic Japanese nigiri with fresh salmon over seasoned rice.",
            pricePerServing = 10.50f,
            diets = listOf("Sushi"),
            extendedIngredients = listOf(
                Ingredient("Salmon"),
                Ingredient("Sushi Rice"),
                Ingredient("Nori"),
                Ingredient("Soy Sauce")
            )
        ),
        102 to RecipeDetail(
            id = 102,
            title = "Tuna Maki",
            image = "https://loremflickr.com/640/480/tuna,maki,sushi?lock=3102",
            summary = "Traditional tuna maki rolls wrapped in nori.",
            pricePerServing = 9.80f,
            diets = listOf("Sushi"),
            extendedIngredients = listOf(
                Ingredient("Tuna"),
                Ingredient("Sushi Rice"),
                Ingredient("Nori")
            )
        ),
        103 to RecipeDetail(
            id = 103,
            title = "California Roll",
            image = "https://loremflickr.com/640/480/california,roll,sushi?lock=3103",
            summary = "American-style sushi roll with crab stick, avocado, and cucumber.",
            pricePerServing = 11.20f,
            diets = listOf("Sushi"),
            extendedIngredients = listOf(
                Ingredient("Crab Stick"),
                Ingredient("Avocado"),
                Ingredient("Cucumber"),
                Ingredient("Sushi Rice"),
                Ingredient("Nori")
            )
        ),
        104 to RecipeDetail(
            id = 104,
            title = "Eel (Unagi) Roll",
            image = "https://loremflickr.com/640/480/unagi,roll,sushi?lock=3104",
            summary = "Sweet and savory grilled eel roll glazed with unagi sauce.",
            pricePerServing = 12.90f,
            diets = listOf("Sushi"),
            extendedIngredients = listOf(
                Ingredient("Eel"),
                Ingredient("Unagi Sauce"),
                Ingredient("Sushi Rice"),
                Ingredient("Nori")
            )
        ),
        105 to RecipeDetail(
            id = 105,
            title = "Shrimp Tempura Roll",
            image = "https://loremflickr.com/640/480/shrimp,tempura,roll,sushi?lock=3105",
            summary = "Crispy shrimp tempura rolled with rice and vegetables.",
            pricePerServing = 13.50f,
            diets = listOf("Sushi"),
            extendedIngredients = listOf(
                Ingredient("Shrimp Tempura"),
                Ingredient("Avocado"),
                Ingredient("Cucumber"),
                Ingredient("Sushi Rice"),
                Ingredient("Nori")
            )
        ),
        201 to RecipeDetail(
            id = 201,
            title = "Egg Tart",
            image = "https://loremflickr.com/640/480/egg,tart?lock=3201",
            summary = "Crispy pastry tart filled with silky egg custard.",
            pricePerServing = 4.20f,
            diets = listOf("Dessert"),
            extendedIngredients = listOf(
                Ingredient("Eggs"),
                Ingredient("Sugar"),
                Ingredient("Milk"),
                Ingredient("Pastry Dough")
            )
        ),
        202 to RecipeDetail(
            id = 202,
            title = "Sweet Dumplings",
            image = "https://loremflickr.com/640/480/sweet,dumpling,dessert?lock=3202",
            summary = "Glutinous rice dumplings filled with sweet sesame paste.",
            pricePerServing = 5.00f,
            diets = listOf("Dessert"),
            extendedIngredients = listOf(
                Ingredient("Glutinous Rice Flour"),
                Ingredient("Black Sesame Paste"),
                Ingredient("Sugar")
            )
        ),
        203 to RecipeDetail(
            id = 203,
            title = "Custard Bun",
            image = "https://loremflickr.com/640/480/custard,bun?lock=3203",
            summary = "Steamed buns filled with creamy custard filling.",
            pricePerServing = 4.80f,
            diets = listOf("Dessert"),
            extendedIngredients = listOf(
                Ingredient("Flour"),
                Ingredient("Eggs"),
                Ingredient("Milk"),
                Ingredient("Sugar")
            )
        ),
        301 to RecipeDetail(
            id = 301,
            title = "Mapo Tofu",
            image = "https://loremflickr.com/640/480/mapo,tofu?lock=3301",
            summary = "Spicy Sichuan-style tofu with minced pork and chili bean paste.",
            pricePerServing = 13.50f,
            diets = listOf("Main Dish"),
            extendedIngredients = listOf(
                Ingredient("Tofu"),
                Ingredient("Ground Pork"),
                Ingredient("Chili Bean Paste"),
                Ingredient("Garlic"),
                Ingredient("Sichuan Peppercorns")
            )
        ),
        302 to RecipeDetail(
            id = 302,
            title = "Beef Noodle Soup",
            image = "https://loremflickr.com/640/480/beef,noodle,soup?lock=3302",
            summary = "Slow-cooked beef brisket served with noodles in savory broth.",
            pricePerServing = 14.80f,
            diets = listOf("Main Dish"),
            extendedIngredients = listOf(
                Ingredient("Beef Brisket"),
                Ingredient("Noodles"),
                Ingredient("Beef Broth"),
                Ingredient("Scallions")
            )
        ),
        303 to RecipeDetail(
            id = 303,
            title = "Sweet & Sour Pork",
            image = "https://loremflickr.com/640/480/sweet,sour,pork?lock=3303",
            summary = "Crispy pork pieces coated in tangy sweet & sour sauce.",
            pricePerServing = 12.50f,
            diets = listOf("Main Dish"),
            extendedIngredients = listOf(
                Ingredient("Pork"),
                Ingredient("Pineapple"),
                Ingredient("Bell Pepper"),
                Ingredient("Sweet & Sour Sauce")
            )
        ),
        304 to RecipeDetail(
            id = 304,
            title = "Kung Pao Chicken",
            image = "https://loremflickr.com/640/480/kungpao,chicken?lock=3304",
            summary = "Spicy stir-fried chicken with peanuts, chili, and vegetables.",
            pricePerServing = 13.20f,
            diets = listOf("Main Dish","Meat"),
            extendedIngredients = listOf(
                Ingredient("Chicken"),
                Ingredient("Peanuts"),
                Ingredient("Dried Chili"),
                Ingredient("Soy Sauce"),
                Ingredient("Garlic")
            )
        ),
        305 to RecipeDetail(
            id = 305,
            title = "Grilled Salmon",
            image = "https://loremflickr.com/640/480/grilled,salmon?lock=3305",
            summary = "Salmon fillet grilled with lemon and herbs.",
            pricePerServing = 18.50f,
            diets = listOf("Main Dish","Seafood"),
            extendedIngredients = listOf(
                Ingredient("Salmon"),
                Ingredient("Lemon"),
                Ingredient("Olive Oil"),
                Ingredient("Rosemary")
            )
        ),
    )
}
