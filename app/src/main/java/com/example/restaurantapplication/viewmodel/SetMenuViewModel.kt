package com.example.restaurantapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.restaurantapplication.model.DietCategory
import com.example.restaurantapplication.model.SetMenu

class SetMenusViewModel : ViewModel() {

    val sets = listOf(
        SetMenu(
            id = "A",
            name = "Set A",
            limits = mapOf(
                DietCategory.MainDish to 4,
                DietCategory.Sushi to 4,
                DietCategory.Dessert to 3
            )
        ),
        SetMenu(
            id = "B",
            name = "Set B",
            limits = mapOf(
                DietCategory.MainDish to 6,
                DietCategory.Sushi to 6,
                DietCategory.Dessert to 4
            )
        )
    )

    var currentSet by mutableStateOf<SetMenu?>(null)
        private set

    // 每个可选类目 -> 已选菜品 id 集合
    private val _selected = mutableStateMapOf<DietCategory, MutableSet<Int>>()
    val selected: Map<DietCategory, Set<Int>> get() = _selected

    fun startSet(id: String) {
        currentSet = sets.first { it.id == id }
        _selected.clear()
        currentSet!!.limits.keys.forEach { _selected[it] = mutableSetOf() }
    }

    fun toggle(cat: DietCategory, recipeId: Int) {
        val limit = currentSet?.limits?.get(cat) ?: return
        val set = _selected.getOrPut(cat) { mutableSetOf() }
        if (recipeId in set) set.remove(recipeId)
        else if (set.size < limit) set.add(recipeId)
    }

    fun count(cat: DietCategory) = _selected[cat]?.size ?: 0
    fun limit(cat: DietCategory) = currentSet?.limits?.get(cat) ?: 0

    val isCompleted: Boolean
        get() = currentSet?.limits?.all { (c, n) -> count(c) == n } == true
}
