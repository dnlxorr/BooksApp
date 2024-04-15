package com.example.booksapp.presentation.screens.landing_page.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.domain.model.Book
import com.example.booksapp.domain.model.Response
import com.example.booksapp.presentation.screens.landing_page.domain.usecase.LandingPageUseCase
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

class LandingPageViewModel(
    private val landingPageUseCase: LandingPageUseCase
):ViewModel() {

    var booksResponse by mutableStateOf<Response<List<Book>>?>(null)
//    var currentUser = authUseCases.getCurrentUser()

    init {
        getBooks()
    }
    fun getBooks() = viewModelScope.launch {
        booksResponse = Response.Loading
        landingPageUseCase.getBooks().let{ response->
            booksResponse = response
        }
    }

}