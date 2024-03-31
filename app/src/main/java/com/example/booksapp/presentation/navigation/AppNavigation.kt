package com.example.booksapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booksapp.presentation.screens.login.LoginScreen

@Composable
fun AppNavigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = AppScreen.Login.route){
        composable(route = AppScreen.Login.route){
            LoginScreen(navHostController)
        }
//        composable(route = AppScreen.SignUp.route){
//            SignUpScreen(navHostController = navHostController)
//        }
//        composable(route = AppScreen.Profile.route){
//            ProfileScreen(navHostController = navHostController)
//        }
    }

}