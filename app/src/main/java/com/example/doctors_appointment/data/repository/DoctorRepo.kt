package com.example.doctors_appointment.data.repository

import com.example.doctors_appointment.data.model.Doctor
import kotlinx.coroutines.flow.Flow

interface DoctorRepo{

    suspend fun insertDoctor(doctor: Doctor)

    suspend fun deleteDoctor(doctor: Doctor)

    fun getAllDoctors(): Flow<List<Doctor>>
}