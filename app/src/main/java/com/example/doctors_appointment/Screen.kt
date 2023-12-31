package com.example.doctors_appointment

sealed class Screen(val route: String){
    object home: Screen("home")
    object signIn: Screen("signIn")
    object signUp: Screen("signUp")
}
