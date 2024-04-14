package com.example.booksapp.presentation.screens.landing_page.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.booksapp.domain.model.Book

@Composable
fun BookCard(book: Book){

    Card(
        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                model = book.image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                text = book.name,
                fontWeight = FontWeight.Bold,
            )

        }

    }

}