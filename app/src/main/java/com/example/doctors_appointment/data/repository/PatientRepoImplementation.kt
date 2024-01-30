package com.example.doctors_appointment.data.repository

import com.example.doctors_appointment.data.local.dao.PatientDao
import com.example.doctors_appointment.data.model.Patient
import kotlinx.coroutines.flow.Flow

class PatientRepoImplementation(
    val patientDao: PatientDao
    ): PatientRepo {

    override suspend fun insertPatient(patient: Patient) {
        patientDao.insertPatient(patient)
    }

    override suspend fun deletePatient(patient: Patient) {
        patientDao.deletePatient(patient)
    }

    override fun getAllPatients(): Flow<List<Patient>> {
        return patientDao.getAllPatients()
    }
}