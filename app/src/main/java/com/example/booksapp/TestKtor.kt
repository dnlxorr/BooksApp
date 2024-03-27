package com.example.booksapp

import com.example.booksapp.domain.model.AppKeyResponse
import com.example.booksapp.domain.model.OAuthKeyResponse
import com.example.booksapp.domain.model.SessionKeyResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json





suspend fun main() {
    val client = HttpClient(Android){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    val jsonSerializer = Json{
        ignoreUnknownKeys = true
    }
    val authenticationResponse = client.post {
        url("https://timetonic.com/live/api.php")
        method = HttpMethod.Post
        headers {
            // Add headers if needed
        }
        parameters {
            parameter("version","1.47")
            parameter("req","createAppkey")
            parameter("appname","booksapp")
        }
//        setBody("PARAMETERS version=1.47&req=createAppkey&appname=booksapp")
    }.body<String>()

    val authenticationKey = jsonSerializer.decodeFromString<AppKeyResponse>(authenticationResponse).appkey

//    val responseString:String = response.body<String>().toString()
    println(authenticationKey)

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

    val loginResponse = jsonSerializer.decodeFromString<OAuthKeyResponse>(oAuthResponse)
    println("oauthkey: ${loginResponse.oauthkey}  o_u: ${loginResponse.o_u}")

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

    val sessKeyResponse = jsonSerializer.decodeFromString<SessionKeyResponse>(createSessionResponse)

    println("SessionKey: ${sessKeyResponse.sesskey}")
    client.close()
}
