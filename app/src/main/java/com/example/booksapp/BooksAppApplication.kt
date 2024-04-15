package com.example.booksapp

import android.app.Application
import android.content.Context
import com.example.booksapp.di.appModule
import com.example.booksapp.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin

class BooksAppApplication:Application() {

    companion object{
        lateinit var BooksAppApplicationContext:Context
    }
    override fun onCreate(){
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BooksAppApplication)
            modules(appModule, useCaseModule)
        }
        BooksAppApplicationContext = this
    }
}