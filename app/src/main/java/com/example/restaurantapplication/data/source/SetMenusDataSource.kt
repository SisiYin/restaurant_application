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
            imageUrl = "https://home.akihabara.kokosil.net/wp-content/uploads/2021/12/d14510-386-95e7589884e47bae000e-0.jpg"
        ),
        SetMenu(
            id = "B", name = "Set B",
            limits = mapOf(
                DietCategory.MainDish to 6,
                DietCategory.Sushi to 6,
                DietCategory.Dessert to 4
            ),
            summary = "Includes: Salad ×4，Main Dish ×6，Sushi ×6，Dessert ×4，Drink ×3",
            imageUrl = "https://oss.lhs11.com/product_imgs_1/HU6Z16/m.lhs11.com/365749.jpeg"
        )
    )
}