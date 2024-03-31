package com.example.booksapp.presentation.screens.login.domain.usecases

class ValidateEmailUseCase(
    private val validator: EmailPatternValidator
) {
    operator fun invoke(email:String):Boolean {
        return validator.invoke(email)
    }
}