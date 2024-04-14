package com.example.booksapp.presentation.screens.landing_page

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.booksapp.presentation.components.DefaultTopBar
import com.example.booksapp.presentation.screens.landing_page.components.GetBooks
import com.example.booksapp.presentation.screens.landing_page.components.LandingPageBottomBar
import com.example.booksapp.presentation.screens.landing_page.components.LandingPageContent
import com.example.booksapp.presentation.screens.login.LoginScreen
import com.example.booksapp.presentation.screens.login.components.LoginBottomBar
import com.example.booksapp.presentation.screens.login.components.LoginContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LandingPageScreen(){
    Scaffold(
        topBar = { DefaultTopBar(title = "Landing Page", upAvailable = false)},
        content = {
            GetBooks()
        },
        bottomBar = { LandingPageBottomBar() }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        // A surface container using the 'background' color from the theme
        LandingPageScreen()
    }
}