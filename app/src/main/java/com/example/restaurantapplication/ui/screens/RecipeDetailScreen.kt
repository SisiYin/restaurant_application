package com.example.restaurantapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.restaurantapplication.ui.components.DietRow
import com.example.restaurantapplication.ui.components.HeroImage
import com.example.restaurantapplication.ui.util.euro
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import com.example.restaurantapplication.viewmodel.UserViewModel
import org.jsoup.Jsoup

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    navController: NavController,
    recipesViewModel: RecipesViewModel,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    val userId = "123456"
    val recipe = remember(recipeId, recipesViewModel.allRecipes) {
        recipesViewModel.getById(recipeId)
    } ?: run {
        // 简单的 loading / 占位
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading…")
        }
        return
    }

        var isFavorite by remember { mutableStateOf(false) }
        val cleanSummary = Jsoup.parse(recipe.summary).text()
        var isExpanded by remember { mutableStateOf(false) }

        Box(
            modifier = modifier
                .fillMaxSize()
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                item {
                    HeroImage(
                        url = recipe.image,
                        title = recipe.title,
                        isFavorite = isFavorite,
                        onToggleFavorite = {
                            isFavorite = !isFavorite
                            userViewModel.toggleFavorite(recipe.id)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 180.dp, max = 280.dp) // ✅ 大图
                    )

                }

                item {
                    DietRow(diets = recipe.diets)
                }

                // 标题
                item {
                    Text(recipe.title, style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Price per serving",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = euro(recipe.pricePerServing),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                //rating and favorite
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //rating
                        Row(
                            verticalAlignment = Alignment.CenterVertically // 垂直居中
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star Icon",
                                tint = Color(0xFFFFD700),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                //text = if (avgRating == -1.0) "No Rating Yet" else "$avgRating",
                                text = "Vote",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 24.sp
                                )
                            )
                        }

                        //favorite button
                    }
                }

                item{
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Price per serving:${euro(recipe.pricePerServing)} EUR")

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = cleanSummary,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 6, // 如果展开则显示完整内容
                        overflow = TextOverflow.Ellipsis // 省略超出的部分
                    )
                }

                item{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { isExpanded = !isExpanded }) {
                            Text(text = if (isExpanded) "Less <<" else "More >>")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 显示配料列表
                    Text(text = "Ingredients:", fontWeight = FontWeight.Bold)
                    (recipe.extendedIngredients ?: emptyList()).forEach { ingredient ->
                        Text(text = ingredient.name)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                }


            }
        }

}
