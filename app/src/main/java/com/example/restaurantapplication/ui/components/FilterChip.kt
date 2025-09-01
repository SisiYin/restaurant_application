package com.example.restaurantapplication.ui.components

import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FilterChip(category: String, selected: Boolean,onClick: () -> Unit) {
    ElevatedFilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(category) },
        modifier = Modifier
    )
}