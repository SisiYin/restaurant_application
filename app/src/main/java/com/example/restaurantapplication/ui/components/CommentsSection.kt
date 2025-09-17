package com.example.restaurantapplication.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.restaurantapplication.viewmodel.CommentsViewModel
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.text.style.TextAlign
import com.example.restaurantapplication.ui.theme.StarYellow
import kotlinx.coroutines.launch

@SuppressLint("DefaultLocale")
@Composable
fun CommentsSection(
    dishId: String,
    commentsViewModel: CommentsViewModel,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    // Data
    val comments by commentsViewModel.commentsOf(dishId).collectAsState(initial = emptyList())
    val avg by commentsViewModel.ratingStat(dishId).collectAsState()
    val inputText = commentsViewModel.inputText
    val inputRating = commentsViewModel.inputRating

    Column {

//        // Top: average rating + count
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Icon(
//                imageVector = Icons.Filled.Star,
//                contentDescription = null,
//                tint = StarYellow,
//                modifier = Modifier.size(22.dp)
//            )
//            Spacer(Modifier.width(6.dp))
//            Text(
//                text = avg?.let { String.format("%.1f", it) } ?: "No rating yet",
//                style = MaterialTheme.typography.titleMedium
//            )
//            if (comments.isNotEmpty()) {
//                Spacer(Modifier.width(6.dp))
//                Text(
//                    text = "(${comments.size})",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                )
//            }
//        }

        Spacer(Modifier.height(12.dp))

        // Input area: rating + text + button
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(Modifier.padding(12.dp)) {

                StarRatingRow(
                    current = inputRating,
                    onSelect = { r -> commentsViewModel.updateRating(r) }
                )

                Spacer(Modifier.height(8.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    val maxChars = 199
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = {
                            if (it.length <= maxChars) {
                                commentsViewModel.updateText(it)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 120.dp), // 大约 5 行
                        placeholder = { Text("Write your comment...") },
                        maxLines = 4,
                        singleLine = false,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Start // 光标从顶部开始
                        )
                    )

                    // 字数统计
                    Text(
                        text = "${inputText.length} / $maxChars",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 12.dp, bottom = 8.dp)
                    )
                }

                Spacer(Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    val canSubmit = inputText.isNotBlank() || inputRating != null
                    Button(
                        onClick = { scope.launch { commentsViewModel.submit(dishId) } },
                        enabled = canSubmit
                    ) {
                        Text("Post / Update")
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(Modifier.height(8.dp))

        // Comments list
        if (comments.isEmpty()) {
            Text(
                "No comments yet. Be the first!",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                comments.forEach { c ->
                    CommentItem(
                        author = c.nickname ?: "Guest",
                        rating = c.rating,
                        text = c.text,
                        time = c.createdAt?.toDate()?.toString() ?: "",
                        canDelete = true,
                        onDelete = { scope.launch { commentsViewModel.delete(dishId, c.id) } }
                    )
                }
                Spacer(Modifier.height(80.dp)) // 如果你想保留底部留白
            }
        }
    }
}

/** Single comment item */
@Composable
private fun CommentItem(
    author: String,
    rating: Int?,
    text: String,
    time: String,
    canDelete: Boolean,
    onDelete: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(author, style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.width(8.dp))
            if (rating != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) { i ->
                        val filled = i < rating
                        Icon(
                            imageVector = if (filled) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = null,
                            tint = if (filled) StarYellow else MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            if (canDelete) {
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
            }
        }
        if (text.isNotBlank()) {
            Spacer(Modifier.height(6.dp))
            Text(text, maxLines = 6, overflow = TextOverflow.Ellipsis)
        }
        if (time.isNotBlank()) {
            Spacer(Modifier.height(6.dp))
            Text(
                time,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/** Row of star rating selection */
@Composable
private fun StarRatingRow(
    current: Int?,
    onSelect: (Int?) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Rating:", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.width(6.dp))
        (1..5).forEach { star ->
            val filled = (current ?: 0) >= star
            IconButton(onClick = { onSelect(if (current == star) null else star) }) {
                Icon(
                    imageVector = if (filled) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = "$star",
                    tint = if (filled) StarYellow else MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}
