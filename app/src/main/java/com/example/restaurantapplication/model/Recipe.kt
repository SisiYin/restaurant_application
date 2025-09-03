package com.example.restaurantapplication.model

//import com.google.firebase.Timestamp


data class RecipesResponse(
    val results: List<Recipe>,
    val number: Int
)

data class Recipe(
    var id: Int,
    var title: String,
    var diets: List<String>,
    var image: String,

    )

data class RecipeDetail(
    var id: Int,
    var title: String,
    var image: String,
    //var readyInMinutes: Int,
    //var servings: Int,
    var summary: String,
    var pricePerServing: Float,
    //var caloriesPerServing: Int,
    //var healthScore: Double,
    var diets: List<String>,
    var extendedIngredients: List<Ingredient>,
)

data class CartItem(
    val recipeId: String = "",
    val title: String = "",
    val image: String = "",
    val quantity: Long = 0
)

//data class Order(
//    val orderId: String = "",
//    val userId: String = "",
//    val status: String = "",
//    val createdAt: Timestamp = Timestamp.now(), // ✅ 修改为 Firestore Timestamp
//    val address: String = "",
//    val phoneNumber: String = "",
//    val timeSlot: String = "",
//    val selectedDate: String = "",
//    val note: String = "",
//    val orderItems: List<CartItem> = emptyList()
//)

data class User(
    val userId: String = "",
    val email: String = "",
    val gender: String = "",
    val birthday: String = "",
    val createdAt: String = "",
    val address: String = "",
    val name: String = "",
    val avatar: String = "",
    val phoneNumber: String = "",
    val role: String = "",
)


//data class Comment(
//    val userId: String = "",
//    val userName: String = "",
//    val rating: Double = 0.0,
//    val content: String = "",
//    val timestamp: Timestamp = Timestamp.now(),
//    val likes: Long = 0,
//
//    )



// 配料类
data class Ingredient(
    var name: String
)