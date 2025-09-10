package com.example.restaurantapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.restaurantapplication.data.model.DietCategory
import com.example.restaurantapplication.data.model.SetMenu
import com.example.restaurantapplication.data.repository.SetMenusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.max


data class SetMenusUiState(
    val currentSet: SetMenu? = null,
    // 每个类目 -> 已选 recipeId 集合
    val selections: Map<DietCategory, Set<Int>> = emptyMap()
) {
    fun count(cat: DietCategory) = selections[cat]?.size ?: 0
    fun limit(cat: DietCategory) = currentSet?.limits?.get(cat) ?: 0
    fun remaining(cat: DietCategory) = max(0, limit(cat) - count(cat))
    val isCompleted: Boolean
        get() = currentSet?.limits?.all { (c, n) -> count(c) == n } == true
}

data class SetCheckout(
    val setId: String,
    val setName: String,
    val pickedIds: Map<DietCategory, Set<Int>>,
    val includedFixed: List<String>
)

class SetMenusViewModel(
    private val repo: SetMenusRepository = SetMenusRepository()
) : ViewModel() {

    // 套餐列表（A/B）
    val sets: List<SetMenu> = repo.getAll()

    // UI 状态
    private val _uiState = MutableStateFlow(SetMenusUiState())
    val uiState: StateFlow<SetMenusUiState> = _uiState.asStateFlow()

    /** 选择某个套餐并重置选择 */
    fun startSet(setId: String) {
        val set = sets.firstOrNull { it.id == setId } ?: return
        _uiState.update {
            it.copy(
                currentSet = set,
                selections = set.limits.keys.associateWith { emptySet<Int>() }
            )
        }
    }

    /** 清空当前套餐 */
    fun clear() { _uiState.value = SetMenusUiState() }

    /** 勾选/取消一个菜（受配额约束） */
    fun toggle(cat: DietCategory, recipeId: Int) {
        val state = _uiState.value
        val set = state.currentSet ?: return
        val limit = set.limits[cat] ?: return

        val current = state.selections[cat].orEmpty()
        val next = current.toMutableSet().apply {
            if (contains(recipeId)) remove(recipeId)
            else if (size < limit) add(recipeId) // 达上限则忽略
        }

        _uiState.update {
            it.copy(selections = it.selections.toMutableMap().apply { put(cat, next) })
        }
    }

    /** 是否已选中某菜 */
    fun isSelected(cat: DietCategory, recipeId: Int): Boolean =
        _uiState.value.selections[cat]?.contains(recipeId) == true

    /** 当前类目是否达到上限（用于禁用未选项） */
    fun reachedLimit(cat: DietCategory): Boolean =
        _uiState.value.count(cat) >= _uiState.value.limit(cat)


    /** 构建结算载荷（把已选的 id 交给下单页；固定项自动带上） */
    fun buildCheckout(): SetCheckout {
        val s = _uiState.value
        val set = s.currentSet ?: error("No active set")
        return SetCheckout(
            setId = set.id,
            setName = set.name,
            pickedIds = s.selections,
            includedFixed = set.includedFixed
        )
    }
}