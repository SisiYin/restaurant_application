package com.example.restaurantapplication.domain.pricing

import com.example.restaurantapplication.data.model.DietCategory
import com.example.restaurantapplication.data.model.SetMenu

data class PriceBreakdown(val baseCents: Int, val upchargeCents: Int) {
    val totalCents: Int get() = baseCents + upchargeCents
}

fun computeSetPrice(
    set: SetMenu,
    selections: Map<DietCategory, Set<Int>>
): PriceBreakdown {
    val pickedIds = selections.values.flatten()
    val up = pickedIds.sumOf { id -> set.upchargesByRecipeId[id] ?: 0 }
    return PriceBreakdown(set.basePriceCents, up)
}

fun upchargeOf(set: SetMenu, recipeId: Int): Int =
    set.upchargesByRecipeId[recipeId] ?: 0