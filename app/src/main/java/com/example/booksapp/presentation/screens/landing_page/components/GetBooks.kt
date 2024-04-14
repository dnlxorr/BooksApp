package com.example.booksapp.presentation.screens.landing_page.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.booksapp.domain.model.Response
import com.example.booksapp.presentation.components.ProgressBar
import com.example.booksapp.presentation.screens.landing_page.viewmodel.LandingPageViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun GetBooks() {
    val viewModel =  getViewModel<LandingPageViewModel>()

    when (val response = viewModel.booksResponse) {
        //Show loading progress bar
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Failure<*> -> Toast.makeText(
            LocalContext.current,
            response.exception?.message ?: "Unknown error",
            Toast.LENGTH_LONG
        ).show()

        is Response.Success -> {
            LandingPageContent(books = response.data)
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