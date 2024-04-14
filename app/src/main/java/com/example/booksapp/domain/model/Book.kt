package com.example.booksapp.domain.model


data class Book(
    var id:String = "",
    var name: String = "",
    var image: String
){

    companion object {
        fun fromJson(data: String): Book = JsonSerializer.jsonSerializer.decodeFromString<Book>(data)
    }
}
