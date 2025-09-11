package com.example.restaurantapplication.ui.appbars

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.restaurantapplication.viewmodel.UserViewModel
//
//@Composable
//fun DetailBottomBar(
//    navController: NavController,
//    userId: String?,
//    recipeId: Int,
//    title: String,
//    image: String,
//    userViewModel: UserViewModel = viewModel(),
//    modifier: Modifier = Modifier
//) {
//    val backStackEntry = navController.currentBackStackEntryAsState()
//    val context = LocalContext.current
//
//    // Tab items for navigation
//    val tabs = listOf(
//        TabItem("Home", Icons.Filled.Home, route = "home"),
//        TabItem("Favorite", Icons.Filled.Favorite, route = "favorites"),
//        TabItem("Cart", Icons.Filled.ShoppingCart, route = "cart")
//    )
//
//    //var buttonColor by remember { mutableStateOf(Color(0xFFFFA500)) }
//
//    BottomAppBar(
//        modifier = modifier.fillMaxWidth()
//    ) {
//        // First two buttons are navigation buttons (Home and Cart)
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f),  // Allow navigation buttons to take available space
//            horizontalArrangement = Arrangement.Start // Align buttons to the left
//        ) {
//            // First two buttons are navigation buttons (Home and Cart)
//            tabs.forEach { tab ->
//                val selected = tab.route == backStackEntry.value?.destination?.route
//                NavigationBarItem(
//                    selected = selected,
//                    onClick = { navController.navigate(tab.route) },
//                    label = { Text(tab.label) },
//                    icon = { Icon(imageVector = tab.icon, contentDescription = null) },
//                    alwaysShowLabel = true
//                )
//            }
//        }
//
//        // To separate the cart button from "Add to Cart" button
//        //val interactionSource = remember { MutableInteractionSource() }
//        // Add "Add to Cart" button
//        TextButton(
//            onClick = {
//                if (userId != null){
//                    userViewModel.addToCart(
//                        userId = userId, recipeId = recipeId.toString(), title = title,
//                        image = image)
//                } else{
//                    Toast.makeText(
//                        context,
//                        "Login Please",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    navController.navigate("login") {
//                        popUpTo("login") { inclusive = true } // 清空登录栈
//                    }
//                }
//            },
//            modifier = Modifier
//                .background(Color(0xFFFFD700)) // Background color for button
//                .padding(horizontal = 16.dp, vertical = 8.dp) // Adjust padding for smaller size
//                .height(36.dp) // Set a fixed height for the button
//                .clip(RoundedCornerShape(16.dp)) // Rounded corners for the button
////                .pointerInput(Unit) {
////                    detectTapGestures(
////                        onPress = {
////                            buttonColor = Color(0xFFFF4500)
////                        },
////                        onRelease = {
////                            buttonColor = Color(0xFFFFA500)
////                        }
////                    )
////                }
//        ) {
//            Text(
//                text = "Add to Cart",
//                color = Color.White // Text color
//            )
//        }
//    }
//}

@Composable
fun DetailBottomBar(
    navController: NavController,
    userId: String?,
    recipeId: Int,
    title: String,
    image: String,
    userViewModel: UserViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val context = LocalContext.current
    val scheme = MaterialTheme.colorScheme

    val tabs = listOf(
        TabItem("Home", Icons.Filled.Home, route = "home"),
        TabItem("Favorite", Icons.Filled.Favorite, route = "favorites"),
        TabItem("Cart", Icons.Filled.ShoppingCart, route = "cart")
    )

    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = scheme.surface,          // 与 BottomPriceBar 的 Surface 对齐
        contentColor = scheme.onSurface,
        tonalElevation = 3.dp                     // 同样 3dp 的“柔和”阴影
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                val selected = tab.route == backStackEntry.value?.destination?.route
                NavigationBarItem(
                    selected = selected,
                    onClick = { navController.navigate(tab.route) },
                    label = { Text(tab.label) },
                    icon  = { Icon(tab.icon, contentDescription = null) },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor   = scheme.primary,
                        selectedTextColor   = scheme.primary,
                        unselectedIconColor = scheme.onSurfaceVariant,
                        unselectedTextColor = scheme.onSurfaceVariant,
                        indicatorColor      = scheme.secondaryContainer
                    )
                )
            }
        }

        // 与 SetMenu 的 Confirm 按钮保持一致：主色填充 + 圆角
        Button(
            onClick = {
                if (userId != null) {
                    userViewModel.addToCart(
                        userId = userId,
                        recipeId = recipeId.toString(),
                        title = title,
                        image = image
                    )
                } else {
                    Toast.makeText(context, "Login Please", Toast.LENGTH_SHORT).show()
                    navController.navigate("login") { popUpTo("login") { inclusive = true } }
                }
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = scheme.primary,
                contentColor   = scheme.onPrimary
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            modifier = Modifier.height(40.dp)
        ) {
            Text("Add to cart")
        }
    }
}
