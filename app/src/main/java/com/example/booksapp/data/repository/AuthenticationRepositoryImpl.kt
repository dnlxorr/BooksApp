package com.example.booksapp.data.repository

import com.example.booksapp.R
import com.example.booksapp.domain.model.AppKeyResponse
import com.example.booksapp.domain.model.OAuthKeyResponse
import com.example.booksapp.domain.model.SessionKeyResponse
import com.example.booksapp.domain.repository.AuthenticationRepository
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
import kotlinx.serialization.json.Json
import org.koin.android.BuildConfig

class AuthenticationRepositoryImpl(private val client: HttpClient) : AuthenticationRepository {

    private val jsonSerializer = Json{
        ignoreUnknownKeys = true
    }
    override suspend fun createAppKey(appName:String): AppKeyResponse {
        return try {
            val authenticationResponse = client.post {
                url(HttpRoutes.BASE_URL)
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

            jsonSerializer.decodeFromString<AppKeyResponse>(authenticationResponse)
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

    override suspend fun createOAuthKey(authenticationKey:String): OAuthKeyResponse {
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
                    parameter("login","android.developer@timetonic.com")
                    parameter("pwd","Android.developer1")
                    parameter("appkey",authenticationKey)
                }
            }.body<String>()
            jsonSerializer.decodeFromString<OAuthKeyResponse>(oAuthResponse)

        }catch (e: RedirectResponseException){
            //3xx - responses
            println("Error: ${e.response.status.description} ")
            OAuthKeyResponse(0,"","","","","")
        }catch (e: ClientRequestException){
            //4xx - responses
            println("Error: ${e.response.status.description} ")
            OAuthKeyResponse(0,"","","","","")
        }catch (e: ServerResponseException){
            //5xx - responses
            println("Error: ${e.response.status.description} ")
            OAuthKeyResponse(0,"","","","","")
        }catch (e: Exception){
            //3xx - responses
            println("Error: ${e.message} ")
            OAuthKeyResponse(0,"","","","","")
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

            jsonSerializer.decodeFromString<SessionKeyResponse>(createSessionResponse)
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

}