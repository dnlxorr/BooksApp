package com.example.booksapp.presentation.screens.login.domain

enum class PasswordError: Error {
    TOO_SHORT,
    NO_UPPERCASE,
    NO_DIGIT
}