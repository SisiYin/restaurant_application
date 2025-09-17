package com.example.restaurantapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.restaurantapplication.data.repository.FavoritesRepository
import com.example.restaurantapplication.ui.AppScaffold
import com.example.restaurantapplication.viewmodel.CartViewModel
import com.example.restaurantapplication.viewmodel.CommentsViewModel
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import com.example.restaurantapplication.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    val userViewModel: UserViewModel by viewModels()
    val commentsViewModel: CommentsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipesViewModel: RecipesViewModel by viewModels()
        val cartViewModel: CartViewModel by viewModels()

        setContent {
            AppScaffold(userViewModel, recipesViewModel, cartViewModel,commentsViewModel)
        }
    }
//    private val credentialManager by lazy { CredentialManager.create(this) }
//    val userViewModel: UserViewModel by viewModels()  // 使用 ViewModel 来管理用户状态
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //initiate
//        FirebaseApp.initializeApp(this)
//
//        userViewModel.setCredentialManager(credentialManager, this) // 传入 CredentialManager 和 context
//
//        val recipeViewModel: RecipesViewModel by viewModels()
//
//        Log.d("MainActivity", "userViewModel instance: ${userViewModel.hashCode()}")
//
//        setContent {
//            RestaurantApplicationTheme () {
//                AppScaffold(userViewModel, recipeViewModel)
//
//            }
//        }
//
//    }
}

