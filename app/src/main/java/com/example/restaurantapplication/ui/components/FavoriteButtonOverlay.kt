package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButtonOverlay(
    isFavorite: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    inset: Dp = 12.dp,                            // 离边缘的“安全边距”
    size: Dp = 40.dp,                             // 按钮外径
    bgAlpha: Float = 0.7f,                        // 半透明底色强度
    usePrimaryWhenOn: Boolean = true              // 选中时是否用主题主色
) {
    Box(
        modifier = modifier

            .padding(all = inset)                 // 先整体缩进，避免被圆角吃掉
    ) {
        IconButton(
            onClick = onToggle,
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(
                    MaterialTheme.colorScheme.surface.copy(alpha = bgAlpha)
                )
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.25f),
                    CircleShape
                )
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Favorite",
                tint = if (isFavorite) {
                    if (usePrimaryWhenOn) MaterialTheme.colorScheme.primary else Color.Red
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}
