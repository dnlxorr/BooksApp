package com.example.booksapp.presentation.screens.login.domain.usecases

import com.example.booksapp.presentation.screens.login.domain.PasswordError
import com.example.booksapp.presentation.screens.login.domain.Result


class PasswordValidatorUseCase {
    operator fun invoke(password:String): Result <Unit,PasswordError> {
        if(password.length<9){
            return Result.Failure(PasswordError.TOO_SHORT)
        }

        val hasUppercaseChar = password.any{it.isUpperCase()}
        if (!hasUppercaseChar){
            return Result.Failure(PasswordError.NO_UPPERCASE)
        }

        val hasDigit = password.any{it.isDigit()}
        if (!hasDigit){
            return Result.Failure(PasswordError.NO_DIGIT)
        }

        return Result.Success(Unit)

    }

}