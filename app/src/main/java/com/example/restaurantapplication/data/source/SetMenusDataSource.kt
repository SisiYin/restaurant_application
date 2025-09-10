package com.example.restaurantapplication.data.source

import com.example.restaurantapplication.data.model.DietCategory
import com.example.restaurantapplication.data.model.SetMenu

object SetMenusDataSource {
    val sets = listOf(
        SetMenu(
            id = "A", name = "Set A",
            limits = mapOf(
                DietCategory.MainDish to 4,
                DietCategory.Sushi to 4,
                DietCategory.Dessert to 3
            ),
            summary = "Includes: Salad ×3，Main Dish ×4，Sushi ×4，Dessert ×3，Drink ×3",
            imageUrl = "https://home.akihabara.kokosil.net/wp-content/uploads/2021/12/d14510-386-95e7589884e47bae000e-0.jpg",
            basePriceCents = 9990, // €99.90
            upchargesByRecipeId = mapOf(
                307 to 200, // Stir-fried Beef +€2.00
                305 to 300, // Grilled Salmon +€3.00
                308 to 800, // Seafood Platter +€8.00
                101 to 150, // Salmon Nigiri +€1.50
                205 to 100, // Chocolate Lava Cake +€1.00
                202 to 150, // Hargow +€1.50
                101 to 150, // Salmon Nigiri +€1.00
                104 to 150, // Eel (Unagi) Roll +€1.50
                105 to 250, //Shrimp Tempura Roll +€2.50
            )
        ),
        SetMenu(
            id = "B", name = "Set B",
            limits = mapOf(
                DietCategory.MainDish to 6,
                DietCategory.Sushi to 6,
                DietCategory.Dessert to 4
            ),
            summary = "Includes: Salad ×4，Main Dish ×6，Sushi ×6，Dessert ×4，Drink ×3",
            imageUrl = "https://oss.lhs11.com/product_imgs_1/HU6Z16/m.lhs11.com/365749.jpeg",
            basePriceCents = 14990, // €149.90
            upchargesByRecipeId = mapOf(
                305 to 300, // Grilled Salmon +€3.00
                308 to 800, // Seafood Platter +€8.00
                202 to 150, // Hargow +€1.50
                104 to 150, // Eel (Unagi) Roll +€1.50
                105 to 250, //Shrimp Tempura Roll +€2.50
                205 to 100,  // Chocolate Lava Cake +€1.00
            )
        )
    )
}