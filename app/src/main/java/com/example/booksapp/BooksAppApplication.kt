package com.example.booksapp

import android.app.Application
import com.example.booksapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BooksAppApplication:Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BooksAppApplication)
            modules(appModule)
        }
    }
}