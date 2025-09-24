package com.example.restaurantapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantapplication.ui.components.SetMenuCard
import com.example.restaurantapplication.viewmodel.SetMenusViewModel

@Composable
fun SetMenuScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    setMenusViewModel: SetMenusViewModel
) {
    var searchWord by remember { mutableStateOf("") }
    val filteredSets by remember(searchWord, setMenusViewModel.sets) {
        derivedStateOf {
            val keyword = searchWord.trim()
            if (keyword.isEmpty()) {
                setMenusViewModel.sets
            } else {
                setMenusViewModel.sets.filter { set ->
                    set.name.contains(keyword, ignoreCase = true)
                }
            }
        }
    }

    Column(modifier = modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 16.dp)){
        OutlinedTextField(
            value = searchWord,
            onValueChange = { searchWord = it },
            placeholder = { Text("Search Dish") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredSets, key = { it.id }) { set ->
                SetMenuCard(set = set, navController = navController, setMenusViewModel = setMenusViewModel)
            }
        }
    }

}
