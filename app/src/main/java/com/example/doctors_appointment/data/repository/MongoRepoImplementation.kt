package com.example.doctors_appointment.data.repository

import android.util.Log
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.model.Prescription
import com.example.doctors_appointment.util.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object MongoRepoImplementation : MongoRepository {

    private lateinit var realm: Realm

    init {
        configureTheRealm()
    }

    val user = MyApp.authenticatedUser
    override fun configureTheRealm() {

        if (user != null) {
            val config = SyncConfiguration.Builder(
                user,
                setOf(
                    Doctor::class,
                    Patient::class,
                    Appointment::class,
                    Prescription::class
                )
            ).initialSubscriptions { sub ->
                add(query = sub.query<Patient>())
                add(query = sub.query<Doctor>())
            }.build()
            realm = Realm.open(config)
        }
    }

    override fun auThenticateUserAsPatient(email: String, password: String): Patient? {

        //Log.d("entered as patient", "entered to auth patient")

        val user = realm
            .query<Patient>("email == $0 and password == $1", email, password)
            .first()
            .find()
        //if(user is Patient) Log.d("entered as patient", "auth patient successful")
        return user
    }

    override fun auThenticateUserAsDoctor(email: String, password: String): Doctor? {
        val user = realm
            .query<Doctor>("email == $0 and password == $1", email, password)
            .first()
            .find()
        Log.d("entered as doctor", "auth  doctor done")
        return user
    }


    override suspend fun insertDoctor(doctor: Doctor) {
        realm.write {
            copyToRealm(doctor)
        }
    }

    override suspend fun deleteDoctor(doctor: Doctor) {

        realm.write {
            val queriedDoctor = realm.query<Doctor>(query = "_id == $0", doctor._id).first().find()

            try {
                queriedDoctor?.let {
                    delete(it)
                }
            } catch (e:Exception){
                Log.d("MongoRepoImplementation", "${e.message}")
            }
        }

    }

    override suspend fun updateDoctor(doctor: Doctor) {
        realm.write {
            try {
                val queriedDoctor = this.query<Doctor>(query = "_id == $0", doctor._id).first().find()

                queriedDoctor?.name = doctor.name
                queriedDoctor?.qualifications = doctor.qualifications
                queriedDoctor?.address = doctor.address
                queriedDoctor?.about = doctor.about
                queriedDoctor?.appointments = doctor.appointments
                queriedDoctor?.availabilityStatus = doctor.availabilityStatus
                queriedDoctor?.bmdcRegistrationNumber = doctor.bmdcRegistrationNumber
                queriedDoctor?.contactNumber = doctor.contactNumber
                queriedDoctor?.docoument = doctor.docoument
                queriedDoctor?.rating = doctor.rating
                queriedDoctor?.consultationFee = doctor.consultationFee
                queriedDoctor?.reviews = doctor.reviews
                queriedDoctor?.experience = doctor.experience
                queriedDoctor?.email = doctor.email
                queriedDoctor?.medicalSpecialty= doctor.medicalSpecialty
                queriedDoctor?.profileImage = doctor.profileImage
                queriedDoctor?.notification = doctor.notification

                queriedDoctor?.password = doctor.password
            }catch (e: Exception){
                println("exception in update doctor" + "${e.message}")
            }

        }
    }


    override fun getAllDoctors(): Flow<List<Doctor>> {
        return realm
            .query<Doctor>()
            .asFlow()
            .map {
                it.list
            }
    }

    override suspend fun getCategoryDoctor(category: String): Flow<List<Doctor>> {
        return realm
            .query<Doctor>(query = "medicalSpecialty == $0", category)
            .asFlow()
            .map {
                it.list
            }
    }

    override suspend fun getDoctorFromId(userId: String): Doctor? {
        val userObjId = ObjectId(userId)
        return realm
            .query<Doctor>("_id  == $0", userObjId)
            .first()
            .find()
    }

    // Patient


    override suspend fun getPatientFromId(_id: ObjectId): Patient? {
        return realm
            .query<Patient>("_id == $0", _id)
            .first()
            .find()
    }

    override suspend fun getAppointmentFromId(OId: String): Appointment? {
        val objId = ObjectId(OId)
        return realm
            .query<Appointment>("_id == $0", objId)
            .first()
            .find()
    }

    override suspend fun insertPatient(patient: Patient){
        realm.write {
            copyToRealm(patient)
        }
    }

    override suspend fun deletePatient(patient: Patient) {

        realm.write {

            val queriedPatient = realm.query<Patient>(query = "_id == $0", patient._id).first().find()

            try {
                queriedPatient?.let {
                    delete(it)
                }
            } catch (e:Exception){
                Log.d("MongoRepoImplementation", "${e.message}")
            }
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun updatePatient(patient: Patient) {
        realm.writeBlocking {
            GlobalScope.launch {
                try {
                    val queriedPatient = async { getPatientFromId(patient._id) }
                    delay(3000L)

                    println(queriedPatient.await()?.name)

                    queriedPatient.await()?.apply {
                        println("Inside apply ")
                        name = patient.name
                        height = patient.height
                        weight = patient.weight
                        contactNumber = patient.contactNumber
                        email = patient.email
                        medicalHistory = patient.medicalHistory
                        dateOfBirth = patient.dateOfBirth
                        notification = patient.notification
                        profileImage = patient.profileImage
                        password = patient.password
                        println("below name")
                    }

                }catch (e: Exception){
                    println("exception in update patient" + "${e.message}")
                }

            }

        }
    }


//    override suspend fun updatePatient(patient: Patient) {
//
//        realm.write {
//            copyToRealm(Patient().apply {
//                _id = patient._id
//                name = patient.name
//                height = patient.height
//                weight = patient.weight
//                contactNumber = patient.contactNumber
//                email = patient.email
//                medicalHistory = patient.medicalHistory
//                dateOfBirth = patient.dateOfBirth
//                notification = patient.notification
//                profileImage = patient.profileImage
//
//                password = patient.password
//            }, UpdatePolicy.ALL)
//        }
//    }

//    override suspend fun updatePatient(patient: Patient) {
//        realm.write {
//            // Get the live patient object from the Realm, or null if it doesn't exist
//            val existingPatient = findLatest(patient)
//
//            // If the existing patient is not null, update its properties
//            existingPatient?.apply {
//                name = patient.name
//                height = patient.height
//                weight = patient.weight
//                contactNumber = patient.contactNumber
//                email = patient.email
//                medicalHistory = patient.medicalHistory
//                dateOfBirth = patient.dateOfBirth
//                notification = patient.notification
//                profileImage = patient.profileImage
//                password = patient.password
//            } ?: run {
//                // If the patient doesn't exist, create a new one
//                copyToRealm(patient, UpdatePolicy.ALL)
//            }
//        }
//    }

    // appointment

    override suspend fun getPastAppointmentsOfUser(): Flow<List<Appointment>> {

        val userId = MyApp.patient._id

        val localDateTime = LocalDateTime.now()
        val date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
        val milliseconds = date.time

        return realm
            .query<Appointment>("@links.Patient.medicalHistory._id == $0 AND appointmentDate < $1", userId, milliseconds)
            .asFlow()
            .map {
                it.list
            }
    }


    override suspend fun getUpcomingAppointmentsOfUser(): Flow<List<Appointment>> {

        val userId = MyApp.patient._id

        val localDateTime = LocalDateTime.now()
        val date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
        val milliseconds = date.time
        return realm
            .query<Appointment>("@links.Patient.medicalHistory._id == $0 AND appointmentDate >= $1", userId, milliseconds)
            .asFlow()
            .map {
                it.list
            }
    }

    override suspend fun insertAppointment(appointment: Appointment){
        realm.write {
            copyToRealm(appointment)
        }
    }

    override suspend fun setAppointment(
        doctor: Doctor,
        patient: Patient,
        appointment: Appointment
    ) {
        realm.write {

            findLatest(doctor)?.appointments?.add(appointment)

            findLatest(patient)?.medicalHistory?.add(appointment)
        }
    }

    override suspend fun deleteAppointment(appointment: Appointment) {

        realm.write {
            val queriedAppointment = realm.query<Appointment>(query = "_id == $0", appointment._id).first().find()

            try {
                queriedAppointment?.let {
                    delete(it)
                }
            } catch (e:Exception){
                Log.d("MongoRepoImplementation", "${e.message}")
            }
        }

    }

}