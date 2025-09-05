package com.example.restaurantapplication.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapplication.ui.appbars.BottomBar
import com.example.restaurantapplication.ui.appbars.ScreenTopBar
import com.example.restaurantapplication.ui.appbars.TopBar
import com.example.restaurantapplication.ui.screens.HomeScreen
import com.example.restaurantapplication.ui.screens.InfoScreen
import com.example.restaurantapplication.ui.screens.MenuScreen
import com.example.restaurantapplication.ui.screens.RecipeDetailScreen
import com.example.restaurantapplication.ui.screens.SetMenusDetailScreen
import com.example.restaurantapplication.ui.screens.SettingsScreen
import com.example.restaurantapplication.viewmodel.RecipesViewModel
import com.example.restaurantapplication.viewmodel.UserViewModel
import com.example.restaurantapplication.viewmodel.SetMenusViewModel

@Composable
fun AppScaffold(
    userViewModel: UserViewModel,
    recipesViewModel: RecipesViewModel,
) {
    val navController = rememberNavController() // create NavController
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    val snackbarHostState = remember { SnackbarHostState() } // create SnackbarHostState
    //val coroutineScope = rememberCoroutineScope()

    val setMenusViewModel: SetMenusViewModel = viewModel()

//    LaunchedEffect(Unit) {
//        userViewModel.initUserIfLoggedIn()
//    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            when (currentRoute) {
                "home" -> TopBar(navController)
                "list" -> ScreenTopBar("Result",navController)
//                "favorites" -> ScreenTopBar("Favorites",navController)
//                "profile" -> ScreenTopBar("Profile",navController)
                "settings" -> ScreenTopBar("Settings",navController)
                "info" -> ScreenTopBar("Info",navController)
//                "cart" -> ScreenTopBar("Cart",navController)
                "recipes/{recipeId}" -> ScreenTopBar("Recipes",navController)
                "setMenus/{id}" -> ScreenTopBar("Set Menu", navController)
//                "checkout" -> ScreenTopBar("Confirm Orders",navController)
//                "orders" -> ScreenTopBar("Orders",navController)
//                //"Sign In/Sign Up" -> ScreenTopBar("Login",navController)
//                "all_orders" -> ScreenTopBar("Manage All Orders",navController)
//                else -> TopBar(navController)
            }
        },
        // drawerContent = { DrawerContent(navController) },  // 侧边栏内容
        bottomBar = {
            when (currentRoute) {
                "recipes/{recipeId}" -> {
                }
                "setMenus/{id}" -> {
                }
                else -> {
                    BottomBar(navController)
                }
            }
        },
        // 底部导航栏

        content = { innerPadding ->
            val modifier = Modifier
                .padding(innerPadding)
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable(route = "home") { HomeScreen(navController, modifier, recipesViewModel) }
                composable(route = "list") { MenuScreen(navController, modifier, recipesViewModel) }

//                composable(route = "login") { LoginScreen(navController=navController,modifier=modifier,userViewModel=userViewModel) }
                composable(route = "info") { InfoScreen(modifier) }
                composable(route = "settings") { SettingsScreen(modifier) }
//                composable(route = "profile") { ProfileScreen(navController,modifier,userViewModel) }
//                composable(route = "cart") { CartScreen(navController=navController,modifier=modifier,userViewModel = userViewModel) }
//                composable(route = "checkout") { CheckoutScreen(navController=navController,modifier=modifier,userViewModel = userViewModel) }
//                composable(route = "orders") { OrderScreen(navController=navController,modifier=modifier,userViewModel = userViewModel) }
////                composable(route = "all_orders") { AdminOrderScreen(modifier=modifier) }
//                composable(route = "favorites") { FavoriteScreen(navController,modifier,userViewModel,recipesViewModel) }
                composable("recipes/{recipeId}") { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getString("recipeId")?.toInt() ?: 0
                    RecipeDetailScreen(
                        navController = navController,
                        modifier = modifier,
                        recipeId = recipeId,
                        recipesViewModel = recipesViewModel,
                        userViewModel = userViewModel
                    )
                }
                composable("setMenus/{id}"){ backStack ->
                    val id = backStack.arguments?.getString("id")!!
                    SetMenusDetailScreen(
                        navController = navController,
                        modifier = modifier,
                        setId = id,
                        recipesViewModel = recipesViewModel,
                        setMenusViewModel = setMenusViewModel,
                        onDone = {  } // 或跳到结算页
                    )
                }

            }
        }
    )
}

