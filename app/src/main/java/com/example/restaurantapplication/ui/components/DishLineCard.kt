package com.example.restaurantapplication.ui.components

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantapplication.data.model.CartLine
import com.example.restaurantapplication.ui.util.euro
import com.example.restaurantapplication.viewmodel.CartViewModel

@Composable
fun DishLineCard(
    line: CartLine.DishLine,
    cartViewModel: CartViewModel,
    titleOf: (Int) -> String?

) {
    //勾选状态
    val ui by cartViewModel.uiState.collectAsState()
    val checked = ui.selectedIds.contains(line.id)

    Card {
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { cartViewModel.toggleSelect(line.id)  },
                modifier = Modifier.size(16.dp)
                    .padding(start = 0.dp)
            )
            Spacer(Modifier.width(8.dp))

            AsyncImage(
                model = line.imageUrl,
                contentDescription = line.title,
                contentScale = ContentScale.Crop,          // ✅ 裁剪填满容器
                modifier = Modifier
                    .size(80.dp)                           // ✅ 固定大小（统一宽高）
                    .clip(MaterialTheme.shapes.medium)     // 圆角
            )

            Spacer(Modifier.width(12.dp))
            // 右侧信息区（两行布局）
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Top,
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
                        onClick = { cartViewModel.remove(line.id) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Remove",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))

                // 第二行：单价 + 数量按钮
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = euro(line.priceCents),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )
                    QtyButtons(
                        lineId = line.id,
                        qty = line.qty,
                        onChange = cartViewModel::changeQty
                    )
                }
            }
        }
    }
}