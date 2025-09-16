package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.restaurantapplication.data.model.Recipe
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import com.example.restaurantapplication.viewmodel.UserViewModel

@Composable
fun DishMenuCard(
    recipe: Recipe,
    navController: NavController,
    recipesViewModel: RecipesViewModel,
    userViewModel: UserViewModel,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onClick: () -> Unit
) {
//    val avgRating by recipesViewModel.avgRating.collectAsState()
//    val recipeId = recipe.id
//    LaunchedEffect(recipeId) {
//        recipesViewModel.getRecipeRating(recipeId.toString())
//    }
    var isFavorite by remember { mutableStateOf(isFavorite) }

    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        onClick = onClick

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // recipe image
//            AsyncImage(
//                model = recipe.image,
//                contentDescription = recipe.title,
//                modifier = Modifier
//                    .height(200.dp)
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(16.dp)),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.height(8.dp))
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
                    .height(160.dp)               // 列表里用较小高度
            )
            Spacer(Modifier.height(4.dp))

//            Box(Modifier.fillMaxWidth().height(180.dp)) {
//                AsyncImage(
//                    model = recipe.image,
//                    contentDescription = recipe.title,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize()
//                )
//
//            }
            // title
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleMedium,
            )
//            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // star icon and rating
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        tint = Color(0xFFFFD700), // golden
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Vote", //
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Gray,
                            fontSize = 20.sp //
                        )
                    )
                }

                //收藏按钮
//                IconButton(
//                    onClick = { /* add */ },
//                    modifier = Modifier.size(40.dp) // 可以统一 IconButton 大小
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.FavoriteBorder,
//                        contentDescription = "Favorite Icon",
//                        tint = Color.Gray
//                    )
//                }
            }
        }
    }
}