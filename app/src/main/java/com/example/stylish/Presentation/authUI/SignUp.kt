package com.example.stylish.Presentation.authUI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylish.DomainLayer.util.Result
import com.example.stylish.Navigation.Routes
import com.example.stylish.Presentation.authUI.Component.CustomTextField
import com.example.stylish.Presentation.authUI.Component.SocialIconButton
import com.example.stylish.R

@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState){
        when(authState){
            is Result.Success->{
                    isLoading=false
                    navController.navigate(Routes.Login){
                        popUpTo(Routes.Signup){inclusive = true}
                    }
                authViewModel.resetAuthState()
            }
            is Result.Failure->{
                isLoading=false
                errorMessage = (authState as Result.Failure).message
            }
            Result.Idle ->{
                isLoading=false
            }
            Result.Loading -> {
                isLoading=true
            }
        }
    }


    Scaffold() {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Column(
//                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Create an account",
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp

                )

                Spacer(Modifier.padding(20.dp))

                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Username or Email",
                    icon = Icons.Default.Email,
                    placeholder = "Username or Email"
                )
                Spacer(Modifier.padding(12.dp))

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    icon = Icons.Default.Lock,
                    placeholder = "Password",
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onVisibilityChange = { passwordVisible = !passwordVisible }
                )

                Spacer(Modifier.padding(12.dp))

                CustomTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "ConfirmPassword",
                    icon = Icons.Default.Lock,
                    placeholder = "ConfirmPassword",
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onVisibilityChange = { passwordVisible = !passwordVisible }
                )
                Spacer(modifier = Modifier.padding(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start

                ){
                Text(
                    text = "By clicking the\nthe public offer",
                    color = Color.LightGray,
                    )

                    Text(
                        text = "Register ",
                        color = Color.Red,
                    )
                    Text(
                        text = "button, you agree to",
                        color = Color.LightGray,
                    )
            }

                Spacer(Modifier.padding(20.dp))

                // Error Message
                if (errorMessage != null) {
                    Text(
                        text = errorMessage ?: "",
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .height(45.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFEF4444))
                        .clickable{
                            if(password != confirmPassword){
                                errorMessage="Password do not match"
                            } else{
                                authViewModel.signup(email,password)
                            }
                        }
                ){
                    if(isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White
                        )
                    }
                    else{
                        Text(
                        text = "Create Account",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.align(Alignment.Center),
                        letterSpacing = 0.5.sp
                    ) }
                }

//                Button(
//                    onClick = {},
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
//                    shape = RoundedCornerShape(10.dp)
//                ) {
//                    Text("Create Account",
//                        fontSize = 16.sp)
//                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "- Or Continue With -",
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    SocialIconButton(R.drawable.google)
                    SocialIconButton(R.drawable.apple)
                    SocialIconButton(R.drawable.facebook)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text("I Already Have an Account  ", fontSize = 14.sp)
                    Text(
                        text = "Login",
                        fontSize = 14.sp,
                        color = Color(0xFFF83758),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable{
                            navController.navigate(Routes.Login)
                        }
                    )
                }
            }


        }
    }


}
