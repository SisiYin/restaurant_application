package com.example.restaurantapplication.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapplication.data.repository.CommentsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommentsViewModel: ViewModel() {

    private val repoComments = CommentsRepository()

    // 详情页：按菜监听
    fun commentsOf(dishId: String) = repoComments.listenByDish(dishId)

    // 统计平均分（给 Vote 显示）
    fun ratingStat(dishId: String) =
        commentsOf(dishId).map { list ->
            val rs = list.mapNotNull { it.rating }
            if (rs.isEmpty()) null else rs.average()
        }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    // 输入状态（只在详情页用到）
    var inputText by mutableStateOf("")
        private set
    var inputRating by mutableStateOf<Int?>(null)
        private set

    fun updateText(t: String) { inputText = t }
    fun updateRating(r: Int?) { inputRating = r }

    // VM 里
    fun submit(dishId: String, nickname: String? = null) = viewModelScope.launch {
        try {
            val text = inputText.trim()
            if (text.isEmpty() && inputRating == null) return@launch
            repoComments.postOrUpdate(dishId, text, inputRating, nickname)
            inputText = ""
            inputRating = null
        } catch (e: Exception) {
            Log.e("CommentsVM", "Submit failed", e)
            // TODO: show snackbar / toast
        }
    }



    fun delete(dishId: String, commentId: String) = viewModelScope.launch {
        repoComments.delete(dishId, commentId)
    }

    // Profile：我的评论列表
    val myComments = repoComments.listenByUser()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}