package com.example.doctors_appointment.data.repository

import com.example.doctors_appointment.data.local.dao.DoctorDao
import com.example.doctors_appointment.data.model.Doctor
import kotlinx.coroutines.flow.Flow

class DoctorRepoImplementation(
    val doctorDao: DoctorDao
    ): DoctorRepo {

    override suspend fun insertDoctor(doctor: Doctor) {
        doctorDao.insertDoctor(doctor)
    }

    override suspend fun deleteDoctor(doctor: Doctor) {
        doctorDao.deleteDoctor(doctor)
    }

    override fun getAllDoctors(): Flow<List<Doctor>> {
        return doctorDao.getAllDoctors()
    }
}