package com.example.restaurantapplication.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
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
import com.example.restaurantapplication.ui.components.CommentsSection
import com.example.restaurantapplication.ui.components.DietRow
import com.example.restaurantapplication.ui.components.ExpandableText
import com.example.restaurantapplication.ui.components.HeroImage
import com.example.restaurantapplication.ui.components.SectionTitle
import com.example.restaurantapplication.ui.util.euro
import com.example.restaurantapplication.viewmodel.CommentsViewModel
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import com.example.restaurantapplication.viewmodel.UserViewModel
import org.jsoup.Jsoup

@SuppressLint("DefaultLocale")
@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    navController: NavController,
    recipesViewModel: RecipesViewModel,
    userViewModel: UserViewModel,
    commentsViewModel: CommentsViewModel,
    modifier: Modifier = Modifier
) {
    val recipe = remember(recipeId, recipesViewModel.allRecipes) {
        recipesViewModel.getById(recipeId)
    } ?: run {
        // 简单的 loading / 占位
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading…")
        }
        return
    }

    val isFavorite by userViewModel.isFavorite(recipe.id).collectAsState(initial = false)
    val ratingStat by commentsViewModel
        .ratingStat(recipe.id.toString())
        .collectAsState()

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

                //rating
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
                                text = ratingStat?.let { String.format("%.1f", it) } ?: "No rating yet",
                                style = MaterialTheme.typography.titleMedium
                            )
                            val count by commentsViewModel.commentsOf(recipe.id.toString())
                                .collectAsState(initial = emptyList())
                            if (count.isNotEmpty()) {
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    "(${count.size})",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                // Summary
                item {
                    SectionTitle("About this dish")
                    ExpandableText(
                        text = cleanSummary,
                        expanded = isExpanded,
                        onToggle = { isExpanded = !isExpanded },
                        collapsedMaxLines = 4
                    )
                }

                // Ingredients
                item {
                    SectionTitle("Ingredients")
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        (recipe.extendedIngredients ?: emptyList()).forEach { ing ->
                            Text("• ${ing.name}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }

                item {
                    SectionTitle("Reviews")
                    CommentsSection(
                        dishId = recipe.id.toString(),
                        commentsViewModel = commentsViewModel,
                        modifier = Modifier.fillMaxWidth()
                    )
                }


            }
        }

}


