package com.example.doctors_appointment

import android.app.Application
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.model.Prescription
import com.example.doctors_appointment.di.DatabaseModule
import com.example.doctors_appointment.util.Constants.APP_ID
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.realmListOf
//import io.realm.kotlin.mongodb.App
import javax.inject.Singleton

@HiltAndroidApp
class MyApp: Application() {

    //val app = App.create(APP_ID)

    companion object {
        lateinit var realm: Realm
        lateinit var patient: Patient
        lateinit var doctor: Doctor
    }


    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Doctor::class,
                    Patient::class,
                    Prescription::class,
                    Appointment::class
                )
            )
        )
        patient = Patient()
    }

}