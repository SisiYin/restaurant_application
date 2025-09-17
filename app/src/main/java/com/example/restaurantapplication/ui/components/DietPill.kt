package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.RiceBowl
import androidx.compose.material.icons.filled.SetMeal
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DietPill(diet: String) {
    val scheme = MaterialTheme.colorScheme
    val style = dietStyle(diet, scheme)

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(style.bg)
            .border(1.dp, style.stroke, RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Icon(
//            imageVector = style.icon,
//            contentDescription = diet,
//            modifier = Modifier.size(16.dp),
//            tint = style.fg
//        )
        Text(
            text = style.emoji,   // ✅ 用 Text 显示 emoji
            fontSize = 14.sp,
            modifier = Modifier.size(16.dp),
            color = style.fg
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = diet,
            style = MaterialTheme.typography.labelLarge,
            color = style.fg
        )
    }
}

private data class DietStyle(
    //val icon: ImageVector,
    val emoji: String,   // ✅ 从 ImageVector 换成 String
    val bg: Color,
    val fg: Color,
    val stroke: Color = bg.copy(alpha = 0.6f)
)
//
//private fun dietStyle(dietRaw: String, scheme: ColorScheme): DietStyle {
//    val diet = dietRaw.trim()
//    return when (diet.lowercase()) {
//        "maindish", "main dish" -> DietStyle(
//            icon   = Icons.Filled.DinnerDining,
//            bg     = scheme.primaryContainer,
//            fg     = scheme.onPrimaryContainer,
//            stroke = scheme.primaryContainer.copy(alpha = 0.6f)
//        )
//        "salad"   -> DietStyle(Icons.Filled.Grass,     scheme.tertiaryContainer,  scheme.onTertiaryContainer,  scheme.tertiaryContainer.copy(alpha = 0.6f))
//        "seafood" -> DietStyle(Icons.Filled.SetMeal,   scheme.secondaryContainer, scheme.onSecondaryContainer, scheme.secondaryContainer.copy(alpha = 0.6f))
//        "sushi"   -> DietStyle(Icons.Filled.RiceBowl,  scheme.secondaryContainer.copy(alpha = .7f), scheme.onSecondaryContainer, scheme.secondaryContainer.copy(alpha = 0.5f))
//        "dessert" -> DietStyle(Icons.Filled.Cake,      scheme.primaryContainer,   scheme.onPrimaryContainer,   scheme.primaryContainer.copy(alpha = 0.6f))
//        "drink"   -> DietStyle(Icons.Filled.LocalCafe, scheme.surfaceVariant,     scheme.onSurfaceVariant,     scheme.surfaceVariant.copy(alpha = 0.6f))
//        "vegan"   -> DietStyle(Icons.Filled.Spa,       scheme.tertiaryContainer.copy(alpha = 0.7f), scheme.onTertiaryContainer, scheme.tertiaryContainer.copy(alpha = 0.5f))
//        else -> {
//            val hue = (diet.hashCode() % 360).let { if (it < 0) it + 360 else it }.toFloat()
//            val bg = Color.hsl(hue, 0.25f, 0.92f)
//            val stroke = Color.hsl(hue, 0.25f, 0.75f)
//            val fg = Color.hsl(hue, 0.50f, 0.30f)
//            DietStyle(Icons.Filled.Label, bg, fg, stroke)
//        }
//    }
//}

private val dietColorMap = mapOf(
    //"maindish" to Color(0xFFFFE0B2),  // 橙色
    "main dish" to Color(0xFFFFE0B2),
    "salad"    to Color(0xFFC8E6C9),  // 绿色
    "sushi"    to Color(0xFFBBDEFB),  // 蓝色
    "dessert"  to Color(0xFFF8BBD0),  // 粉色
    "drink"    to Color(0xFFD1C4E9),  // 紫色
    "vegan"    to Color(0xFFDCEDC8),  // 浅绿
    "seafood"  to Color(0xFFB2EBF2),   // 青色
    "beef"     to Color(0xFFFFCDD2),  // 浅红
    "pork"     to Color(0xFFFFF9C4),  // 浅黄
    "chicken"  to Color(0xFFE1BEE7)   // 浅紫
)

private fun dietStyle(dietRaw: String, scheme: ColorScheme): DietStyle {
    val diet = dietRaw.trim().lowercase()

    // 1️⃣ 固定色优先
    val fixedColor = dietColorMap[diet]
    if (fixedColor != null) {
        return DietStyle(
            emoji = when (diet) {
//                "maindish", "main dish" -> Icons.Filled.DinnerDining
//                "salad"   -> Icons.Filled.Grass
//                "sushi"   -> Icons.Filled.RiceBowl
//                "dessert" -> Icons.Filled.Cake
//                "drink"   -> Icons.Filled.LocalCafe
//                "vegan"   -> Icons.Filled.Spa
//                "seafood" -> Icons.Filled.SetMeal
//                else -> Icons.Filled.
                "main dish" -> "🍽️"
                "salad"     -> "🥗"
                "sushi"     -> "🍣"
                "dessert"   -> "🍰"
                "drink"     -> "🍹"
                "vegan"     -> "🥦"
                "seafood"   -> "🐟"
                "beef"      -> "🐂"
                "pork"      -> "🐖"
                "chicken"   -> "🐓"
                else        -> "🏷️"
            },
            bg = fixedColor,
            fg = Color.Black,
            stroke = fixedColor.copy(alpha = 0.6f)
        )
    }

    // 2️⃣ 未知标签 → fallback 用 hashCode 随机色
    val hue = (diet.hashCode() % 360).let { if (it < 0) it + 360 else it }.toFloat()
    val sat = 0.5f + ((diet.hashCode() shr 3) % 40) / 100f   // 0.5 ~ 0.9
    val light = 0.7f + ((diet.hashCode() shr 6) % 20) / 100f // 0.7 ~ 0.9

    val bg = Color.hsl(hue, sat, light)
    val stroke = Color.hsl(hue, sat, (light - 0.15f).coerceAtLeast(0.3f))
    val fg = Color.hsl(hue, sat * 0.7f, (light - 0.35f).coerceAtLeast(0.2f))

    return DietStyle("🏷️", bg, fg, stroke)
}

