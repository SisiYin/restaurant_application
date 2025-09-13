package com.example.restaurantapplication.ui.appbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapplication.ui.util.EuroText
import com.example.restaurantapplication.ui.util.euro

@Composable
fun CartBottomBar(
    total: Int,                    // cents
    enabled: Boolean,
    allSelected: Boolean,          // ✅ 是否全选
    onToggleAll: () -> Unit,       // ✅ 点击全选
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),   // ✅ 和其它统一高度
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 3.dp
    )  {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: Select All + Total
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = allSelected,
                    onCheckedChange = { onToggleAll() },
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text("Select All", style = MaterialTheme.typography.labelLarge)

                Spacer(Modifier.width(16.dp))

                Text("Total", style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.width(8.dp))
                EuroText(total, style = MaterialTheme.typography.titleMedium)
            }
            // Right: Submit
            Button(
                onClick = onSubmit,
                enabled = enabled,
            ) {
                Text("Submit")   // 你已有的 euro(total) 格式化函数
            }
        }
    }
}
