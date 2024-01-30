package com.example.doctors_appointment.data.repository

import com.example.doctors_appointment.data.model.Patient
import kotlinx.coroutines.flow.Flow

interface PatientRepo{

    suspend fun insertPatient(patient: Patient)

    suspend fun deletePatient(patient: Patient)

    fun getAllPatients(): Flow<List<Patient>>
}