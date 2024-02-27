package com.example.doctors_appointment.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.repository.MongoRepository
import com.example.doctors_appointment.util.ProfileEvent
import com.example.doctors_appointment.util.Screen
import com.example.doctors_appointment.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
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

    var newPatient: Patient = Patient().apply {
        _id = user._id
        name = user.name
        email = user.email
        gender = user.gender
        contactNumber = user.contactNumber
        height = user.height
        weight = user.weight
        notification = user.notification
        medicalHistory = user.medicalHistory
        dateOfBirth = user.dateOfBirth
        profileImage = user.profileImage
        password = user.password
    }

    private val _uiEvents = Channel<UiEvent>()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun OnEvent(event: ProfileEvent){
        when(event){
            is ProfileEvent.EditEmail -> {
                newPatient.email = event.email
            }
            is ProfileEvent.EditGender -> {
                newPatient.gender = event.gender
            }
            is ProfileEvent.EditName -> {
                newPatient.name = event.name
            }
            is ProfileEvent.EditHeight -> {
                newPatient.height = event.height
            }
//            is ProfileEvent.Edi -> {
//                newPatient.weight = event.height
//            }
            is ProfileEvent.EditNumber -> {
                newPatient.contactNumber = event.contact
            }
            is ProfileEvent.EditNotificationStatus -> {
                newPatient.notification = event.notificationStatus
            }
            is ProfileEvent.OnSave -> {
                viewModelScope.launch {
                    repository.updatePatient(newPatient)
                }
            }
            else -> Unit
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){  // sends flow throw the channel
        viewModelScope.launch{   // this binds the lifecycle of coroutine with our viewmodel
            _uiEvents.send(uiEvent)
        }
    }

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

    fun updatePatient(patient: Patient){
        viewModelScope.launch {
            repository.updatePatient(patient)
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