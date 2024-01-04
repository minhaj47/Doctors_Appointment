package com.example.doctors_appointment

sealed class Screen(val route: String){
    object home: Screen("home")
    object signIn: Screen("signIn")
    object signUp: Screen("signUp")
    object mainHome: Screen("mainHome")
    object doctors: Screen("doctors")
    object appointment: Screen("appointment")
    object profile: Screen("profile")
}
