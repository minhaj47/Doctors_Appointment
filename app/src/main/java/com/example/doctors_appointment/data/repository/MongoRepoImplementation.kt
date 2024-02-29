package com.example.doctors_appointment.data.repository

import android.util.Log
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.model.Prescription
import com.example.doctors_appointment.util.Constants
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.log.LogLevel
//import io.realm.kotlin.mongodb.App
//import io.realm.kotlin.mongodb.Credentials
//import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    override fun configureTheRealm() {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Doctor::class,
                Patient::class,
                Appointment::class,
                Prescription::class
            )
        ).build()

        realm = Realm.open(config)
    }

    override fun auThenticateUserAsPatient(email: String, password: String): Patient? {

        //Log.d("entered as patient", "entered to auth patient")

        val user = realm
            .query<Patient>("email == $0 and password == $1", email, password)
            .first()
            .find()

        if (user is Patient) Log.d("entered as patient", "auth patient successful")

        return user
    }

    override fun auThenticateUserAsDoctor(email: String, password: String): Doctor? {
        val user = realm
            .query<Doctor>("email == $0 and password == $1", email, password)
            .first()
            .find()
        if (user is Doctor) Log.d("entered as doctor", "auth  doctor done")
        return user
    }


    override suspend fun insertDoctor(doctor: Doctor) {
        realm.write {
            copyToRealm(doctor, UpdatePolicy.ALL)
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
                val queriedDoctor = query<Doctor>(query = "_id == $0", doctor._id).first().find()

                queriedDoctor?.name = doctor.name
                queriedDoctor?.qualifications = doctor.qualifications
                queriedDoctor?.address = doctor.address
                queriedDoctor?.about = doctor.about
                queriedDoctor?.gender = doctor.gender
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
        val frozenDoctor = realm
            .query<Doctor>()
            .asFlow()
            .map {
                it.list
            }
        return frozenDoctor
    }

    override fun getCategoryDoctor(category: String): Flow<List<Doctor>> {
        return realm
            .query<Doctor>(query = "medicalSpecialty == $0", category)
            .asFlow()
            .map {
                it.list
            }
    }

    override fun getDoctorFromId(userId: String): Doctor? {
        val userObjId = ObjectId(userId)
        return realm
            .query<Doctor>("_id  == $0", userObjId)
            .first()
            .find()
    }

    // Patient


    override fun getPatientFromId(_id: ObjectId): Patient? {
        return realm
            .query<Patient>("_id == $0", _id)
            .first()
            .find()
    }

    override fun getAppointmentFromId(OId: String): Appointment? {
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

            val queriedPatient = query<Patient>(query = "_id == $0", patient._id).first().find()

            try {
                queriedPatient?.let {
                    delete(it)
                    Log.d("fjd", "patient deletion done")
                }
            } catch (e:Exception){
                Log.d("MongoRepoImplementation", "${e.message}")
            }
        }

    }


    override suspend fun updatePatient(patient: Patient) {

        realm.write {
            val queriedPatient = query<Patient>(query = "_id == $0", patient._id).first().find()

            try {
                println(queriedPatient?.name)
                queriedPatient?.apply {
                    println("inside apply")
                    name = patient.name
                    height = patient.height
                    weight = patient.weight
                    contactNumber = patient.contactNumber
                    gender = patient.gender
                    email = patient.email
                    medicalHistory = patient.medicalHistory
                    dateOfBirth = patient.dateOfBirth
                    notification = patient.notification
                    profileImage = patient.profileImage
                    password = patient.password
                    println("after apply")
                    println(queriedPatient.name)
                }

            } catch (e: Exception) {
                Log.d("inside update patient", "${e.message}")
            }


        }

    }
    // appointment

    override fun getPastAppointmentsOfUser(): Flow<List<Appointment>> {

        val userId = MyApp.patient._id

        val localDateTime = LocalDateTime.now()
        val date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
        val milliseconds = date.time

        return realm
            .query<Appointment>(
                "@links.Patient.medicalHistory._id == $0 AND appointmentDate < $1",
                userId,
                milliseconds
            )
            .asFlow()
            .map {
                it.list
            }
    }


    override fun getUpcomingAppointmentsOfUser(): Flow<List<Appointment>> {

        val userId = MyApp.patient._id

        val localDateTime = LocalDateTime.now()
        val date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
        val milliseconds = date.time
        return realm
            .query<Appointment>(
                "@links.Patient.medicalHistory._id == $0 AND appointmentDate >= $1",
                userId,
                milliseconds
            )
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