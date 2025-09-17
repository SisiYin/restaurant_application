package com.example.restaurantapplication.data.model

import java.util.UUID
import kotlin.math.roundToInt

//import com.google.firebase.Timestamp


data class RecipesResponse(
    val results: List<Recipe>,
    val number: Int
)

//data class Recipe(
//    var id: Int,
//    var title: String,
//    var diets: List<String>,
//    var image: String,
//    //var pricePerServing: Float,
//    )

data class Recipe(
    var id: Int,
    var title: String,
    var image: String,
    var summary: String,
    val pricePerServing: Int,   // ✅ 用 Double
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

private fun Double.euroToCents(): Int = (this * 100).roundToInt()


//fun FsDish.toRecipe() = Recipe(id, title, diets, imageUrl)
fun FsDish.toRecipe() = Recipe(
    id, title, imageUrl, summary, pricePerServing.euroToCents(),
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


//Cart

sealed class CartLine {
    abstract val id: String
    abstract val title: String
    abstract val priceCents: Int
    abstract val qty: Int

    data class SetMenuLine(
        override val id: String = UUID.randomUUID().toString(),
        override val title: String,
        override val priceCents: Int,          // base + upcharge（单份）
        override val qty: Int = 1,
        val setId: String,
        val imageUrl: String?,
        val selections: Map<DietCategory, Set<Int>> // 每类选中的 recipeId（Int）
    ) : CartLine()

    data class DishLine(
        override val id: String = UUID.randomUUID().toString(),
        override val title: String,
        override val priceCents: Int,          // 单份
        override val qty: Int = 1,
        val recipeId: Int,
        val imageUrl: String?
    ) : CartLine()
}

data class CartUiState(
    val lines: List<CartLine> = emptyList(),
    val selectedIds: Set<String> = emptySet()//selected state
) {
    val subtotalCents: Int = lines.sumOf { it.priceCents * it.qty }
    val count: Int = lines.sumOf { it.qty }
    // ✅ 只算已勾选的商品
    val selectedSubtotalCents: Int =
        lines.filter { selectedIds.contains(it.id) }
            .sumOf { it.priceCents * it.qty }
}

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


data class Comment(
    val id: String = "",
    val dishId: String = "",
    val uid: String? = null,
    val nickname: String? = null,
    val text: String = "",
    val rating: Int? = null,                  // 1..5，可空
    val createdAt: com.google.firebase.Timestamp? = null,
    val updatedAt: com.google.firebase.Timestamp? = null
)

data class UserCommentRef(
    val id: String = "",
    val dishId: String = "",
    val rating: Int? = null,
    val textPreview: String? = null,
    val createdAt: com.google.firebase.Timestamp? = null
)




// 配料类
