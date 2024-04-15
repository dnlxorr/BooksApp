package com.example.booksapp.domain.model

import kotlinx.serialization.json.Json

object JsonSerializer {
    val jsonSerializer = Json{
        ignoreUnknownKeys = true
    }
}