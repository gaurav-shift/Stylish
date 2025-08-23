package com.example.stylish.Presentation.authUI

import android.R.attr.password
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylish.Presentation.authUI.Component.CustomTextField
import com.example.stylish.Presentation.authUI.Component.SocialIconButton
import com.example.stylish.R

@Composable
fun ForgetPassword(){

    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordVisible by remember { mutableStateOf(false) }


    Scaffold() {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
//                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Forget",
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp

                )
                Text(
                    text = "password?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp

                )
                Spacer(Modifier.padding(20.dp))

                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Enter your email address",
                    icon = Icons.Default.Email,
                    placeholder = "Enter your email address"
                )
                Spacer(Modifier.padding(12.dp))


                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "We will send you a message to set or reset\nyour new password",
                    color = Color.LightGray,
                    modifier = Modifier.align(Alignment.Start)

                )

                Spacer(Modifier.padding(20.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Submit",
                        fontSize = 16.sp)
                }
            }


        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun PreviewForgetPassword(){
   ForgetPassword()
}