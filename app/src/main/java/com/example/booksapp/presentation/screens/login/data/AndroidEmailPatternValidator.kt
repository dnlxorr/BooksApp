package com.example.booksapp.presentation.screens.login.data

import android.util.Patterns
import com.example.booksapp.presentation.screens.login.domain.usecases.EmailValidatorUseCase

class AndroidEmailPatternValidator:EmailValidatorUseCase {
    override fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}