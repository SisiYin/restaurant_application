package com.example.restaurantapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // App Logo
//            Image(
//                painter = painterResource(id = R.drawable.logo_color),
//                contentDescription = "App Logo",
//                modifier = Modifier
//                    .size(150.dp)
//            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Foodie Genie",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Version 1.0.3",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))


        }

        item {
            SectionTitle("About the App")
            SectionText("Foodie Genie is a mobile app that brings personalized, high-end dining experiences directly to your door. Whether you're hosting a private dinner, a business event, or a family gathering, our app makes it easy to browse custom menus, book professional chefs, and arrange seamless culinary experiences. Specializing in Asian-inspired dishes, Foodie Genie focuses on using high-quality ingredients and offering expert chef services, transforming your home into a unique dining destination.")
        }
        item {
            SectionTitle("Developer")
            SectionText("Foodie Genie Group\nEmail: foodiegenie@foo.com")
        }

        item {
            SectionTitle("Privacy Policy")
            SectionText("We value your privacy. Your personal data is safe and never shared without permission.")
        }

    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun SectionText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}
