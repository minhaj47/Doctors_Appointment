package com.example.doctors_appointment.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.repository.MongoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OthersViewModel @Inject constructor(
    private val repository: MongoRepository
): ViewModel() {

    var doctors = mutableStateOf(emptyList<Doctor>())
    var categoryDoctors = mutableStateOf(emptyList<Doctor>())
    var doctor = Doctor()
    var upcomingAppointments = mutableStateOf(emptyList<Appointment>())
    var pastAppointments = mutableStateOf(emptyList<Appointment>())
    var user = MyApp.patient

    fun getDoctorFromId(userId: String){

        viewModelScope.launch {
            doctor = repository.getDoctorFromId(userId)!!
        }
    }

    fun getDoctorFromCategory(category: String){
        viewModelScope.launch {
            repository.getCategoryDoctor(category).collect {
                categoryDoctors.value = it
            }
        }

    }

    init {
        viewModelScope.launch {
            repository.getAllDoctors().collect{
                doctors.value = it
            }
        }

        viewModelScope.launch {
            repository.getUpcomingAppointmentsOfUser().collect{
                upcomingAppointments.value = it
            }
            repository.getPastAppointmentsOfUser().collect{
                pastAppointments.value = it
            }
        }
    }

}