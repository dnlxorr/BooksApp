package com.example.booksapp.presentation.screens.login.components

import AppScreen
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.booksapp.presentation.screens.login.viewmodel.LoginViewModel
import com.example.booksapp.presentation.screens.login.domain.Result
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel


@Composable
fun Login(navHostController: NavHostController){

    Log.d("Login","Checking Login process")
    val viewModel = getViewModel<LoginViewModel>()


    when (val loginResponse = viewModel.loginFlow) {
        is Result.Failure<*, *> -> Toast.makeText(
            LocalContext.current,
            loginResponse.error.toString(),
            Toast.LENGTH_LONG
        ).show()

        is Result.Success<*, *> -> {
            Log.d("Login","Login success!")
            LaunchedEffect(Unit) {
                navHostController.navigate(route = AppScreen.LandingPage.route)
            }
        }
        null -> {
            Toast.makeText(
                LocalContext.current,
                "Context null Login",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}