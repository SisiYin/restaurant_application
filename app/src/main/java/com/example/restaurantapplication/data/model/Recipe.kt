package com.example.restaurantapplication.data.model

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
    var summary: String,
    var pricePerServing: Float,
    var diets: List<String>,
    var extendedIngredients: List<Ingredient>,
)

data class Ingredient(
    var name: String
)

data class FsDish(
    var id: Int = 0,
    var title: String = "",
    var imageUrl: String = "",
    var summary: String = "",
    var pricePerServing: Double = 0.0,
    var diets: List<String> = emptyList(),
    var extendedIngredients: List<String> = emptyList()
)

fun FsDish.toRecipe() = Recipe(id, title, diets, imageUrl)
fun FsDish.toRecipeDetail() = RecipeDetail(
    id, title, imageUrl, summary, pricePerServing.toFloat(),
    diets, extendedIngredients.map { Ingredient(it) }
)


enum class DietCategory { MainDish, Sushi, Dessert }

data class SetMenu(            // 套餐定义
    val id: String,            // "A" / "B"
    val name: String,          // "A Set" / "B Set"
    // 只有可选的类目给配额；沙拉/饮料固定，不在这里限制
    val limits: Map<DietCategory, Int>,   // e.g. A: MainDish=4, Sushi=4, Dessert=3
    val includedFixed: List<String> = listOf("Salad", "Drink"),
    val summary: String,
    val price: Int = 0,
    val imageUrl: String,
    val basePriceCents: Int,                  // 套餐基础价（含固定项+配额内）
    val upchargesByRecipeId: Map<Int, Int> = emptyMap() // 每道菜的加价（选中才生效）
)

data class FixedItem(
    val title: String,
    val imageUrl: String
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
