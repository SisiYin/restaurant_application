package com.example.restaurantapplication.data.repository

import com.example.restaurantapplication.data.source.SetMenusDataSource

class SetMenusRepository {
    fun getAll() = SetMenusDataSource.sets
}