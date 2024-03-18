package com.example.doctors_appointment.data.repository

//import io.realm.kotlin.mongodb.App
//import io.realm.kotlin.mongodb.Credentials
//import io.realm.kotlin.mongodb.sync.SyncConfiguration
import android.util.Log
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.retrofit.RetrofitInstance
import org.mongodb.kbson.ObjectId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MongoRepoImplementation : MongoRepository {


    override suspend fun getAllDoctors(): List<Doctor> {
        val getAllDoctor: Call<List<Doctor>> = RetrofitInstance.api.getAllDoctors()
        var data: List<Doctor> = emptyList()

        getAllDoctor.enqueue(object : Callback<List<Doctor>?> {
            override fun onResponse(call: Call<List<Doctor>?>, response: Response<List<Doctor>?>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        for (doctor in it){
                            Log.i("rtro", "onResponse: Successful -- ${doctor.name}")
                        }
                        data = response.body()!!
                    }
                }
            }

            override fun onFailure(call: Call<List<Doctor>?>, t: Throwable) {
                Log.i("rettro", "ON Failure: ${t.message}")
            }
        })
        return data
    }

override suspend fun insertDoctor(doctor: Doctor) {
        try {
            RetrofitInstance.api.insertDoctor(doctor)
        } catch (e: Exception) {
            // Handle the exception (e.g., logging, error reporting)
        }
    }

    override suspend fun deleteDoctor(doctor: Doctor) {
        try {
            RetrofitInstance.api.deleteDoctor(doctor._id)
        } catch (e: Exception) {
            // Handle the exception (e.g., logging, error reporting)
        }
    }

    override suspend fun updateDoctor(doctor: Doctor) {
        try {
            RetrofitInstance.api.updateDoctor(doctor._id, doctor)
        } catch (e: Exception) {
            // Handle the exception (e.g., logging, error reporting)
        }
    }


    override suspend fun getDoctorFromId(userId: String): Doctor? {
        val getDoctor: Call<Doctor> = RetrofitInstance.api.getDoctorFromId(userId)
        var data: Doctor? = null

        try {
            getDoctor.enqueue(object : Callback<Doctor?> {
                override fun onResponse(call: Call<Doctor?>, response: Response<Doctor?>) {
                    if (response.isSuccessful) {
                        data = response.body()
                    }
                }

                override fun onFailure(call: Call<Doctor?>, t: Throwable) {
                    // Handle failure if needed
                }
            })
        } catch (e: Exception) {
            // Handle exceptions if needed
        }

        return data
    }

    override suspend fun getPatientFromId(_id: ObjectId): Patient? {
        val getPatient: Call<Patient> = RetrofitInstance.api.getPatientFromId(_id)
        var data: Patient? = null

        try {
            getPatient.enqueue(object : Callback<Patient?> {
                override fun onResponse(call: Call<Patient?>, response: Response<Patient?>) {
                    if (response.isSuccessful) {
                        data = response.body()
                    }
                }

                override fun onFailure(call: Call<Patient?>, t: Throwable) {
                    // Handle failure if needed
                }
            })
        } catch (e: Exception) {
            // Handle exceptions if needed
        }

        return data
    }


    override suspend fun insertPatient(patient: Patient) {
        try {
            RetrofitInstance.api.insertPatient(patient)
        } catch (e: Exception) {
            // Handle the exception (e.g., logging, error reporting)
        }
    }

    override suspend fun updatePatient(patient: Patient) {
        try {
            RetrofitInstance.api.updatePatient(patient._id, patient)
        } catch (e: Exception) {
            // Handle the exception (e.g., logging, error reporting)
        }
    }

    override suspend fun deletePatient(patient: Patient) {
        try {
            RetrofitInstance.api.deletePatient(patient._id)
        } catch (e: Exception) {
            // Handle the exception (e.g., logging, error reporting)
        }
    }

    override suspend fun getUpcomingAppointmentsOfUser(): List<Appointment> {
        return try {
            RetrofitInstance.api.getUpcomingAppointmentsOfUser(MyApp.doctor._id)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getPastAppointmentsOfUser(): List<Appointment> {
        return try {
            RetrofitInstance.api.getPastAppointmentsOfUser(MyApp.doctor._id)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun insertAppointment(appointment: Appointment) {
        try {
            RetrofitInstance.api.insertAppointment(appointment)
        } catch (e: Exception) {
            Log.d("insert appointment", "failed")
        }
    }

    override suspend fun setAppointment(
        doctor: Doctor,
        patient: Patient,
        appointment: Appointment
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAppointment(appointment: Appointment) {
        try {
            RetrofitInstance.api.deleteAppointment(appointment._id)
        } catch (e: Exception) {
            Log.d("delete Appointment", "failed")
        }
    }

    override suspend fun getCategoryDoctor(category: String): List<Doctor> {
        return try {
            RetrofitInstance.api.getCatagoryDoctor(category)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun auThenticateUserAsPatient(email: String, password: String): Patient? {
        val getPatient: Call<Patient> = RetrofitInstance.api.authenticateUserAsPatient(email,password)
        var data: Patient? = null

        getPatient.enqueue(object : Callback<Patient?> {
            override fun onResponse(call: Call<Patient?>, response: Response<Patient?>) {
                if (response.isSuccessful){
                    data = response.body()
                    Log.d("authenticate" ,"as a Patient")
                }else{
                    Log.d("dflkdf", "kdf")
                }
            }

            override fun onFailure(call: Call<Patient?>, t: Throwable) {
                // handle later
            }
        })
        return data
    }

    override suspend fun auThenticateUserAsDoctor(email: String, password: String): Doctor? {

        val getDoctor: Call<Doctor> = RetrofitInstance.api.authenticateUserAsDoctor(email, password)
        var data: Doctor? = null

        getDoctor.enqueue(object : Callback<Doctor?> {
            override fun onResponse(call: Call<Doctor?>, response: Response<Doctor?>) {
                if (response.isSuccessful) {
                    data = response.body()
                    Log.d("authenticate", "as a Doctor")
                } else {
                    Log.d("dflkdf", "kdf")
                }
            }

            override fun onFailure(call: Call<Doctor?>, t: Throwable) {
                // handle later
            }
        })
        return data

    }
}
