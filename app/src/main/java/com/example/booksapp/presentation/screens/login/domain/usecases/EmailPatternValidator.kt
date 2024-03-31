package com.example.booksapp.presentation.screens.login.domain.usecases

interface EmailPatternValidator {
    operator fun invoke(email:String):Boolean
}