package com.example.restaurantapplication.data.source

import com.example.restaurantapplication.data.model.Ingredient
import com.example.restaurantapplication.data.model.Recipe
import com.example.restaurantapplication.data.model.RecipeDetail


object FakeRecipesDataSource {
    val recipes = listOf(
        Recipe(id = 1, title = "Stewed Chicken", diets = listOf("Main Dish","Meat"), image = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg"),
        Recipe(id = 2, title = "Stir-fried Beef", diets = listOf("Main Dish","Meat"), image = "https://www.themealdb.com/images/media/meals/1529444830.jpg"),
        Recipe(id = 3, title = "Classic Sushi", diets = listOf("Sushi"),image = "https://www.themealdb.com/images/media/meals/g046bb1663960946.jpg"),
        Recipe(id = 4, title = "Avocado Salad", diets = listOf("Salad", "Seafood"), image = "https://www.themealdb.com/images/media/meals/1549542994.jpg"),
        Recipe(id = 5, title = "Seafood Platter", diets = listOf("Main Dish","Seafood"), image = "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Recipe(id = 6, title = "Refreshing Lemon Drink", diets = listOf("Drink"), image = "https://picjumbo.com/wp-content/uploads/lemon-drink-free-photo-2210x1473.jpg") ,
        Recipe(id = 7, title = "Chocolate Lava Cake", diets = listOf("Dessert"), image = "https://www.themealdb.com/images/media/meals/tyywsw1505930373.jpg"),
        Recipe(id = 8, title = "Caesar Salad", diets = listOf("Salad"), image = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg"),
        Recipe(id = 9, title = "Coffee", diets = listOf("Drink"), image = "https://recipesnobs.com/wp-content/uploads/2016/05/coffee-latte.jpg"),
        Recipe(id = 11, title = "Tea", diets = listOf("Drink"), image = "https://i.pinimg.com/originals/b5/f6/f1/b5f6f103524c4ef74a277bd726371e4e.jpg"),
        Recipe(id = 10, title = "Strawberries Romanoff", diets = listOf("Dessert"), image = "https://www.themealdb.com/images/media/meals/oe8rg51699014028.jpg"),
        Recipe(id = 12, title = "Garden Salad", diets = listOf("Salad", "Vegan"), image = "https://hellolittlehome.com/wp-content/uploads/2022/08/garden-salad-recipe-2.jpg"),
        Recipe(id = 13, title = "Fruit Salad", diets = listOf("Salad", "Vegan"), image = "https://img.freepik.com/premium-photo/bowl-full-with-mixed-berries-fruits-salad_787273-4882.jpg"),
        Recipe(id = 101, title ="Salmon Nigiri", diets = listOf("Sushi"), image = "https://loremflickr.com/640/480/salmon,nigiri,sushi?lock=3101"),
        Recipe(id = 102, title ="Tuna Maki", diets = listOf("Sushi"), image = "https://loremflickr.com/640/480/tuna,maki,sushi?lock=3102"),
        Recipe(id = 103, title ="California Roll", diets = listOf("Sushi"), image = "https://loremflickr.com/640/480/california,roll,sushi?lock=3103"),
        Recipe(104, "Eel (Unagi) Roll", diets = listOf("Sushi"), image ="https://loremflickr.com/640/480/unagi,roll,sushi?lock=3104"),
        Recipe(105, "Shrimp Tempura Roll", diets = listOf("Sushi"), image ="https://loremflickr.com/640/480/shrimp,tempura,roll,sushi?lock=3105"),
        Recipe(301, "Mapo Tofu", diets = listOf("Main Dish"), image ="https://norecipes.com/wp-content/uploads/2018/02/mapo-tofu-recipe-011.jpg"),
        Recipe(302, "Beef Noodle Soup", diets = listOf("Main Dish"), image ="https://poshjournal.com/wp-content/uploads/2021/05/taiwanese-beef-noodle-soup-recipe.jpg"),
        Recipe(303, "Sweet & Sour Pork", diets = listOf("Main Dish"), image ="https://www.coolinarco.com/wp-content/uploads/2023/09/ds0887_Sweet_and_Sour_Pork_745ad2e8-115b-4306-9fb5-46ac3690c0a3.webp"),
        Recipe(304, "Kung Pao Chicken", diets = listOf("Main Dish","Meat"), image ="https://www.cookingclassy.com/wp-content/uploads/2020/02/kung-pao-chicken-1-1024x1536.jpg"),
        Recipe(305, "Grilled Salmon", diets = listOf("Main Dish","Meat"), image ="https://www.feastingathome.com/wp-content/uploads/2022/08/Grilled-Salmon-10.jpg"),
        Recipe(201, "Glazed Donut", diets = listOf("Dessert"), image ="https://doughnutlounge.com/wp-content/uploads/glazed-donut-recipe.jpeg"),
        Recipe(202, "Har Gow", diets = listOf("Dessert","Seafood"), image ="https://pic.nximg.cn/file/20190113/21251565_134420422084_2.jpg"),
        Recipe(203, "Xiaolongbao", diets = listOf("Dessert"), image ="https://img.freepik.com/premium-photo/xiaolongbao-bamboo-tray_447653-9778.jpg"),
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
            title = "Coffee",
            image = "https://recipesnobs.com/wp-content/uploads/2016/05/coffee-latte.jpg",
            summary = "Smooth and creamy coffee.",
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
        11 to RecipeDetail(
            id = 9,
            title = "Tea",
            image = "https://i.pinimg.com/originals/b5/f6/f1/b5f6f103524c4ef74a277bd726371e4e.jpg",
            summary = "Light and refreshing hot tea.",
            pricePerServing = 4.50f,
            diets = listOf("Drink"),
            extendedIngredients = emptyList()
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
            title = "Glazed Donut",
            image = "https://doughnutlounge.com/wp-content/uploads/glazed-donut-recipe.jpeg",
            summary = "Classic ring doughnut with vanilla glaze.",
            pricePerServing = 3.49f,
            diets = listOf("Dessert"),
            extendedIngredients = listOf(
                Ingredient("All-purpose Flour"),
                Ingredient("Granulated Sugar"),
                Ingredient("Whole Milk"),
                Ingredient("Egg"),
                Ingredient("Unsalted Butter"),
            )
        ),
        202 to RecipeDetail(
            id = 202,
            title = "Har Gow",
            image = "https://pic.nximg.cn/file/20190113/21251565_134420422084_2.jpg",
            summary = "Cantonese shrimp dumplings with translucent wrappers.",
            pricePerServing = 5.00f,
            diets = listOf("Dessert"),
            extendedIngredients = listOf(
                Ingredient("Shrimp"),
                Ingredient("Bamboo Shoots"),
                Ingredient("Ginger"),
                Ingredient("Scallions"),
                Ingredient("Sesame Oil"),
            )
        ),
        203 to RecipeDetail(
            id = 203,
            title = "Xiaolongbao",
            image = "https://img.freepik.com/premium-photo/xiaolongbao-bamboo-tray_447653-9778.jpg",
            summary = "Shanghai-style soup dumplings with juicy pork filling.",
            pricePerServing = 4.80f,
            diets = listOf("Dessert"),
            extendedIngredients = listOf(
                Ingredient("Ground Pork"),
                Ingredient("Ginger"),
                Ingredient("Scallions"),
                Ingredient("Light Soy Sauce")
            )
        ),
        301 to RecipeDetail(
            id = 301,
            title = "Mapo Tofu",
            image = "https://norecipes.com/wp-content/uploads/2018/02/mapo-tofu-recipe-011.jpg",
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
            image = "https://www.coolinarco.com/wp-content/uploads/2023/09/ds0887_Sweet_and_Sour_Pork_745ad2e8-115b-4306-9fb5-46ac3690c0a3.webp",
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
            image = "https://www.cookingclassy.com/wp-content/uploads/2020/02/kung-pao-chicken-1-1024x1536.jpg",
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
            image = "https://www.feastingathome.com/wp-content/uploads/2022/08/Grilled-Salmon-10.jpg",
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
