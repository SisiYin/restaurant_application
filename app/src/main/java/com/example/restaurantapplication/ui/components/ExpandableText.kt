package com.example.restaurantapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ExpandableText(
    text: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    collapsedMaxLines: Int = 6,
    modifier: Modifier = Modifier
) {
    var isOverflowing by remember { mutableStateOf(false) }

    Box(modifier) {
        Text(
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else collapsedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { result ->
                // 检查是否被截断
                isOverflowing = result.hasVisualOverflow
            }
        )
    }

    if (isOverflowing) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { onToggle() }) {
                Text(text = if (expanded) "Less <<" else "More >>")
            }
        }
    }
}
