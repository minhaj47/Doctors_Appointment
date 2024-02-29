package com.example.doctors_appointment.ui

import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.repository.MongoRepoImplementation
import com.example.doctors_appointment.data.repository.MongoRepository


class SignUpOp {
    suspend fun insertDoctor(doctor: Doctor) {
        MongoRepoImplementation.insertDoctor(doctor)
    }

    suspend fun insertPatient(patient: Patient) {
        MongoRepoImplementation.insertPatient(patient)
    }
}