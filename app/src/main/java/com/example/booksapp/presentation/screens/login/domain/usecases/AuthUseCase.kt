package com.example.booksapp.presentation.screens.login.domain.usecases

import android.util.Log
import com.example.booksapp.BooksAppApplication
import com.example.booksapp.R
import com.example.booksapp.domain.repository.AuthenticationRepository
import com.example.booksapp.presentation.screens.login.domain.LoginError
import com.example.booksapp.presentation.screens.login.domain.Result


class AuthUseCase(private val authenticationRepository: AuthenticationRepository) {


    suspend fun login(email:String, password:String) : Result<String, LoginError> {
        val appName = BooksAppApplication.BooksAppApplicationContext.getString(R.string.app_name)
        Log.d("AppName",appName)
        authenticationRepository.createAppKey(appName).let {
            if (it.status=="ok"){
                authenticationRepository.createOAuthKey(email,password,it.appkey).let {
                    when (it) {
                        is Result.Success -> {
                            authenticationRepository.createSessionKey(it.data).let {
                                if (it.status == "ok") {
                                    Log.d("Auth", "SessKey successfuly created")
                                    return Result.Success(it.sesskey)
                                } else {
                                    Log.d("Auth", "SessKey NOT created!")
                                    return Result.Failure(LoginError.SERVER_ERROR)
                                }
                            }
                        }

                        is Result.Failure -> {
                            Log.d("Auth", "OAuthKey NOT created! ${it.error}")
                            return Result.Failure(LoginError.SERVER_ERROR)
                        }

                        is Result.Loading -> {}
                    }
                }
            }else{
                Log.d("Auth","AppKey NOT created!")
                return Result.Failure(LoginError.SERVER_ERROR)
            }
        }
        return Result.Failure(LoginError.SERVER_ERROR)
    }

}