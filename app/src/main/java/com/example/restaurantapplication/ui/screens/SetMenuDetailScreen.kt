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



// ====== imports（按需保留） ======
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
import com.example.restaurantapplication.data.model.Recipe

@Composable
fun SetMenusDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    setId: String,
    recipesViewModel: RecipesViewModel,
    setMenusViewModel: SetMenusViewModel,
    onDone: () -> Unit
) {
    // 初始化套餐 + 拉取菜品兜底
    LaunchedEffect(setId) { setMenusViewModel.startSet(setId) }
    LaunchedEffect(Unit) {
        if (recipesViewModel.allRecipes.isEmpty()) recipesViewModel.fetchAllRecipes()
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
                        RecipePickCardHorizontal(
                            recipe = recipe,
                            checked = selected,
                            enabled = selected || !reached, // 达上限后，仅允许取消已选
                            onToggle = { setMenusViewModel.toggle(currentTab, recipe.id) }
                        )
                    }
                }
            }
        }



        // 底部说明 + 确认按钮
        item {
            Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    "套餐包含 Salad 和 Drink（固定项），其余按配额自选。",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Button(
                    onClick = onDone,
                    enabled = ui.isCompleted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Text("Confirm ${set.name}")
                }
            }
        }

    }
}

/* ===================== 辅助 UI ===================== */

@Composable
private fun HeaderBlockCompact(
    title: String,
    summary: String,
    mainDish: Pair<Int, Int>,
    sushi: Pair<Int, Int>,
    dessert: Pair<Int, Int>
) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(6.dp))
        Text(summary, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray))
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Main Dish ${mainDish.first}/${mainDish.second}  |  " +
                    "Sushi ${sushi.first}/${sushi.second}  |  " +
                    "Dessert ${dessert.first}/${dessert.second}",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun RecipePickCardHorizontal(
    recipe: Recipe,
    checked: Boolean,
    enabled: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(12.dp)

    Card(
        shape = shape,
        modifier = modifier
            .width(160.dp)     // 卡片宽度可按需调整
            .height(210.dp)
            .then(if (enabled) Modifier.clickable { onToggle() } else Modifier),
        border = BorderStroke(1.dp, if (checked) MaterialTheme.colorScheme.primary else Color.LightGray)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = recipe.image,
                    contentDescription = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(shape),
                    contentScale = ContentScale.Crop,
                    alpha = if (enabled || checked) 1f else 0.5f
                )
                // 右上角复选
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
                    tonalElevation = 2.dp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { if (enabled) onToggle() },
                        enabled = enabled
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                maxLines = 2
            )
        }
    }
}

/* ===================== 工具函数 ===================== */

private fun Recipe.inCategory(cat: DietCategory): Boolean = when (cat) {
    DietCategory.MainDish -> diets.any { it.equals("Main Dish", ignoreCase = true) }
    DietCategory.Sushi    -> diets.any { it.equals("Sushi", ignoreCase = true) }
    DietCategory.Dessert  -> diets.any { it.equals("Dessert", ignoreCase = true) }
}

private fun DietCategory.label(): String = when (this) {
    DietCategory.MainDish -> "Main Dish"
    DietCategory.Sushi    -> "Sushi"
    DietCategory.Dessert  -> "Dessert"
}
