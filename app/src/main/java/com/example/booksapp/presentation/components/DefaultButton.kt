package com.example.booksapp.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksapp.presentation.ui.theme.BooksAppTheme
import com.example.booksapp.presentation.ui.theme.Purple80

@Composable
fun DefaultButton(
    modifier: Modifier,
    text:String,
    errorMsg: String = "",
    onClick: () -> Unit,
    color: Color = Purple80,
    textColor: Color = Color.White,
    enable: Boolean = true,
    icon: ImageVector = Icons.Default.ArrowForward
){
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        enabled = enable
    ) {
        Icon(imageVector = icon, contentDescription = "", tint = textColor)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text, fontSize = 18.sp, color = textColor)
    }
    Text(
        modifier = Modifier.padding(top = 5.dp),
        text = errorMsg,
        fontSize = 11.sp,
        color = Color.White
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    BooksAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
            DefaultButton(modifier = Modifier, text = "hola", onClick = { /*TODO*/ })
    }
}
