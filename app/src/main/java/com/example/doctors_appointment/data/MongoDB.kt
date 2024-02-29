package com.example.doctors_appointment.data
//
//import com.example.doctors_appointment.data.model.Appointment
//import com.example.doctors_appointment.data.model.Doctor
//import com.example.doctors_appointment.data.model.Patient
//import com.example.doctors_appointment.data.repository.MongoRepoImplementation
//import com.example.doctors_appointment.data.repository.MongoRepository
//import com.example.doctors_appointment.util.Constants
//import io.realm.kotlin.mongodb.App
//import kotlinx.coroutines.flow.Flow
//import org.mongodb.kbson.ObjectId
//
//object MongoDB: MongoRepository {
//
//    override suspend fun insertDoctor(doctor: Doctor) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteDoctor(doctor: Doctor) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun updateDoctor(doctor: Doctor) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAllDoctors(): Flow<List<Doctor>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getDoctorFromId(userId: String): Doctor? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getPatientFromId(_id: ObjectId): Patient? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getAppointmentFromId(OId: String): Appointment? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun insertPatient(patient: Patient) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun updatePatient(patient: Patient) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deletePatient(patient: Patient) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getUpcomingAppointmentsOfUser(): Flow<List<Appointment>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getPastAppointmentsOfUser(): Flow<List<Appointment>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun insertAppointment(appointment: Appointment) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun setAppointment(
//        doctor: Doctor,
//        patient: Patient,
//        appointment: Appointment
//    ) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteAppointment(appointment: Appointment) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getCategoryDoctor(category: String): Flow<List<Doctor>> {
//        TODO("Not yet implemented")
//    }
//}