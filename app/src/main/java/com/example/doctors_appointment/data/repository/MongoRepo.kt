package com.example.doctors_appointment.data.repository

import android.util.Log
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.model.Prescription
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MongoRepository {

    fun configureTheRealm()

    suspend fun insertDoctor(doctor: Doctor)
    suspend fun deleteDoctor(doctor: Doctor)
    suspend fun updateDoctor(doctor: Doctor)

    fun getAllDoctors(): Flow<List<Doctor>>
    fun getDoctorFromId(userId: String): Doctor?

    fun getPatientFromId(_id: ObjectId): Patient?
    fun getAppointmentFromId(OId: String): Appointment?
    suspend fun insertPatient(patient: Patient)
    suspend fun updatePatient(patient: Patient)
    suspend fun deletePatient(patient: Patient)
    fun getUpcomingAppointmentsOfUser(): Flow<List<Appointment>>
    fun getPastAppointmentsOfUser(): Flow<List<Appointment>>
    suspend fun insertAppointment(appointment: Appointment)

    suspend fun setAppointment(doctor: Doctor, patient: Patient, appointment: Appointment)
    suspend fun deleteAppointment(appointment: Appointment)
    fun getCategoryDoctor(category: String): Flow<List<Doctor>>

    fun auThenticateUserAsPatient(email: String, password: String): Patient?

    fun auThenticateUserAsDoctor(email: String, password: String): Doctor?
}