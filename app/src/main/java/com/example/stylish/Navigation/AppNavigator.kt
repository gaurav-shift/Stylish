package com.example.stylish.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stylish.DataLayer.local.UserPreferenceDataStore
import com.example.stylish.DataLayer.repositoryImpl.AuthRepositoryImpl
import com.example.stylish.DataLayer.repositoryImpl.UserInterfaceRepoImpl
import com.example.stylish.DomainLayer.usecase.GetUserPreferenceUseCase
import com.example.stylish.DomainLayer.usecase.LoginUserCase
import com.example.stylish.DomainLayer.usecase.SetUserPreferenceUseCase
import com.example.stylish.DomainLayer.usecase.SignupUserUseCase
import com.example.stylish.Presentation.Splash.SplashScreen
import com.example.stylish.Presentation.authUI.AuthViewModel
import com.example.stylish.Presentation.authUI.ForgetPassword
import com.example.stylish.Presentation.authUI.LoginScreen
import com.example.stylish.Presentation.authUI.SignUpScreen
import com.example.stylish.Presentation.onBoarding.OnboardingPagerScreen
import com.example.stylish.UserPreferences.UserPreferencesViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigator(){
    val navController = rememberNavController()
    val context = LocalContext.current

    val userPreferenceDataStore =  remember { UserPreferenceDataStore(context) }
    val userPreferenceRepo = remember { UserInterfaceRepoImpl(userPreferenceDataStore) }
    val getUserPreferenceUseCase = remember { GetUserPreferenceUseCase(userPreferenceRepo) }
    val setUserPreferenceUseCase = remember { SetUserPreferenceUseCase(userPreferenceRepo) }
    val userPreferencesViewModel = remember { UserPreferencesViewModel(getUserPreferenceUseCase,setUserPreferenceUseCase) }

    val authRepo = remember { AuthRepositoryImpl(FirebaseAuth.getInstance()) }
    val loginUserCase = remember { LoginUserCase(authRepo) }
    val signupUserUseCase = remember { SignupUserUseCase(authRepo) }
    val authViewModel = remember { AuthViewModel (loginUserCase,signupUserUseCase, setUserPreferenceUseCase) }

    val userPreferencesState by userPreferencesViewModel.state.collectAsState()


    NavHost(
        navController=navController,
        startDestination = Routes.Splash
    ){

        composable<Routes.Login> {
            LoginScreen(navController,authViewModel)
        }

        composable<Routes.Signup> {
            SignUpScreen(navController,authViewModel)
        }

        composable<Routes.ForgetPassword> {
            ForgetPassword()
        }

        composable<Routes.Onboarding> {
            OnboardingPagerScreen(navController,
                onGetStarted = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Splash) { inclusive = true }
                   }
                }
            )
        }


        composable<Routes.Splash>{
            SplashScreen(
                isLoading =userPreferencesState.isLoading,
                onFinish = {
                    val destination = when {
                      //  userPreferencesState.isLoggedIn -> Routes.ProductListScreen
                        userPreferencesState.IsFirstTimeLogin -> Routes.Onboarding
                        else -> Routes.Login
                    }

                    navController.navigate(destination) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }

                }
            )
        }

//        composable("onboarding1") {
//            Onboarding1Screen()
//        }





        
    }

}