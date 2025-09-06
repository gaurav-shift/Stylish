package com.example.stylish.Presentation.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylish.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    isLoading: Boolean,
    onFinish:()-> Unit
){
    LaunchedEffect(isLoading) { // uses unit as key
        if(!isLoading){
            onFinish()
        }
    }

   Box(
       modifier = Modifier
           .fillMaxSize(),
       contentAlignment = Alignment.Center
   ){
       Row(
           verticalAlignment = Alignment.CenterVertically
       ){
            Image(painter = painterResource(R.drawable.stylish_logo),
                contentDescription = "Image Logo",
                modifier = Modifier.size(100.dp))
           Spacer(modifier = Modifier.width(20.dp))
           Text("Stylish",
               fontSize = 50.sp,
               fontWeight = FontWeight.Bold,
               color = Color(0xFFF83758),
               style = TextStyle(
                   shadow = Shadow(
                       color = Color.Black,
                       offset = Offset(1f, 1f),
                       blurRadius = 1f
                   )
               )

               )

       }
   }

}

//@Preview(showSystemUi = true)
//@Composable
//fun PrevSplashScreen(){
//    SplashScreen()
//}
