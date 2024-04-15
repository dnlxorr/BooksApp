package com.example.booksapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksapp.R
import com.example.booksapp.presentation.components.DefaultButton
import com.example.booksapp.presentation.components.DefaultTextField
import com.example.booksapp.presentation.screens.login.domain.Result
import com.example.booksapp.presentation.screens.login.viewmodel.LoginViewModel
import com.example.booksapp.presentation.ui.theme.Purple80
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun LoginContent() {


    val loginViewModel =  getViewModel<LoginViewModel>()
    val loginFlow = loginViewModel.loginFlow


    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(280.dp)
                .background(Purple80)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Control de XBox 360"
                )
                Text(text = "TIMETONIC API CONSUMER")
            }
        }
        Card(
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 200.dp),
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                Text(
                    modifier = Modifier.padding(
                        top = 30.dp,
                        bottom = 0.dp,
                        start = 0.dp,
                        end = 0.dp
                    ),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Please login to continue", fontSize = 12.sp, color = Color.Gray)
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = loginViewModel.email.value,
                    onValueChange = { value -> loginViewModel.email.value = value },
                    label = "Email",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = loginViewModel.emailErrorMsg.value,
                    validateField = {
                        loginViewModel.validateEmail()
                    }


                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = loginViewModel.password.value,
                    onValueChange = { loginViewModel.password.value = it },
                    label = "Password",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    hideText = true,
                    errorMsg = loginViewModel.passwordErrorMsg.value,
                    validateField = {
                        loginViewModel.validatePassword()
                    }
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    text = "Login",
                    onClick = { loginViewModel.login() },
                    enable = loginViewModel.isEnabledLoginButton
                )

            }

        }
    }

    loginFlow?.let { state ->
        when (state) {

            is Result.Failure<*, *> -> Toast.makeText(
                LocalContext.current,
                state.error.toString(), Toast.LENGTH_LONG
            ).show()

            is Result.Success<*, *> -> {
                LaunchedEffect(Unit){
//                    navHostController.navigate(route = AppScreen.BookList.route)
                }
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        // A surface container using the 'background' color from the theme
            LoginContent()

    }
}