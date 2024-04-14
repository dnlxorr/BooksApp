package com.example.booksapp.presentation.screens.landing_page.domain.repository

import com.example.booksapp.domain.model.Book
import com.example.booksapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun getBooks(): Response<List<Book>>

}