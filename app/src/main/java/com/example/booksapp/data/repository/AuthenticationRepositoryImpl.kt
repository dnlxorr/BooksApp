package com.example.booksapp.data.repository

import android.util.Log
import com.example.booksapp.core.SessionDataStore
import com.example.booksapp.domain.model.AppKeyResponse
import com.example.booksapp.domain.model.JsonSerializer
import com.example.booksapp.domain.model.OAuthKeyResponse
import com.example.booksapp.domain.model.SessionKeyResponse
import com.example.booksapp.domain.repository.AuthenticationRepository
import com.example.booksapp.presentation.screens.login.domain.OAuthError
import com.example.booksapp.presentation.screens.login.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import io.ktor.http.parameters

class AuthenticationRepositoryImpl(private val client: HttpClient) : AuthenticationRepository {

    override suspend fun createAppKey(appName:String): AppKeyResponse {
        return try {
            val authenticationResponse = client.post {
                url(HttpRoutes.API_URL)
                method = HttpMethod.Post
                headers {
                    // Add headers if needed
                }
                parameters {
                    parameter("version", "1.47")
                    parameter("req", "createAppkey")
                    parameter("appname", appName)
                }
            }.body<String>()

            Log.d("Authentication Response", authenticationResponse)

            JsonSerializer.jsonSerializer.decodeFromString<AppKeyResponse>(authenticationResponse)
        }catch (e: RedirectResponseException){
            //3xx - responses
            println("Error: ${e.response.status.description} ")
            AppKeyResponse(0,"","","","")
        }catch (e: ClientRequestException){
            //4xx - responses
            println("Error: ${e.response.status.description} ")
            AppKeyResponse(0,"","","","")
        }catch (e: ServerResponseException){
            //5xx - responses
            println("Error: ${e.response.status.description} ")
            AppKeyResponse(0,"","","","")
        }catch (e: Exception){
            //3xx - responses
            println("Error: ${e.message} ")
            AppKeyResponse(0,"","","","")
        }
    }

    override suspend fun createOAuthKey(email:String, password:String, authenticationKey:String): Result<OAuthKeyResponse, OAuthError> {
        return try{
            val oAuthResponse = client.post {
                url("https://timetonic.com/live/api.php")
                method = HttpMethod.Post
                headers {
                    // Add headers if needed
                }
                parameters {
                    parameter("version","1.47")
                    parameter("req","createOauthkey")
                    parameter("login",email)
                    parameter("pwd",password)
                    parameter("appkey",authenticationKey)
                }
            }.body<String>()

            Log.d("OAuth Response", oAuthResponse)
            if (oAuthResponse.contains("nok")) {
                Result.Failure(OAuthError.INVALID_EMAIL_PASSWORD)
            }else{
                Result.Success(JsonSerializer.jsonSerializer.decodeFromString<OAuthKeyResponse>(oAuthResponse))
            }

        }catch (e: RedirectResponseException){
            //3xx - responses
            println("RedirectError: ${e.response.status.description} ")
            println("RedirectError: ${e.message} ")
//            OAuthKeyResponse(0,"","","","","")
            Result.Failure(OAuthError.INVALID_EMAIL_PASSWORD)
        }catch (e: ClientRequestException){
            //4xx - responses
            println("ClientError: ${e.response.status.description} ")
            println("ClientError: ${e.message} ")

//            OAuthKeyResponse(0,"","","","","")
            Result.Failure(OAuthError.INVALID_EMAIL_PASSWORD)
        }catch (e: ServerResponseException){
            //5xx - responses
            println("ServerError: ${e.response.status.description} ")
            println("ServertError: ${e.message} ")

//            OAuthKeyResponse(0,"","","","","")
            Result.Failure(OAuthError.INVALID_EMAIL_PASSWORD)
        }catch (e: Exception){
            //3xx - responses
            println("ExceptionError: ${e.message} ")
//            OAuthKeyResponse(0,"","","","","")
            Result.Failure(OAuthError.INVALID_EMAIL_PASSWORD)

        }
    }

    override suspend fun createSessionKey(loginResponse:OAuthKeyResponse): SessionKeyResponse {

        return try{
            val createSessionResponse = client.post {
                url("https://timetonic.com/live/api.php")
                method = HttpMethod.Post
                headers {
                    // Add headers if needed
                }
                parameters {
                    parameter("version","1.47")
                    parameter("req","createSesskey")
                    parameter("o_u",loginResponse.o_u)
                    parameter("o_c",loginResponse.o_u)
                    parameter("oauthkey",loginResponse.oauthkey)
                }
            }.body<String>()



            JsonSerializer.jsonSerializer.decodeFromString<SessionKeyResponse>(createSessionResponse).also {
                savesessionData(loginResponse.o_u, loginResponse.o_u,it.sesskey)
            }
        }catch (e: RedirectResponseException){
            //3xx - responses
            println("Error: ${e.response.status.description} ")
            SessionKeyResponse(0,"","","","")
        }catch (e: ClientRequestException){
            //4xx - responses
            println("Error: ${e.response.status.description} ")
            SessionKeyResponse(0,"","","","")
        }catch (e: ServerResponseException){
            //5xx - responses
            println("Error: ${e.response.status.description} ")
            SessionKeyResponse(0,"","","","")
        }catch (e: Exception){
            //3xx - responses
            println("Error: ${e.message} ")
            SessionKeyResponse(0,"","","","")
        }
    }

    private suspend fun savesessionData(ou:String, uc:String, sesskey:String){
        SessionDataStore.setOu(ou)
        SessionDataStore.setUc(uc)
        SessionDataStore.setSesskey(sesskey)
    }

}