package com.example.doctors_appointment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Prescription
import com.example.doctors_appointment.data.repository.MongoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val repository: MongoRepository
): ViewModel() {

    var doctor1 = Doctor()
    var user = MyApp.patient
    var appointment = Appointment()
    fun getDoctorFromId(userId: String){

        viewModelScope.launch {
            doctor1 = repository.getDoctorFromId(userId)!!
        }
    }



//    fun getAppointmentFromId(userId: String){
//
//        viewModelScope.launch {
//            appointment = repository.getAppointmentFromId(userId)!!
//        }
//    }

    fun createAppointment(slotNo: Int): Appointment{

        appointment.apply {
            doctorID = doctor1._id.toHexString()
            patientId = user._id.toHexString()
            appointment.appointmentDate = getAppointmentTime(slotNo)
        }

        return appointment
    }

    fun getAppointmentTime(slotNo: Int): Long {
        val day = slotNo / 12
        val time = getTime(slotNo % 12)

        val minutes = if (hasFraction(time)) 30 else 0

        val tomorrow = LocalDate.now().plusDays(day.toLong())
        val localTime = LocalDateTime.of(tomorrow, LocalTime.of(time.toInt(), minutes))

        return Date.from(localTime.atZone(ZoneId.systemDefault()).toInstant()).time
    }


    fun getTime(slot: Int): Double{
        return when(slot){
                0 -> 9.30
                1 -> 10.00
                2 -> 10.30
                3 -> 11.00
                4 -> 11.30
                5 -> 12.00
                6 -> 6.30
                7 -> 7.00
                8 -> 7.30
                9 -> 8.00
                10 -> 8.30
                11 -> 9.00
                else -> -1.0
        }
    }

    fun hasFraction(number: Double): Boolean {
        return number != number.toInt().toDouble()
    }

    fun onConfirm() {
        viewModelScope.launch {
            println(appointment.toString())
            println(getDoctorFromId(appointment.doctorID))
            println(appointment.patientId)
            println(appointment.appointmentDate)
            println(appointment.prescription.toString())
            repository.insertAppointment(appointment)
        }
    }
}