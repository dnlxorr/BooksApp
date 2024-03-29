package com.example.booksapp.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.booksapp.presentation.screens.login.components.LoginBottomBar
import com.example.booksapp.presentation.screens.login.components.LoginContent
import com.example.booksapp.presentation.ui.theme.BooksAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogingScreen(navHostController: NavHostController) {

    Scaffold(
        topBar = {},
        content = {
            LoginContent(navHostController)
        },
        bottomBar = { LoginBottomBar(navHostController) }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    BooksAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LogingScreen(rememberNavController())
        }
    }
}