package com.example.stylish.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stylish.Presentation.Splash.SplashScreen
import com.example.stylish.Presentation.authUI.ForgetPassword
import com.example.stylish.Presentation.authUI.LoginScreen
import com.example.stylish.Presentation.authUI.SignUpScreen
import com.example.stylish.Presentation.onBoarding.OnboardingPagerScreen
import androidx.navigation.NavHostController
import androidx.navigation.toRoute

@Composable
fun AppNavigator(){
    val navController = rememberNavController()

    NavHost(
        navController=navController,
        startDestination = Routes.Splash
    ){
        composable<Routes.Splash>{
            SplashScreen {
                navController.navigate(Routes.Onboarding)
//                    popUpTo("SplashScreen"){inclusive= true}

            }
        }

//        composable("onboarding1") {
//            Onboarding1Screen()
//        }
        composable<Routes.Onboarding> {
            OnboardingPagerScreen(navController,
                onGetStarted = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<Routes.Login> {
            LoginScreen(navController)
        }
        composable<Routes.ForgetPassword> {
            ForgetPassword()
        }
        composable<Routes.Signup> {
            SignUpScreen(navController)
        }


    }
}