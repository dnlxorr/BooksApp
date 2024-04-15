package com.example.booksapp.presentation.screens.landing_page.domain.usecase

import com.example.booksapp.presentation.screens.landing_page.domain.repository.BooksRepository

class GetBooks (private val repository: BooksRepository) {
    suspend operator fun invoke() = repository.getBooks()

}