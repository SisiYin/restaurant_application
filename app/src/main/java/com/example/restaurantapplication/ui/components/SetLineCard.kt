package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantapplication.data.model.CartLine
import com.example.restaurantapplication.ui.screens.QtyButtons
import com.example.restaurantapplication.ui.util.euro
import com.example.restaurantapplication.viewmodel.CartViewModel

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
                        text = euro(line.priceCents),
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
