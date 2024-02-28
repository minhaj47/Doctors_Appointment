package com.example.doctors_appointment

import android.app.Application
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.util.Constants
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.User


@HiltAndroidApp
class MyApp : Application() {

    companion object {
        lateinit var patient: Patient
        lateinit var doctor: Doctor
        var authenticatedUser: User? = null
        var app = App.create(Constants.APP_ID)
//    val firebaseUser= FirebaseAuth.getInstance().currentUser
    }

}