package com.example.restaurantapplication.data.repository

import com.example.restaurantapplication.data.source.FakeRecipesDataSource

class RecipesRepository {
    fun getAll() = FakeRecipesDataSource.recipes
}