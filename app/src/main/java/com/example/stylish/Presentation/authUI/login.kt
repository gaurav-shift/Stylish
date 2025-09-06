package com.example.stylish.Presentation.authUI

import android.R.attr.password
import android.widget.Toast
import androidx.compose.animation.core.Animatable
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
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stylish.DomainLayer.util.Result
import com.example.stylish.Navigation.Routes
import com.example.stylish.Presentation.authUI.Component.CustomTextField
import com.example.stylish.Presentation.authUI.Component.SocialIconButton
import com.example.stylish.R

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current


    val authState by authViewModel.authState.collectAsState()


    LaunchedEffect(authState) {

        when(val currentState = authState) {// we make a val curstate and then put authstate without directly putting it in when cuz sometimes it can give null to when cuz Ui is rendering on main thread which is faster that's why !!

            is Result.Success->{
                //navigate to homescreen
                isLoading=false
                Toast.makeText(context,"Login Successful",Toast.LENGTH_SHORT).show()

            }
            is Result.Failure->{
                showError = true
                errorMessage= currentState.message
                isLoading=false
            }
            Result.Idle->{
                isLoading=false
            }
            Result.Loading-> {
                isLoading = true
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
                    text = "Welcome",
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp

                )
                Text(
                    text = "Back!",
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
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "Forgot Password?",
                    color = Color(0xFFEF4444),
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            navController.navigate(Routes.ForgetPassword)
                        }

                )

                Spacer(Modifier.padding(10.dp))

                if (showError) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }



                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .height(45.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFEF4444))
                        .clickable{
                            if(email.isNotBlank() && password.isNotBlank()){
                                authViewModel.login(email,password)
                            }
                        }
                ){
                    if(isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White
                        )
                    } else{
                        Text(
                            text = "Login",
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.Center),
                            letterSpacing = 0.5.sp
                        )
                    }
                } }

//                Button(
//                    onClick = {},
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
//                    shape = RoundedCornerShape(10.dp)
//                ) {
//                    Text("Login",
//                        fontSize = 16.sp)
//                }
//            }

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
                Text("Create An Account ", fontSize = 14.sp)
                Text(
                    text = "Sign Up",
                    fontSize = 14.sp,
                    color = Color(0xFFF83758),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable{
                        navController.navigate(Routes.Signup)
                    }
                )
            }
        }


        }
    }


}



