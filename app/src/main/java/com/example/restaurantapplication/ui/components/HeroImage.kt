package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

//
//@Composable
//fun HeroImage(
//    url: String,
//    title: String,
//    isFavorite: Boolean,
//    onToggleFavorite: () -> Unit,
//    modifier: Modifier = Modifier,
//) {
//    Card( // ✅ 用 Card 管理圆角+阴影，避免 clip 吃掉按钮
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(2.dp),
//        modifier = modifier
//            .fillMaxWidth()
//            .heightIn(min = 180.dp, max = 280.dp)
//    ) {
//        Box {
//            AsyncImage(
//                model = url,
//                contentDescription = title,
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
//
//            Box(
//                Modifier
//                    .matchParentSize()
//                    .background(
//                        Brush.verticalGradient(
//                            0f to Color.Transparent,
//                            0.7f to Color.Transparent,
//                            1f to Color.Black.copy(alpha = 0.20f)
//                        )
//                    )
//            )
//
//            IconButton(
//                onClick = onToggleFavorite,
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .padding(top = 12.dp, end = 12.dp) // ✅ 往里缩，避免贴边
//                    .size(40.dp)
//                    .clip(CircleShape)
//                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f))
//            ) {
//                Icon(
//                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
//                    contentDescription = "Favorite",
//                    tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface
//                )
//            }
//        }
//    }
//}

@Composable
fun HeroImage(
    url: String,
    title: String,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier,   // ✅ 由调用方决定大小
    cornerRadius: Dp = 16.dp,        // ✅ 圆角可调
    showGradient: Boolean = true     // ✅ 控制是否显示渐变遮罩
) {
    Card(
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
    ) {
        Box {
            AsyncImage(
                model = url,
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (showGradient) {
                Box(
                    Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                0f to Color.Transparent,
                                0.7f to Color.Transparent,
                                1f to Color.Black.copy(alpha = 0.20f)
                            )
                        )
                )
            }

            IconButton(
                onClick = onToggleFavorite,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp, end = 12.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f))
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


