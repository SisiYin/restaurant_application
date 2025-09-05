package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.restaurantapplication.data.model.DietCategory
import com.example.restaurantapplication.data.model.SetMenu
import com.example.restaurantapplication.viewmodel.SetMenusViewModel

@Composable
fun SetMenuCard(
    set: SetMenu,
    navController: NavController,
    setMenusViewModel: SetMenusViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {

            // 封面图
            AsyncImage(
                model = set.imageUrl,
                contentDescription = set.name,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(8.dp))

            // 标题 + 介绍
            Text(set.name, style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(8.dp))

            Text(
                text = set.summary,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )

            Spacer(Modifier.height(12.dp))

            // 进入详情的小按钮/箭头
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        setMenusViewModel.startSet(set.id)
                        navController.navigate("setMenus/${set.id}")
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Detail", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.width(4.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
        }
    }
}


