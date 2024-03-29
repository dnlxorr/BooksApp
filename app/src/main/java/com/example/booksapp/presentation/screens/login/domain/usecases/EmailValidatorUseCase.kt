package com.example.booksapp.presentation.screens.login.domain.usecases

interface EmailValidatorUseCase {
    fun isValidEmail(email:String):Boolean
}