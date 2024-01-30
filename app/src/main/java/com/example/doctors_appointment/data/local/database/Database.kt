package com.example.doctors_appointment.data.local.database

import androidx.room.Database
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.local.dao.DoctorDao
import com.example.doctors_appointment.data.local.dao.PatientDao
import com.example.doctors_appointment.data.model.Patient

@Database(
    entities = arrayOf(
        Doctor::class,
        Patient:: class
    ),
    version = 1
)
abstract class Database{

    abstract fun getDoctorDao(): DoctorDao

    abstract fun getPatientDao(): PatientDao

}