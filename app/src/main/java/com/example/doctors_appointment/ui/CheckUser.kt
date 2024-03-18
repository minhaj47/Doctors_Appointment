package com.example.doctors_appointment.ui

//class Check:ViewModel(){
//
//}

import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.repository.MongoRepoImplementation
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

suspend fun CheckUser(
    email: String,
    password: String,
): Boolean {
    val repository = MongoRepoImplementation

    return withContext(Dispatchers.IO) {

        val flag: Deferred<Boolean> = async {

            println("inside check user")

            val doctor = repository.auThenticateUserAsDoctor(email, password)
            val patient = repository.auThenticateUserAsPatient(email, password)

            if (doctor != null) {
                MyApp.doctor = doctor
                println("doctor matched")
                false
            } else if (patient != null) {
                MyApp.patient = patient
                println("patient matched")
                true
            } else {
                false // Return false if neither doctor nor patient is matched
            }
        }
        return@withContext flag.await()
    }
}
