package com.example.restaurantapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.restaurantapplication.data.model.CartLine
import com.example.restaurantapplication.data.model.CartUiState
import com.example.restaurantapplication.data.model.DietCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel : ViewModel() {
    private val _ui = MutableStateFlow(CartUiState())
    val uiState = _ui.asStateFlow()

    fun addSet(
        setId: String,
        setName: String,
        imageUrl: String?,
        basePriceCents: Int,
        upchargeCents: Int,
        selections: Map<DietCategory, Set<Int>>
    ) {
        val line = CartLine.SetMenuLine(
            title = setName,
            priceCents = basePriceCents + upchargeCents,
            setId = setId,
            imageUrl = imageUrl,
            selections = selections
        )
        _ui.value = _ui.value.copy(lines = _ui.value.lines + line)
    }

    fun addDish(recipeId: Int, title: String, priceCents: Int, imageUrl: String?) {
        val line = CartLine.DishLine(
            recipeId = recipeId,
            title = title,
            priceCents = priceCents,
            imageUrl = imageUrl
        )
        _ui.value = _ui.value.copy(lines = _ui.value.lines + line)
    }

    fun changeQty(lineId: String, newQty: Int) {
        if (newQty <= 0) return remove(lineId)
        _ui.value = _ui.value.copy(lines = _ui.value.lines.map {
            when {
                it.id != lineId -> it
                it is CartLine.SetMenuLine -> it.copy(qty = newQty)
                it is CartLine.DishLine -> it.copy(qty = newQty)
                else -> it
            }
        })
    }

    fun remove(lineId: String) {
        _ui.value = _ui.value.copy(lines = _ui.value.lines.filterNot { it.id == lineId })
    }

    fun clear() { _ui.value = CartUiState() }
}