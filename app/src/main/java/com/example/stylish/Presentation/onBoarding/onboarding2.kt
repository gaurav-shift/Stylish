package com.example.stylish.Presentation.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylish.Navigation.Routes
import com.example.stylish.Presentation.onBoarding.Components.TopIndicator
import com.example.stylish.R


@Composable
fun Onboarding2Screen(navController: NavController) {
    Scaffold(
//        topBar = { TopIndicator(1, 3, onSkipClick = {}) },
//        bottomBar = { ButtomIndicator(3,1)}
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopIndicator(1, 3, onSkipClick = { navController.navigate(Routes.Login) })
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.sales_consulting_pana_1), "logo2",
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .size(300.dp),
//                    .padding(horizontal = 16.dp),
                    contentScale = ContentScale.Crop


                )
                Text(
                    "Make Payment",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold

                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Amet minim mollit non deserunt ullamco est ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFD1D3D7)
                )
                Text(
                    text = "sit aliqua dolor do amet sint. Velit officia ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFD1D3D7)
                )
                Text(
                    text = "consequat duis enim velit mollit. ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFD1D3D7)
                )

            }
        }


    }
}



//@Preview(showSystemUi = true)
//@Composable
//fun PreviewOnboarding2Screen() {
//    Onboarding2Screen()
//}