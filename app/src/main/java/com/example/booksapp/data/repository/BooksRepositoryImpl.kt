package com.example.booksapp.data.repository

import android.util.Log
import com.example.booksapp.core.SessionDataStore
import com.example.booksapp.domain.model.Book
import com.example.booksapp.domain.model.JsonSerializer
import com.example.booksapp.domain.model.Response
import com.example.booksapp.domain.model.SessionData
import com.example.booksapp.presentation.screens.landing_page.domain.repository.BooksRepository
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

class BooksRepositoryImpl(private val client: HttpClient):BooksRepository {
    override suspend fun getBooks(): Response<List<Book>> {
        return try {
            val sessionData = getSessionData()
            val authenticationResponse = client.post {
                url(HttpRoutes.BASE_URL)
                method = HttpMethod.Post
                headers {
                    // Add headers if needed
                }
                parameters {
                    parameter("version", "1.47")
                    parameter("req", "getAllBooks")
                    parameter("o_u",sessionData.ou)
                    parameter("u_c",sessionData.ou)
                    parameter("oauthkey",sessionData.sesskey)
                }
            }.body<String>()

            Log.d("Authentication Response","$authenticationResponse")

            Response.Success(JsonSerializer.jsonSerializer.decodeFromString<List<Book>>(authenticationResponse))
        }catch (e: RedirectResponseException){
            //3xx - responses
            println("Error: ${e.response.status.description} ")
            Response.Failure(e)
        }catch (e: ClientRequestException){
            //4xx - responses
            println("Error: ${e.response.status.description} ")
            Response.Failure(e)
        }catch (e: ServerResponseException){
            //5xx - responses
            println("Error: ${e.response.status.description} ")
            Response.Failure(e)

        }catch (e: Exception){
            //3xx - responses
            println("Error: ${e.message} ")
            Response.Failure(e)

        }
    }

    private suspend fun getSessionData() : SessionData {
        val ou = SessionDataStore.getOu()
        val uc = SessionDataStore.getUc()
        val sesskey = SessionDataStore.getSesskey()

        return SessionData(ou,uc,sesskey)
    }
}