package com.example.booksapp.presentation.screens.login.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.booksapp.domain.repository.AuthenticationRepository
import com.example.booksapp.presentation.screens.login.domain.LoginError
import com.example.booksapp.presentation.screens.login.domain.PasswordError
import com.example.booksapp.presentation.screens.login.domain.usecases.EmailValidatorUseCase
import com.example.booksapp.presentation.screens.login.domain.usecases.PasswordValidatorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.booksapp.presentation.screens.login.domain.Result
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val passwordValidatorUseCase: PasswordValidatorUseCase
):ViewModel() {

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMsg: MutableState<String> = mutableStateOf("")

    var isEnabledLoginButton = false

    private val _loginFlow = MutableStateFlow<Result<Unit,LoginError>?>(null)
    val loginFlow: StateFlow<Result<Unit, LoginError>?> = _loginFlow

    fun validateEmail():Boolean{
        return emailValidatorUseCase.isValidEmail(email.value)
    }

    fun validatePassword(): Result<Unit, PasswordError> {
        return passwordValidatorUseCase.validatePassword(password.value)
    }

}





