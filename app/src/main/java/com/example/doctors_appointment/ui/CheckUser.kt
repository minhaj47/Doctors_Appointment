package com.example.doctors_appointment.ui

import androidx.compose.runtime.Composable
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.repository.MongoRepoImplementation
import com.example.doctors_appointment.ui.patientsUI.NavBar

@Composable
fun CheckUser(
    email: String,
    password: String
) {
    val repository = MongoRepoImplementation

    val doctor = repository.auThenticateUserAsDoctor(email, password)
    val patient = repository.auThenticateUserAsPatient(email, password)

    if (doctor != null) {
        MyApp.doctor = doctor
        // DoctorsNavBar(repository)
    } else if (patient != null) {
        MyApp.patient = patient
        NavBar(repository)
    }
}