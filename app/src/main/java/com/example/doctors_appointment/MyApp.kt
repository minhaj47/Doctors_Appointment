package com.example.doctors_appointment

import android.app.Application
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.model.Prescription
import com.example.doctors_appointment.di.DatabaseModule
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.realmListOf
import javax.inject.Singleton

@HiltAndroidApp
class MyApp: Application() {

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