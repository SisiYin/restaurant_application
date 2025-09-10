package com.example.restaurantapplication.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import java.text.NumberFormat
import java.util.Currency

/** 把“分”格式化为欧元字符串，例如 1990 -> €19.90 */
fun euro(cents: Int): String {
    val fmt = NumberFormat.getCurrencyInstance().apply {
        currency = Currency.getInstance("EUR")
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return fmt.format(cents / 100.0)
}

/** 直接渲染金额 Text */
@Composable
fun EuroText(
    cents: Int,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    val fmt = remember {
        NumberFormat.getCurrencyInstance().apply {
            currency = Currency.getInstance("EUR")
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }
    }
    Text(fmt.format(cents / 100.0), style = style)
}
