package com.example.restaurantapplication

import android.app.Application
import com.google.firebase.FirebaseApp
import android.util.Log

class RestaurantApp : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        AuthBootstrap.ensureSignedIn { user ->
            Log.d("Auth", "Signed in as ${user.uid}")
        }
    }
}
