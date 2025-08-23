package com.example.stylish.Navigation

import kotlinx.serialization.Serializable

@Serializable
    sealed class Routes{
        @Serializable
        data object Splash: Routes()

        @Serializable
        data object Onboarding: Routes()

        @Serializable
        data object Login : Routes()

        @Serializable
        data object ForgetPassword: Routes()

        @Serializable
        data object Signup: Routes()




    }