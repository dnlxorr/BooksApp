package com.example.booksapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val ownerPrefs: OwnerPrefs,
)

@Serializable
data class OwnerPrefs(
    val oCoverImg: String?,
    val title: String,
)

@Serializable
data class AllBooks(
    val books: List<Book>
)

@Serializable
data class BooksResponse(
    val allBooks: AllBooks
)