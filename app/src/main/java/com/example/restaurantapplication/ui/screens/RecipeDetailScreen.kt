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
import coil.compose.rememberAsyncImagePainter
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

    val recipeDetail = recipesViewModel.recipeDetail.value
    if (recipeDetail != null) {
        //var isFavorite by remember { mutableStateOf(false) }
        val cleanSummary = Jsoup.parse(recipeDetail.summary).text()
        var isExpanded by remember { mutableStateOf(false) }

        Box(
            modifier = modifier
                .fillMaxSize()
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(bottom = 80.dp), //
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                item {
                    AsyncImage(
                        model = recipeDetail.image,
                        contentDescription = recipeDetail.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 180.dp, max = 280.dp) // 限制高度范围，但不固定死
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )

                }

                item{
                    recipeDetail.diets?.let { diets ->

                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            diets.forEach { diet ->
                                AssistChip(
                                    onClick = {  },
                                    label = { Text(text = diet) },
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = getDietColor(diet) // 动态生成颜色
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                //title
                item {
                    Text(
                        text = recipeDetail.title,
                        style = MaterialTheme.typography.headlineSmall
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
                    Text(text = "Price per serving:${recipeDetail.pricePerServing} EUR")

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
                    (recipeDetail.extendedIngredients ?: emptyList()).forEach { ingredient ->
                        Text(text = ingredient.name)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                }


            }
        }
    }
}

@Composable
fun getDietColor(diet: String): Color {
    val hue = (diet.hashCode() % 360).toFloat().let { if (it < 0) it + 360 else it } // 确保 hue 在 0~360
    return Color.hsl(hue, 0.4f, 0.65f)
}