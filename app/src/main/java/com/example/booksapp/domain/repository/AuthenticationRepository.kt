package com.example.booksapp.domain.repository

import com.example.booksapp.domain.model.AppKeyResponse
import com.example.booksapp.domain.model.OAuthKeyResponse
import com.example.booksapp.domain.model.SessionKeyResponse
import com.example.booksapp.presentation.screens.login.domain.Result
import com.example.booksapp.presentation.screens.login.domain.OAuthError

interface AuthenticationRepository {

    suspend fun createAppKey(appName:String) : AppKeyResponse
    suspend fun createOAuthKey(email:String,password:String, authenticationKey:String) : Result<OAuthKeyResponse,OAuthError>
    suspend fun createSessionKey(loginResponse:OAuthKeyResponse) : SessionKeyResponse
}