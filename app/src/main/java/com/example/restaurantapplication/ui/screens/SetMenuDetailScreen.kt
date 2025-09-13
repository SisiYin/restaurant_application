package com.example.restaurantapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantapplication.data.model.DietCategory
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import com.example.restaurantapplication.viewmodel.SetMenusViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.example.restaurantapplication.domain.pricing.upchargeOf
import com.example.restaurantapplication.ui.components.FixedCategoryRow
import com.example.restaurantapplication.ui.components.RecipePickCard
import com.example.restaurantapplication.ui.util.EuroText
import com.example.restaurantapplication.ui.util.euro
import com.example.restaurantapplication.ui.util.inCategory
import com.example.restaurantapplication.ui.util.label

@Composable
fun SetMenusDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    setId: String,
    recipesViewModel: RecipesViewModel,
    setMenusViewModel: SetMenusViewModel,
) {
    // 初始化套餐 + 拉取菜品兜底
    LaunchedEffect(setId) { setMenusViewModel.startSet(setId) }
    LaunchedEffect(Unit) {
        if (recipesViewModel.allRecipes.isEmpty()) recipesViewModel.fetchAllRecipes()
    }
    val allRecipes = recipesViewModel.allRecipes

    val salads by remember(allRecipes) {
        derivedStateOf { allRecipes.filter { it.diets.any { d -> d.equals("Salad", true) } } }
    }
    val drinks by remember(allRecipes) {
        derivedStateOf { allRecipes.filter { it.diets.any { d -> d.equals("Drink", true) } } }
    }

    val ui by setMenusViewModel.uiState.collectAsState()
    val set = ui.currentSet ?: return

    // 只保留本套餐包含的类目（固定顺序）
    val categories = listOf(DietCategory.MainDish, DietCategory.Sushi, DietCategory.Dessert)
        .filter { it in set.limits.keys }

    var currentTab by rememberSaveable { mutableStateOf(categories.first()) }
    val list = remember(recipesViewModel.allRecipes, currentTab) {
        recipesViewModel.allRecipes.filter { it.inCategory(currentTab) }
    }
    val reached = setMenusViewModel.reachedLimit(currentTab)
    //val price by remember(ui) { derivedStateOf { computeSetPrice(set, ui.selections) } }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        // 头部：标题 + summary + 简洁进度
        item {
            AsyncImage(
                model = set.imageUrl,
                contentDescription = set.name,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .heightIn(min = 180.dp, max = 280.dp) // 限制高度范围，但不固定死
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }

        item {
            HeaderBlockCompact(
                title = set.name,
                summary = set.summary,
                mainDish = ui.count(DietCategory.MainDish) to ui.limit(DietCategory.MainDish),
                sushi    = ui.count(DietCategory.Sushi)     to ui.limit(DietCategory.Sushi),
                dessert  = ui.count(DietCategory.Dessert)   to ui.limit(DietCategory.Dessert)
            )
        }

        // 顶部 Tabs
        item {
            TabRow(selectedTabIndex = categories.indexOf(currentTab)) {
                categories.forEach { c ->
                    Tab(
                        selected = (currentTab == c),
                        onClick = { currentTab = c },
                        text = { Text(c.label()) }
                    )
                }
            }
            Spacer(Modifier.height(4.dp))
        }

        // 当前类目菜品（横向单行，可滑动）
        item {
            if (list.isEmpty()) {
                Box(
                    Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No items in this category.")
                }
            } else {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(list, key = { it.id }) { recipe ->
                        val selected = setMenusViewModel.isSelected(currentTab, recipe.id)
                        val extra = upchargeOf(set, recipe.id)
                        RecipePickCard(
                            recipe = recipe,
                            checked = selected,
                            enabled = selected || !reached,
                            onToggle = { setMenusViewModel.toggle(currentTab, recipe.id) },
                            extraCents = extra
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }

        // —— 固定项：Salad ——（标题=Salad，下面横向所有 Salad 卡片）
        item {
            FixedCategoryRow(
                title = "Salad",
                recipes = salads
            )
            Spacer(Modifier.height(8.dp))

        }
        // —— 固定项：Drink ——（标题=Drink，下面横向所有 Drink 卡片）
        item {
            FixedCategoryRow(
                title = "Drink",
                recipes = drinks
            )
            Spacer(Modifier.height(8.dp))
        }

    }

}



@Composable
private fun HeaderBlockCompact(
    title: String,
    summary: String,
    mainDish: Pair<Int, Int>,
    sushi: Pair<Int, Int>,
    dessert: Pair<Int, Int>
) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
        //Title
        Text(title, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))

        //Summary
        Text(summary, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray))
        Spacer(Modifier.height(4.dp))

        Text(
            "Salad and Drink are fixed; pick the others up to the quota.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(Modifier.height(8.dp))

        //Selected dish number
        Text(
            text = "Main Dish ${mainDish.first}/${mainDish.second}  |  " +
                    "Sushi ${sushi.first}/${sushi.second}  |  " +
                    "Dessert ${dessert.first}/${dessert.second}",
            style = MaterialTheme.typography.labelLarge
        )
    }
}


