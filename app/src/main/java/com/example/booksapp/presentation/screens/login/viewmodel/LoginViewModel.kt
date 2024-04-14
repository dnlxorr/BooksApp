package com.example.booksapp.presentation.screens.login.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.presentation.screens.login.data.AndroidEmailPatternValidator
import com.example.booksapp.presentation.screens.login.domain.LoginError
import com.example.booksapp.presentation.screens.login.domain.PasswordError
import com.example.booksapp.presentation.screens.login.domain.usecases.EmailPatternValidator
import com.example.booksapp.presentation.screens.login.domain.usecases.PasswordValidatorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.booksapp.presentation.screens.login.domain.Result
import com.example.booksapp.presentation.screens.login.domain.usecases.AuthUseCase
import com.example.booksapp.presentation.screens.login.domain.usecases.ValidateEmailUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.inject

class LoginViewModel(
    private val authUseCase: AuthUseCase
):ViewModel() {


    private val emailValidatorUseCase = AndroidEmailPatternValidator()
    private val passwordValidatorUseCase = PasswordValidatorUseCase()
//    private val authUseCase = AuthUseCase()

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMsg: MutableState<String> = mutableStateOf("")

    var isEnabledLoginButton = false

    var loginFlow by mutableStateOf <Result<String,LoginError>?>(null)
//    val loginFlow: StateFlow<Result<String, LoginError>?> = _loginFlow


    fun login() = viewModelScope.launch {
        val result = authUseCase.login(email.value,password.value)
        loginFlow = result
    }

    fun enabledLoginButton(){
        isEnabledLoginButton = isEmailValid.value && isPasswordValid.value
    }

    fun validateEmail(){
        Log.d("Validating Email", email.value)
        if (emailValidatorUseCase(email.value)){
            isEmailValid.value = true
            emailErrorMsg.value = ""
        }else{
            isEmailValid.value = false
            emailErrorMsg.value = "Invalid email!"
        }
        enabledLoginButton()
    }

    fun validatePassword(): Result<Unit, PasswordError> {
        passwordValidatorUseCase(password.value).also {
            if (it is Result.Success ){
                isPasswordValid.value = true
                passwordErrorMsg.value = ""
            }else if (it is Result.Failure){
                when (it.error){
                    PasswordError.TOO_SHORT -> {
                        isPasswordValid.value = false
                        passwordErrorMsg.value = "use 8 characters or more"
                    }
                    PasswordError.NO_UPPERCASE -> {
                        isPasswordValid.value = false
                        passwordErrorMsg.value = "Password has no uppercase characters"
                    }
                    PasswordError.NO_DIGIT -> {
                        isPasswordValid.value = false
                        passwordErrorMsg.value = "Password has no digits"
                    }
                }
            }
            enabledLoginButton()

            return it
        }
    }

}





