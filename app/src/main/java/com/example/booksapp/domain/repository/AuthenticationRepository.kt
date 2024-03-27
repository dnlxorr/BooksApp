package com.example.booksapp.domain.repository

import com.example.booksapp.domain.model.AppKeyResponse
import com.example.booksapp.domain.model.OAuthKeyResponse
import com.example.booksapp.domain.model.SessionKeyResponse

interface AuthenticationRepository {

    suspend fun createAppKey(appName:String) : AppKeyResponse
    suspend fun createOAuthKey(authenticationKey:String) : OAuthKeyResponse
    suspend fun createSessionKey(loginResponse:OAuthKeyResponse) : SessionKeyResponse
}