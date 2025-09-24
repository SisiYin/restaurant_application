package com.example.restaurantapplication.ui.appbars

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.restaurantapplication.R
import com.example.restaurantapplication.viewmodel.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    userViewModel: UserViewModel = viewModel()
    ) {
    var expanded by remember { mutableStateOf(false) }
//    val isLoggedIn by userViewModel.isLoggedIn.collectAsState()  // ✅ 监听 StateFlow
//    val user = FirebaseAuth.getInstance().currentUser
//    val userId = user?.uid
    val userId = "123456"
    var showLogoutDialog by remember { mutableStateOf(false) } // 控制弹出对话框
    val context = LocalContext.current



    TopAppBar(
        title = {
            //Text("LOGO")
            Image(
                painter = painterResource(id = R.drawable.logo), // 把你的 logo 放到 res/drawable/logo.png
                contentDescription = "Logo",
                modifier = Modifier
                    .height(32.dp)           // 28~32dp 比较合适
                    .clip(RoundedCornerShape(4.dp)),
//                    .clickable { navController.navigate("home") }, // 可选：点 logo 回首页
                contentScale = ContentScale.Fit
            )
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More Options")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        expanded = false
                        navController.navigate("settings") // 跳转到设置页
                    }
                )
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = {
                        expanded = false
                        navController.navigate("info") // 跳转到信息页
                    }
                )
                if (userId != null) {
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = {
                            expanded = false
                            showLogoutDialog = true // 弹出确认退出对话框
                        }
                    )
                } else {
                    DropdownMenuItem(
                        text = { Text("Login") },
                        onClick = {
                            expanded = false
                            navController.navigate("login")
                        }
                    )
                }
            }
        }
    )
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Confirm Logout") },
            text = { Text("Are you sure you want to log out?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        userViewModel.logout()
                        showLogoutDialog = false
                        Toast.makeText(context, "Successfully logged out", Toast.LENGTH_SHORT).show() // 提示退出成功
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}