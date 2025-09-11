package com.example.restaurantapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.restaurantapplication.data.model.CartLine
import com.example.restaurantapplication.viewmodel.CartViewModel
import com.example.restaurantapplication.viewmodel.RecipesViewModel

@Composable
fun CartScreen(
    navController: NavController,
    recipesViewModel: RecipesViewModel,        // ✅ 新增
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    val ui by cartViewModel.uiState.collectAsState()

    if (ui.lines.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Your cart is empty.")
        }
    } else {
        // id -> title 解析器（英文名）
        val nameMap = remember(recipesViewModel.allRecipes) {
            recipesViewModel.allRecipes.associate { it.id to it.title }
        }

// 需要的解析器：(Int) -> String?
        val titleOf: (Int) -> String? = remember(nameMap) {
            { id: Int -> nameMap[id] }
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(ui.lines, key = { it.id }) { line ->
                when (line) {
                    is CartLine.SetMenuLine -> SetLineCard(line, cartViewModel,titleOf)
                    is CartLine.DishLine -> DishLineCard(line, cartViewModel)
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

//@Composable
//private fun SetLineCard(line: CartLine.SetMenuLine, cart: CartViewModel) {
//    Card {
//        Row(
//            Modifier
//                .padding(8.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // 图片更大
//            AsyncImage(
//                model = line.imageUrl,
//                contentDescription = line.title,
//                contentScale = ContentScale.Crop,          // ✅ 裁剪填满容器
//                modifier = Modifier
//                    .size(80.dp)                           // ✅ 固定大小（统一宽高）
//                    .clip(MaterialTheme.shapes.medium)     // 圆角
//            )
//
//            Spacer(Modifier.width(12.dp))
//
//            // 左侧：标题 + 单价
//            Column(Modifier.weight(1f)) {
//                Text(
//                    line.title,
//                    style = MaterialTheme.typography.titleMedium.copy(
//                        fontWeight = FontWeight.Bold
//                    )
//                )
//                Spacer(Modifier.height(8.dp))
//                Text(
//                    text = centsToEuro(line.priceCents) ,
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                )
//            }
//
//            // 右侧：数量控制 + 删除 + 小计
//            Column(horizontalAlignment = Alignment.Start) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    QtyButtons(
//                        lineId = line.id,
//                        qty = line.qty,
//                        onChange = cart::changeQty
//                    )
//                    IconButton(
//                        onClick = { cart.remove(line.id) },
//                        modifier = Modifier.size(32.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Delete,
//                            contentDescription = "Remove",
//                            modifier = Modifier.size(18.dp)
//                        )
//                    }
//                }
//                Spacer(Modifier.height(8.dp))
//
//                Text(
//                    text = " ${centsToEuro(line.priceCents * line.qty)}",
//                    style = MaterialTheme.typography.bodySmall,
//                    modifier = Modifier.padding(start=8.dp),
//                )
//            }
//        }
//    }
//}
@Composable
private fun SetLineCard(
    line: CartLine.SetMenuLine,
    cart: CartViewModel,
    titleOf: (Int) -> String?
) {
    Card {
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            // 左侧图片
            AsyncImage(
                model = line.imageUrl,
                contentDescription = line.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            Spacer(Modifier.width(12.dp))

            // 右侧信息区（三行布局）
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                // 第一行：标题 + 删除
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        line.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { cart.remove(line.id) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Remove",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // 第二行：summary + >>/<<
                var expanded by remember { mutableStateOf(false) }
                val summary = remember(line) { selectionSummary(line, titleOf) }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = summary,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = if (expanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    if (summary.isNotBlank()) {
                        Text(
                            text = if (expanded) "<<" else ">>",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .clickable { expanded = !expanded }
                        )
                    }
                }

                Spacer(Modifier.height(4.dp))

                // 第三行：单价 + 数量按钮
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = centsToEuro(line.priceCents),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )
                    QtyButtons(
                        lineId = line.id,
                        qty = line.qty,
                        onChange = cart::changeQty
                    )
                }
            }
        }
    }
}


/** 把 selections 转为菜名（英文）列表，用 · 连接；保持非 @Composable */
private fun selectionSummary(
    line: CartLine.SetMenuLine,
    titleOf: (Int) -> String?
): String {
    val ids: List<Int> = line.selections.values.flatten()
    val names = ids.mapNotNull(titleOf)
    return names.joinToString(" · ")
}

@Composable
private fun DishLineCard(line: CartLine.DishLine, cart: CartViewModel) {
    Card {
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = line.imageUrl,
                contentDescription = line.title,
                contentScale = ContentScale.Crop,          // ✅ 裁剪填满容器
                modifier = Modifier
                    .size(80.dp)                           // ✅ 固定大小（统一宽高）
                    .clip(MaterialTheme.shapes.medium)     // 圆角
            )

            Spacer(Modifier.width(12.dp))

            // 文本区：标题 + 单价（灰色小字），不再显示总价，避免重复
            Column(Modifier.weight(1f)) {
                Text(
                    line.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = centsToEuro(line.priceCents),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // 右侧紧凑数量区（占比更小）
            Row(verticalAlignment = Alignment.CenterVertically) {
                QtyButtons(
                    lineId = line.id,
                    qty = line.qty,
                    onChange = cart::changeQty
                )
                IconButton(
                    onClick = { cart.remove(line.id) },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Remove",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

/** 紧凑版数量按钮：更小的触控面积与图标，减少占位 */
@Composable
private fun QtyButtons(
    lineId: String,
    qty: Int,
    onChange: (String, Int) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = { onChange(lineId, (qty - 1).coerceAtLeast(0)) },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = "Decrease",
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = "$qty",
            modifier = Modifier.width(24.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        IconButton(
            onClick = { onChange(lineId, qty + 1) },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Increase",
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

private fun centsToEuro(cents: Int): String = "€%.2f".format(cents / 100f)
