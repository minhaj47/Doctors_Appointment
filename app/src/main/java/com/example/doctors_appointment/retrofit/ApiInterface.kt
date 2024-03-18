package com.example.doctors_appointment.retrofit

import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import org.mongodb.kbson.ObjectId
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {

    // Define endpoints for doctor operations
    @GET("api/doctors")
    suspend fun getAllDoctors(): Call<List<Doctor>>

    @GET("api/doctors/{catagory}")
    suspend fun getCatagoryDoctor(@Path("category") category: String): List<Doctor>

    @POST("api/doctors")
    suspend fun insertDoctor(@Body doctor: Doctor)

    @DELETE("api/doctors/{id}")
    suspend fun deleteDoctor(@Path("id") id: ObjectId)

    @PUT("api/doctors/{id}")
    suspend fun updateDoctor(@Path("id") id: ObjectId, @Body doctor: Doctor)

    @GET("api/doctors/{id}")
    suspend fun getDoctorFromId(@Path("id") userId: String): Call<Doctor>

    // Define endpoints for patient operations
    @POST("api/patients")
    suspend fun insertPatient(@Body patient: Patient)

    @DELETE("api/patients/{id}")
    suspend fun deletePatient(@Path("id") id: ObjectId)

    @PUT("api/patients/{id}")
    suspend fun updatePatient(@Path("id") id: ObjectId, @Body patient: Patient)

    @GET("api/patients/{id}")
    suspend fun getPatientFromId(@Path("id") id: ObjectId): Call<Patient>

    // Define endpoints for appointment operations
    @POST("api/appointments")
    suspend fun insertAppointment(@Body appointment: Appointment)

    @DELETE("api/appointments/{id}")
    suspend fun deleteAppointment(@Path("id") id: ObjectId)

    @PUT("api/appointments/{id}")
    suspend fun updateAppointment(@Path("id") id: ObjectId, @Body appointment: Appointment)

    @GET("api/appointments/upcoming/{id}")
    suspend fun getUpcomingAppointmentsOfUser(@Path("id") id: ObjectId): List<Appointment>

    @GET("api/appointments/past/{id}")
    suspend fun getPastAppointmentsOfUser(@Path("id") id: ObjectId): List<Appointment>

    // Define authentication endpoints
    @GET("api/authenticate/patient/{email}/{password}")
    suspend fun authenticateUserAsPatient(@Path("email") email: String, @Path("password") password: String): Call<Patient>

    @GET("api/authenticate/doctor/{email}/{password}")
    suspend fun authenticateUserAsDoctor(@Path("email") email: String, @Path("password") password: String): Call<Doctor>
}

