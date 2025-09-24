package com.example.restaurantapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import com.example.restaurantapplication.ui.components.RecipeSection
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.restaurantapplication.R
import com.example.restaurantapplication.ui.components.LocalVideoPlayer
import com.example.restaurantapplication.ui.theme.Purple40


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    recipesViewModel: RecipesViewModel,
){
    var searchWord by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        recipesViewModel.fetchAllRecipes()
    }

    val recipes = recipesViewModel.allRecipes
    println("DEBUG: Recipes count = ${recipes.size}")

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            OutlinedTextField(
                value = searchWord,
                onValueChange = { searchWord = it },
                placeholder = { Text("Search Recipe") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            LocalVideoPlayer(
                rawResId = R.raw.restaurant_intro,        // 你的文件名 restaurant.mp4
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),                // 跟你原来保持一致
                autoPlay = true,
                loop = true,
                mute = true,                        // 餐厅 App 常见静音循环
                useController = false               // 不显示控制条
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { navController.navigate("menu") },
                    modifier = Modifier.weight(1f)
                ) { Text("View full menu") }

                OutlinedButton(
                    onClick = { navController.navigate("setmenu") /* 或 set menus 列表 */ },
                    modifier = Modifier.weight(1f)
                ) { Text("Set menus") }
            }
        }

//        item {
//            RecipeSection("Newest", recipes.take(4), navController, recipesViewModel)
//        }
//
//        item {
//            RecipeSection("Popular", recipes.shuffled().take(6), navController, recipesViewModel)
//        }

        item {
            Text("Latest Notice", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2E3A87)), // 深蓝背景
                shape = RoundedCornerShape(16.dp)
            ) {


                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 左侧图片
                    Image(
                        painter = painterResource(id = R.drawable.welcome), // 你上传的 WELCOME 图片
                        contentDescription = "Welcome",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.width(16.dp))

                    // 右侧文字
                    Column {
//                        Text(
//                            "Latest Notice",
//                            style = MaterialTheme.typography.titleLarge.copy(
//                                color = Color.White,
//                                fontWeight = FontWeight.Bold
//                            )
//                        )
//                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Tervetuloa Okiniirin sivustolle!\n" +
                                    "On ilo toivottaa sinut tervetulleeksi virallisille verkkosivuillemme.\n\n" +
                                    "Täältä löydät ruokalistamme, voit varata pöydän, tilata ruokaa mukaan ja pysyä ajan tasalla uutisistamme.",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                        )
                    }
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Contact Us", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                    Spacer(Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Place, contentDescription = null, tint = Purple40)
                        Spacer(Modifier.width(8.dp))
                        Text("Pakkahuoneenkatu 22, 90100 Oulu, Finland")
                    }

                    Spacer(Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Email, contentDescription = null, tint = Purple40)
                        Spacer(Modifier.width(8.dp))
                        Text("info@okiniiri.fi")
                    }

                    Spacer(Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = Purple40)
                        Spacer(Modifier.width(8.dp))
                        Text("+358 449751468")
                    }
                }
            }
        }


    }
}