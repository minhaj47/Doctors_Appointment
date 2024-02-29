package com.example.doctors_appointment.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.repository.MongoRepoImplementation
import com.example.doctors_appointment.ui.patientsUI.BottomNavigationItem
import com.example.doctors_appointment.ui.patientsUI.NavBar
import com.example.doctors_appointment.util.Screen

fun CheckUser(
    email: String,
    password: String,
): Boolean {
    val repository = MongoRepoImplementation

    val doctor = repository.auThenticateUserAsDoctor(email, password)
    val patient = repository.auThenticateUserAsPatient(email, password)

    if (doctor != null) {
        MyApp.doctor = doctor
        println("doctor matched")
        return false
    } else if (patient != null) {
        MyApp.patient = patient
        println("patient matched")
        return true
    } else return true
}