package com.example.doctors_appointment.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.doctors_appointment.data.model.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Insert
    suspend fun insertPatient(patient: Patient)

    @Delete
    suspend fun deletePatient(doctor: Patient)

    @Query("Select * from ")
    fun getAllPatients(): Flow<List<Patient>>

}