package com.example.booksapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.booksapp.presentation.screens.landing_page.LandingPageScreen

//fun NavGraphBuilder.landingNavGraph(){
//    navigation(
//        route = Graph.LANDING_PAGE,
//        startDestination = LandingPageScreen.LandingPage.route
//    ){
//        composable(route = LandingPageScreen.LandingPage.route){
//            LandingPageScreen()
//        }
//    }
//}
//
//sealed class LandingPageScreen(val route:String) {
//    object LandingPage:LandingPageScreen(route = "login")
//}