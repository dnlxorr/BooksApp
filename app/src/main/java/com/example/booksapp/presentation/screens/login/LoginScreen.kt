package com.example.booksapp.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.booksapp.presentation.screens.login.components.Login
import com.example.booksapp.presentation.screens.login.components.LoginBottomBar
import com.example.booksapp.presentation.screens.login.components.LoginContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navHostController: NavHostController = rememberNavController()) {

    Scaffold(
        topBar = {},
        content = {
            LoginContent()
        },
        bottomBar = { LoginBottomBar() }
    )
    Login(navHostController = navHostController)

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        // A surface container using the 'background' color from the theme
            LoginScreen(rememberNavController())
    }
}