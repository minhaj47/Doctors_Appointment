package com.example.doctors_appointment.data.repository

import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.model.Prescription
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface MongoRepository {

    //fun configureTheRealm()

    suspend fun insertDoctor(doctor: Doctor)
    suspend fun deleteDoctor(doctor: Doctor)
    suspend fun updateDoctor(doctor: Doctor)

    fun getAllDoctors(): Flow<List<Doctor>>
    suspend fun getDoctorFromId(userId: String): Doctor?

    suspend fun getPatientFromId(_id: ObjectId): Patient?
    suspend fun getAppointmentFromId(OId: String): Appointment?
    suspend fun insertPatient(patient: Patient)
    suspend fun updatePatient(patient: Patient)
    suspend fun deletePatient(patient: Patient)
    suspend fun getUpcomingAppointmentsOfUser(): Flow<List<Appointment>>
    suspend fun getPastAppointmentsOfUser(): Flow<List<Appointment>>
    suspend fun insertAppointment(appointment: Appointment)

    suspend fun setAppointment(doctor: Doctor, patient: Patient, appointment: Appointment)
    suspend fun deleteAppointment(appointment: Appointment)
    suspend fun getCategoryDoctor(category: String): Flow<List<Doctor>>
}