package com.example.booksapp.presentation.screens.login.data

import android.util.Patterns
import com.example.booksapp.presentation.screens.login.domain.usecases.EmailPatternValidator

class AndroidEmailPatternValidator:EmailPatternValidator {
    override operator fun invoke(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}