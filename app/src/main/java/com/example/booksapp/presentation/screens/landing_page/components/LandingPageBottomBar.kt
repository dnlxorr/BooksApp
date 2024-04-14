package com.example.booksapp.presentation.screens.landing_page.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LandingPageBottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Landing Page", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            modifier = Modifier.clickable {
//                navHostController.navigate(route = AppScreen.SignUp.route)
            },
            text = "SIGN UP!",
            fontSize = 14.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )

    }
}