package com.example.booksapp.presentation.screens.landing_page.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booksapp.domain.model.Book

@Composable
fun LandingPageContent(
    books: List<Book>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 50.dp)
    ){
        items(items = books) { book ->
            BookCard(book = book)
        }
    }
}