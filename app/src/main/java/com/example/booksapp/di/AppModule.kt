package com.example.booksapp.di

import com.example.booksapp.data.repository.AuthenticationRepositoryImpl
import com.example.booksapp.data.repository.BooksRepositoryImpl
import com.example.booksapp.domain.repository.AuthenticationRepository
import com.example.booksapp.presentation.screens.landing_page.components.GetBooks
import com.example.booksapp.presentation.screens.landing_page.domain.repository.BooksRepository
import com.example.booksapp.presentation.screens.landing_page.domain.usecase.GetBooks
import com.example.booksapp.presentation.screens.landing_page.domain.usecase.LandingPageUseCase
import com.example.booksapp.presentation.screens.landing_page.viewmodel.LandingPageViewModel
import com.example.booksapp.presentation.screens.login.domain.usecases.AuthUseCase
import com.example.booksapp.presentation.screens.login.viewmodel.LoginViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single{
        HttpClient(Android){
            install(ContentNegotiation){
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
    singleOf(::AuthenticationRepositoryImpl){bind< AuthenticationRepository>() }
    single<BooksRepository>{
        BooksRepositoryImpl(get())
    }
    viewModelOf(::LoginViewModel)
    viewModelOf(::LandingPageViewModel)
}

val useCaseModule = module{
    single{
        AuthUseCase(get())
    }
    single{
        LandingPageUseCase(getBooks = GetBooks(get()))
    }
}