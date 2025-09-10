package com.example.restaurantapplication.data.source
//
//import com.example.restaurantapplication.data.model.Ingredient
//import com.example.restaurantapplication.data.model.Recipe
//import com.example.restaurantapplication.data.model.RecipeDetail
//
//
//object FakeRecipesDataSource {
//    val recipes = listOf(
//        Recipe(id = 306, title = "Stewed Chicken", diets = listOf("Main Dish","Meat"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/stewed_chicken.jpg?alt=media&token=731acf6b-45b4-4563-9777-d3cca05518e8"),
//        Recipe(id = 307, title = "Stir-fried Beef", diets = listOf("Main Dish","Meat"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/stirfried_beef.jpg?alt=media&token=0ddef531-668d-4699-8744-fd63fb2e88a3"),
//        Recipe(id = 106, title = "Classic Sushi", diets = listOf("Sushi"),image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/classic_sushi.jpg?alt=media&token=b7ef7a45-00ec-40f6-8fd2-eddb762ec6c8"),
//        Recipe(id = 401, title = "Avocado Salad", diets = listOf("Salad", "Seafood"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/avocado_salad.jpg?alt=media&token=fa4fc5eb-4ab9-4455-b6d5-56bee0ead3ac"),
//        Recipe(id = 308, title = "Seafood Platter", diets = listOf("Main Dish","Seafood"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/seafood_platter.jpg?alt=media&token=9a4324e0-3a24-4f1a-9f6a-1e662162b10d"),
//        Recipe(id = 501, title = "Lemon Drink", diets = listOf("Drink"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/lemon_drink.jpg?alt=media&token=c9e557bf-ae1a-4a8f-bd15-fbac67c1cffb") ,
//        Recipe(id = 205, title = "Chocolate Lava Cake", diets = listOf("Dessert"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/chocolate_lava_cakes.jpg?alt=media&token=458146a8-fc4c-439d-96e0-380642d5f9c7"),
//        Recipe(id = 402, title = "Caesar Salad", diets = listOf("Salad"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/caesar_salad.jpg?alt=media&token=3a670b4c-800b-4ad7-95b2-cac086b1272d"),
//        Recipe(id = 502, title = "Coffee", diets = listOf("Drink"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/coffee.jpg?alt=media&token=1e09603f-bae8-46ea-8ed3-e4f9bb3d2717"),
//        Recipe(id = 503, title = "Tea", diets = listOf("Drink"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/tea.jpg?alt=media&token=14e8b133-a47c-4adf-9766-39f5d720c4aa"),
//        Recipe(id = 204, title = "Strawberries Romanoff", diets = listOf("Dessert"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/strawberries_romanoff.jpg?alt=media&token=fd6a0c84-36de-4137-9e5b-f8999fb3daf2"),
//        Recipe(id = 403, title = "Garden Salad", diets = listOf("Salad", "Vegan"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/garden_salad.jpg?alt=media&token=93deb144-10ce-43b4-a009-4a9a67a250d1"),
//        Recipe(id = 404, title = "Fruit Salad", diets = listOf("Salad", "Vegan"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/fruits_salad.jpg?alt=media&token=b7c23d7a-b517-4879-b40f-713c2f04275a"),
//        Recipe(id = 101, title ="Salmon Nigiri", diets = listOf("Sushi"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/salmon_nigiri.jpg?alt=media&token=7cf86c68-19c5-48f2-a8f1-088f15caa82d"),
//        Recipe(id = 102, title ="Tuna Maki", diets = listOf("Sushi"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/king_maki.jpg?alt=media&token=93d4a472-96a0-4909-8711-4e473ae71a18"),
//        Recipe(id = 103, title ="California Roll", diets = listOf("Sushi"), image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/california_roll.jpg?alt=media&token=583fc18e-0c63-4d96-830f-4f273a637ede"),
//        Recipe(104, "Eel (Unagi) Roll", diets = listOf("Sushi"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/eel_roll.jpg?alt=media&token=fad4b245-9595-4aeb-9f4e-e12d32dd2794"),
//        Recipe(105, "Shrimp Tempura Roll", diets = listOf("Sushi"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/shrimp_tempura_roll.jpg?alt=media&token=cef1e9a7-bc02-4624-9b96-6a215082e3aa"),
//        Recipe(301, "Mapo Tofu", diets = listOf("Main Dish"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/mapo_tofu.jpg?alt=media&token=0fc64855-20db-4e87-9564-ac6304011404"),
//        Recipe(302, "Beef Noodle Soup", diets = listOf("Main Dish"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/beef_noodle_soup.jpg?alt=media&token=00fa3cab-1262-488d-988b-592891fd9bb7"),
//        Recipe(303, "Sweet & Sour Pork", diets = listOf("Main Dish"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/sweet_sour_pork.jpg?alt=media&token=636b90e6-cccf-4902-adcc-4dde0397b5df"),
//        Recipe(304, "Kung Pao Chicken", diets = listOf("Main Dish","Meat"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/kung_pao_chicken.jpg?alt=media&token=44088781-71e4-4d32-b9bb-5af52e5f88ff"),
//        Recipe(305, "Grilled Salmon", diets = listOf("Main Dish","Meat"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/grilled_salmon.jpg?alt=media&token=a308ba20-1a58-429b-94e6-9ff35c19d9d8"),
//        Recipe(201, "Glazed Donut", diets = listOf("Dessert"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/glazed_donut.jpg?alt=media&token=978209b9-0e8d-4d63-9daa-3824b7cfae74"),
//        Recipe(202, "Har Gow", diets = listOf("Dessert","Seafood"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/har_gow.jpg?alt=media&token=0a92a33a-6c4f-4941-84f1-954188a122c7"),
//        Recipe(203, "Xiaolongbao", diets = listOf("Dessert"), image ="https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/xiaolongbao.jpg?alt=media&token=146b43a1-c69f-45ef-a7b1-495a09641060"),
//    )
//
//    val recipeDetails = mapOf(
//        306 to RecipeDetail(
//            id = 306,
//            title = "Stewed Chicken",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/stewed_chicken.jpg?alt=media&token=731acf6b-45b4-4563-9777-d3cca05518e8",
//            summary = "Juicy grilled chicken served with seasonal vegetables.",
//            pricePerServing = 18.99f,
//            diets = listOf("Main Dish","Meat"),
//            extendedIngredients = listOf(
//                Ingredient("Chicken Breast"),
//                Ingredient("Olive Oil"),
//                Ingredient("Garlic"),
//                Ingredient("Salt"),
//                Ingredient("Pepper")
//            )
//        ),
//        307 to RecipeDetail(
//            id = 307,
//            title = "Stir-fried Beef",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/stirfried_beef.jpg?alt=media&token=0ddef531-668d-4699-8744-fd63fb2e88a3",
//            summary = "Tender beef steak cooked to perfection with a side of mashed potatoes.",
//            pricePerServing = 22.99f,
//            diets = listOf("Main Dish","Meat"),
//            extendedIngredients = listOf(
//                Ingredient("Beef Steak"),
//                Ingredient("Butter"),
//                Ingredient("Garlic"),
//                Ingredient("Rosemary")
//            )
//        ),
//        106 to RecipeDetail(
//            id = 106,
//            title = "Classic Sushi",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/classic_sushi.jpg?alt=media&token=b7ef7a45-00ec-40f6-8fd2-eddb762ec6c8",
//            summary = "Traditional Japanese sushi rolls with fresh fish and rice.",
//            pricePerServing = 10.50f,
//            diets = listOf("Sushi"),
//            extendedIngredients = listOf(
//                Ingredient("Rice"),
//                Ingredient("Nori"),
//                Ingredient("Salmon"),
//                Ingredient("Tuna"),
//                Ingredient("Avocado")
//            )
//        ),
//        401 to RecipeDetail(
//            id = 401,
//            title = "Avocado Salad",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/avocado_salad.jpg?alt=media&token=fa4fc5eb-4ab9-4455-b6d5-56bee0ead3ac",
//            summary = "A creamy and healthy avocado salad with fresh greens and lime dressing.",
//            pricePerServing = 9.99f,
//            diets = listOf("Salad", "Seafood"),
//            extendedIngredients = listOf(
//                Ingredient("Avocado"),
//                Ingredient("Lettuce"),
//                Ingredient("Cherry Tomatoes"),
//                Ingredient("Lime Juice"),
//                Ingredient("Olive Oil")
//            )
//        ),
//        308 to RecipeDetail(
//            id = 308,
//            title = "Seafood Platter",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/seafood_platter.jpg?alt=media&token=9a4324e0-3a24-4f1a-9f6a-1e662162b10d",
//            summary = "A luxurious platter of fresh seafood including prawns, crab, and lobster.",
//            pricePerServing = 35.99f,
//            diets = listOf("Main Dish","Seafood"),
//            extendedIngredients = listOf(
//                Ingredient("Prawns"),
//                Ingredient("Crab"),
//                Ingredient("Lobster"),
//                Ingredient("Butter Sauce")
//            )
//        ),
//        501 to RecipeDetail(
//            id = 501,
//            title = "Lemon Drink",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/lemon_drink.jpg?alt=media&token=c9e557bf-ae1a-4a8f-bd15-fbac67c1cffb",
//            summary = "A chilled lemon drink to refresh your senses.",
//            pricePerServing = 4.50f,
//            diets = listOf("Drink"),
//            extendedIngredients = emptyList()
//
//        ),
//        205 to RecipeDetail(
//            id = 205,
//            title = "Chocolate Lava Cake",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/chocolate_lava_cakes.jpg?alt=media&token=458146a8-fc4c-439d-96e0-380642d5f9c7",
//            summary = "A rich and gooey chocolate dessert with a molten center.",
//            pricePerServing = 6.99f,
//            diets = listOf("Dessert"),
//            extendedIngredients = listOf(
//                Ingredient("Chocolate"),
//                Ingredient("Butter"),
//                Ingredient("Sugar"),
//                Ingredient("Eggs"),
//                Ingredient("Flour")
//            )
//        ),
//        402 to RecipeDetail(
//            id = 402,
//            title = "Caesar Salad",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/caesar_salad.jpg?alt=media&token=3a670b4c-800b-4ad7-95b2-cac086b1272d",
//            summary = "A classic Caesar salad with crispy croutons and creamy dressing.",
//            pricePerServing = 9.50f,
//            diets = listOf("Salad"),
//            extendedIngredients = listOf(
//                Ingredient("Romaine Lettuce"), Ingredient("Croutons"), Ingredient("Parmesan"), Ingredient("Caesar Dressing")
//            )
//        ),
//        502 to RecipeDetail(
//            id = 502,
//            title = "Coffee",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/coffee.jpg?alt=media&token=1e09603f-bae8-46ea-8ed3-e4f9bb3d2717",
//            summary = "Smooth and creamy coffee.",
//            pricePerServing = 4.50f,
//            diets = listOf("Drink"),
//            extendedIngredients = emptyList()
//        ),
//        204 to RecipeDetail(
//            id = 204,
//            title = "Strawberries Romanoff",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/strawberries_romanoff.jpg?alt=media&token=fd6a0c84-36de-4137-9e5b-f8999fb3daf2",
//            summary = "A creamy cheesecake with a buttery biscuit base.",
//            pricePerServing = 7.99f,
//            diets = listOf("Dessert"),
//            extendedIngredients = listOf(
//                Ingredient("Strawberry"), Ingredient("Sugar"), Ingredient("Cream"), Ingredient("Sour Cream"), Ingredient("Grand Marnier")
//            )
//        ),
//        503 to RecipeDetail(
//            id = 503,
//            title = "Tea",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/tea.jpg?alt=media&token=14e8b133-a47c-4adf-9766-39f5d720c4aa",
//            summary = "Light and refreshing hot tea.",
//            pricePerServing = 4.50f,
//            diets = listOf("Drink"),
//            extendedIngredients = emptyList()
//        ),
//        403 to RecipeDetail(
//            id = 403,
//            title = "Garden Salad",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/garden_salad.jpg?alt=media&token=93deb144-10ce-43b4-a009-4a9a67a250d1",
//            summary = "A classic vegetable salad with crispy croutons and creamy dressing.",
//            pricePerServing = 9.50f,
//            diets = listOf("Salad","Vegan"),
//            extendedIngredients = listOf(
//                Ingredient("Romaine Lettuce"), Ingredient("Croutons"), Ingredient("Parmesan"), Ingredient("Salad Dressing")
//            )
//        ),
//        404 to RecipeDetail(
//            id = 404,
//            title = "Fruits Salad",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/fruits_salad.jpg?alt=media&token=b7c23d7a-b517-4879-b40f-713c2f04275a",
//            summary = "A Salad with variety kinds of fruit.",
//            pricePerServing = 6.50f,
//            diets = listOf("Salad","Vegan"),
//            extendedIngredients = listOf(
//                Ingredient("Apple"), Ingredient("Strawberry"), Ingredient("Blueberry"), Ingredient("Major Dressing")
//            )
//        ),
//
//        101 to RecipeDetail(
//            id = 101,
//            title = "Salmon Nigiri",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/salmon_nigiri.jpg?alt=media&token=7cf86c68-19c5-48f2-a8f1-088f15caa82d",
//            summary = "Classic Japanese nigiri with fresh salmon over seasoned rice.",
//            pricePerServing = 10.50f,
//            diets = listOf("Sushi"),
//            extendedIngredients = listOf(
//                Ingredient("Salmon"),
//                Ingredient("Sushi Rice"),
//                Ingredient("Nori"),
//                Ingredient("Soy Sauce")
//            )
//        ),
//        102 to RecipeDetail(
//            id = 102,
//            title = "Tuna Maki",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/king_maki.jpg?alt=media&token=93d4a472-96a0-4909-8711-4e473ae71a18",
//            summary = "Traditional tuna maki rolls wrapped in nori.",
//            pricePerServing = 9.80f,
//            diets = listOf("Sushi"),
//            extendedIngredients = listOf(
//                Ingredient("Tuna"),
//                Ingredient("Sushi Rice"),
//                Ingredient("Nori")
//            )
//        ),
//        103 to RecipeDetail(
//            id = 103,
//            title = "California Roll",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/california_roll.jpg?alt=media&token=583fc18e-0c63-4d96-830f-4f273a637ede",
//            summary = "American-style sushi roll with crab stick, avocado, and cucumber.",
//            pricePerServing = 11.20f,
//            diets = listOf("Sushi"),
//            extendedIngredients = listOf(
//                Ingredient("Crab Stick"),
//                Ingredient("Avocado"),
//                Ingredient("Cucumber"),
//                Ingredient("Sushi Rice"),
//                Ingredient("Nori")
//            )
//        ),
//        104 to RecipeDetail(
//            id = 104,
//            title = "Eel (Unagi) Roll",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/eel_roll.jpg?alt=media&token=fad4b245-9595-4aeb-9f4e-e12d32dd2794",
//            summary = "Sweet and savory grilled eel roll glazed with unagi sauce.",
//            pricePerServing = 12.90f,
//            diets = listOf("Sushi"),
//            extendedIngredients = listOf(
//                Ingredient("Eel"),
//                Ingredient("Unagi Sauce"),
//                Ingredient("Sushi Rice"),
//                Ingredient("Nori")
//            )
//        ),
//        105 to RecipeDetail(
//            id = 105,
//            title = "Shrimp Tempura Roll",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/shrimp_tempura_roll.jpg?alt=media&token=cef1e9a7-bc02-4624-9b96-6a215082e3aa",
//            summary = "Crispy shrimp tempura rolled with rice and vegetables.",
//            pricePerServing = 13.50f,
//            diets = listOf("Sushi"),
//            extendedIngredients = listOf(
//                Ingredient("Shrimp Tempura"),
//                Ingredient("Avocado"),
//                Ingredient("Cucumber"),
//                Ingredient("Sushi Rice"),
//                Ingredient("Nori")
//            )
//        ),
//        201 to RecipeDetail(
//            id = 201,
//            title = "Glazed Donut",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/glazed_donut.jpg?alt=media&token=978209b9-0e8d-4d63-9daa-3824b7cfae74",
//            summary = "Classic ring doughnut with vanilla glaze.",
//            pricePerServing = 3.49f,
//            diets = listOf("Dessert"),
//            extendedIngredients = listOf(
//                Ingredient("All-purpose Flour"),
//                Ingredient("Granulated Sugar"),
//                Ingredient("Whole Milk"),
//                Ingredient("Egg"),
//                Ingredient("Unsalted Butter"),
//            )
//        ),
//        202 to RecipeDetail(
//            id = 202,
//            title = "Har Gow",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/har_gow.jpg?alt=media&token=0a92a33a-6c4f-4941-84f1-954188a122c7",
//            summary = "Cantonese shrimp dumplings with translucent wrappers.",
//            pricePerServing = 5.89f,
//            diets = listOf("Dessert"),
//            extendedIngredients = listOf(
//                Ingredient("Shrimp"),
//                Ingredient("Bamboo Shoots"),
//                Ingredient("Ginger"),
//                Ingredient("Scallions"),
//                Ingredient("Sesame Oil"),
//            )
//        ),
//        203 to RecipeDetail(
//            id = 203,
//            title = "Xiaolongbao",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/xiaolongbao.jpg?alt=media&token=146b43a1-c69f-45ef-a7b1-495a09641060",
//            summary = "Shanghai-style soup dumplings with juicy pork filling.",
//            pricePerServing = 4.80f,
//            diets = listOf("Dessert"),
//            extendedIngredients = listOf(
//                Ingredient("Ground Pork"),
//                Ingredient("Ginger"),
//                Ingredient("Scallions"),
//                Ingredient("Light Soy Sauce")
//            )
//        ),
//        301 to RecipeDetail(
//            id = 301,
//            title = "Mapo Tofu",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/mapo_tofu.jpg?alt=media&token=0fc64855-20db-4e87-9564-ac6304011404",
//            summary = "Spicy Sichuan-style tofu with minced pork and chili bean paste.",
//            pricePerServing = 15.50f,
//            diets = listOf("Main Dish"),
//            extendedIngredients = listOf(
//                Ingredient("Tofu"),
//                Ingredient("Ground Pork"),
//                Ingredient("Chili Bean Paste"),
//                Ingredient("Garlic"),
//                Ingredient("Sichuan Peppercorns")
//            )
//        ),
//        302 to RecipeDetail(
//            id = 302,
//            title = "Beef Noodle Soup",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/beef_noodle_soup.jpg?alt=media&token=00fa3cab-1262-488d-988b-592891fd9bb7",
//            summary = "Slow-cooked beef brisket served with noodles in savory broth.",
//            pricePerServing = 14.80f,
//            diets = listOf("Main Dish"),
//            extendedIngredients = listOf(
//                Ingredient("Beef Brisket"),
//                Ingredient("Noodles"),
//                Ingredient("Beef Broth"),
//                Ingredient("Scallions")
//            )
//        ),
//        303 to RecipeDetail(
//            id = 303,
//            title = "Sweet & Sour Pork",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/sweet_sour_pork.jpg?alt=media&token=636b90e6-cccf-4902-adcc-4dde0397b5df",
//            summary = "Crispy pork pieces coated in tangy sweet & sour sauce.",
//            pricePerServing = 18.90f,
//            diets = listOf("Main Dish"),
//            extendedIngredients = listOf(
//                Ingredient("Pork"),
//                Ingredient("Pineapple"),
//                Ingredient("Bell Pepper"),
//                Ingredient("Sweet & Sour Sauce")
//            )
//        ),
//        304 to RecipeDetail(
//            id = 304,
//            title = "Kung Pao Chicken",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/kung_pao_chicken.jpg?alt=media&token=44088781-71e4-4d32-b9bb-5af52e5f88ff",
//            summary = "Spicy stir-fried chicken with peanuts, chili, and vegetables.",
//            pricePerServing = 16.90f,
//            diets = listOf("Main Dish","Meat"),
//            extendedIngredients = listOf(
//                Ingredient("Chicken"),
//                Ingredient("Peanuts"),
//                Ingredient("Dried Chili"),
//                Ingredient("Soy Sauce"),
//                Ingredient("Garlic")
//            )
//        ),
//        305 to RecipeDetail(
//            id = 305,
//            title = "Grilled Salmon",
//            image = "https://firebasestorage.googleapis.com/v0/b/okiniiri-restaurant.firebasestorage.app/o/grilled_salmon.jpg?alt=media&token=a308ba20-1a58-429b-94e6-9ff35c19d9d8",
//            summary = "Salmon fillet grilled with lemon and herbs.",
//            pricePerServing = 18.50f,
//            diets = listOf("Main Dish","Seafood"),
//            extendedIngredients = listOf(
//                Ingredient("Salmon"),
//                Ingredient("Lemon"),
//                Ingredient("Olive Oil"),
//                Ingredient("Rosemary")
//            )
//        ),
//    )
//}
