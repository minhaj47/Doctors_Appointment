package com.example.doctors_appointment.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.doctors_appointment.data.model.Doctor
import kotlinx.coroutines.flow.Flow

@Dao
interface DoctorDao {

    @Insert
    suspend fun insertDoctor(doctor: Doctor)

    @Delete
    suspend fun deleteDoctor(doctor: Doctor)

    @Query("Select * from ")
    fun getAllDoctors(): Flow<List<Doctor>>

}