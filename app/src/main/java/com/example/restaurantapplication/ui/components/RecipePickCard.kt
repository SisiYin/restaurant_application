package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantapplication.data.model.Recipe
import com.example.restaurantapplication.ui.util.euro
import java.text.NumberFormat
import java.util.Currency

@Composable
fun RecipePickCard(
    recipe: Recipe,
    checked: Boolean,
    enabled: Boolean,
    extraCents: Int = 0,               // ← 新增
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
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    alpha = if (enabled || checked) 1f else 0.5f
                )
                // 右上角select
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

                // 左下角加价角标（存在且>0才显示）
                if (extraCents > 0) {
                    Surface(
                        shape = RoundedCornerShape(999.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = if (checked) 1f else 0.85f),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "+${euro(extraCents)}",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
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

//fun euro(cents: Int): String {
//    val fmt = NumberFormat.getCurrencyInstance().apply {
//        currency = Currency.getInstance("EUR")
//        minimumFractionDigits = 2
//        maximumFractionDigits = 2
//    }
//    return fmt.format(cents / 100.0)
//}
