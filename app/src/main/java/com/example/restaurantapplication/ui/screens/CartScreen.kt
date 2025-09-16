package com.example.restaurantapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantapplication.data.model.CartLine
import com.example.restaurantapplication.ui.components.DishLineCard
import com.example.restaurantapplication.ui.components.SetLineCard
import com.example.restaurantapplication.viewmodel.CartViewModel
import com.example.restaurantapplication.viewmodel.RecipesViewModel

@Composable
fun CartScreen(
    navController: NavController,
    recipesViewModel: RecipesViewModel,        // ✅ 新增
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    val ui by cartViewModel.uiState.collectAsState()

    if (ui.lines.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Your cart is empty.")
        }
    } else {
        // id -> title 解析器（英文名）
        val nameMap = remember(recipesViewModel.allRecipes) {
            recipesViewModel.allRecipes.associate { it.id to it.title }
        }

// 需要的解析器：(Int) -> String?
        val titleOf: (Int) -> String? = remember(nameMap) {
            { id: Int -> nameMap[id] }
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(ui.lines, key = { it.id }) { line ->
                when (line) {
                    is CartLine.SetMenuLine -> SetLineCard(line, cartViewModel,titleOf)
                    is CartLine.DishLine -> DishLineCard(line, cartViewModel,titleOf)
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}


