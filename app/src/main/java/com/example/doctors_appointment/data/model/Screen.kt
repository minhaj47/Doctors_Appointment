package com.example.doctors_appointment.data.model

sealed class Screen(val route: String){
    object home: Screen("home")
    object signIn: Screen("signIn")
    object signUp: Screen("signUp")
    object mainHome: Screen("mainHome")
    object doctors: Screen("doctors")
    object appointment: Screen("appointment")
    object profile: Screen("profile")
    object doctorsDetails: Screen("doctor's details page")
    object catagoryDoctors: Screen("catagorically doctors page")

}
