package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

enum class DietLayout { Row, Flow }

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DietRow(
    diets: List<String>,
    layout: DietLayout = DietLayout.Row,   // 默认横向滚动
    modifier: Modifier = Modifier
) {
    if (diets.isEmpty()) return

    when (layout) {
        DietLayout.Row -> {
            LazyRow(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 2.dp)
            ) {
                items(diets) { diet ->
                    DietPill(diet = diet)
                }
            }
        }
        DietLayout.Flow -> {
            FlowRow(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                diets.forEach { diet ->
                    DietPill(diet = diet)
                }
            }
        }
    }
}