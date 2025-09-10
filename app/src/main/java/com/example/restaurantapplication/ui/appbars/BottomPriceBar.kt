package com.example.restaurantapplication.ui.appbars

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapplication.ui.util.EuroText
import com.example.restaurantapplication.ui.util.euro
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomPriceBar(
    base: Int,
    up: Int,
    total: Int,
    enabled: Boolean,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false
) {
    var showSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    // 底部条（只显示 Total + Confirm；点“Total”或图标展开明细）
    Surface(tonalElevation = 3.dp) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 可点击展开明细的 Total 区域
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { showSheet = true }
            ) {
                Text("Total", style = MaterialTheme.typography.labelLarge)
                EuroText(total, style = MaterialTheme.typography.titleMedium)
            }

            IconButton(onClick = { showSheet = true }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Show breakdown"
                )
            }

            Button(onClick = onConfirm, enabled = enabled) {
                Text("Confirm • ${euro(total)}")
            }
        }
    }

    // 明细：在底栏“上方”弹出
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            dragHandle = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { showSheet = false }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown, // 向下箭头
                            contentDescription = "Close breakdown",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        )  {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Price breakdown", style = MaterialTheme.typography.titleMedium)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Base"); EuroText(base)
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Upcharges"); EuroText(up)
                }
                Divider(Modifier.padding(vertical = 6.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total"); EuroText(total)
                }
                Spacer(Modifier.height(8.dp))

            }
        }
    }
}
