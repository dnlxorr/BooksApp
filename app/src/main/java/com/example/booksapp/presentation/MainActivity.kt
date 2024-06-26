package com.example.booksapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.booksapp.presentation.navigation.AppNavigation
import com.example.booksapp.presentation.screens.login.viewmodel.LoginViewModel
import com.example.booksapp.presentation.ui.theme.BooksAppTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    //    private val viewModel by viewModel<AuthenticationViewModel>()   // get the instance of the viewmodel in NON-Compose projects
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksAppTheme {
//                val viewModel = getViewModel<LoginViewModel>()  // get the instance of the viewmodel in Compose projects
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navHostController = rememberNavController()
                    AppNavigation(navHostController)
                }
            }
        }
    }
}