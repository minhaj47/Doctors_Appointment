package com.example.doctors_appointment.data.repository

import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import org.mongodb.kbson.ObjectId

interface MongoRepository {

    suspend fun insertDoctor(doctor: Doctor)
    suspend fun deleteDoctor(doctor: Doctor)
    suspend fun updateDoctor(doctor: Doctor)
    suspend fun getAllDoctors(): List<Doctor>
    suspend fun getDoctorFromId(userId: String): Doctor?
    suspend fun getPatientFromId(_id: ObjectId): Patient?
    suspend fun insertPatient(patient: Patient)
    suspend fun updatePatient(patient: Patient)
    suspend fun deletePatient(patient: Patient)
    suspend fun getUpcomingAppointmentsOfUser(): List<Appointment>
    suspend fun getPastAppointmentsOfUser(): List<Appointment>
    suspend fun insertAppointment(appointment: Appointment)

    suspend fun setAppointment(doctor: Doctor, patient: Patient, appointment: Appointment)
    suspend fun deleteAppointment(appointment: Appointment)
    suspend fun getCategoryDoctor(category: String): List<Doctor>

    suspend fun auThenticateUserAsPatient(email: String, password: String): Patient?
    suspend fun auThenticateUserAsDoctor(email: String, password: String): Doctor?

    //fun getAppointmentFromId(_id: ObjectId): Appointment?
}