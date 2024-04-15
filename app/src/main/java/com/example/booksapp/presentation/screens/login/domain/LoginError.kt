package com.example.booksapp.presentation.screens.login.domain

enum class LoginError : Error {
    WRONG_USER_OR_PASSWORD,
    SERVER_ERROR,
    CLIENT_ERROR,
    LOADING
}