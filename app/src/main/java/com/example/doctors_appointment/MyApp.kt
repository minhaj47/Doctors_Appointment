package com.example.doctors_appointment

import android.app.Application
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.util.Constants
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.User


@HiltAndroidApp
class MyApp : Application() {

    companion object {
        lateinit var patient: Patient
        lateinit var doctor: Doctor
        // var authenticatedUser: User? = null

        val appConfig = AppConfiguration.Builder("doctors_appointment_android_app-afyts").build()
        val app = App.create(appConfig)

//    val firebaseUser= FirebaseAuth.getInstance().currentUser
    }

}