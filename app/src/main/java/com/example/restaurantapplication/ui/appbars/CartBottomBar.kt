package com.example.restaurantapplication.ui.appbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(tonalElevation = 3.dp) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: Total
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ) {
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
