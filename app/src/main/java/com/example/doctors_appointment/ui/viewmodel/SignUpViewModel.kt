package com.example.doctors_appointment.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.MyApp.Companion.realm
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.repository.MongoRepository
import com.example.doctors_appointment.util.Screen
import com.example.doctors_appointment.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.query
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: MongoRepository
): ViewModel() {

    private val _uiEvents = Channel<UiEvent>()                  // mutable hence made private(so that no one can change it outside the viewmodel) for sending event

    val uiEvents = _uiEvents.receiveAsFlow()                   // immutable to use in the outside viewModel (UI) for collecting the flow

    fun OnEvent(event: UiEvent){
        when(event){
            is UiEvent.SignUp -> {
                if(event.password != event.confirmPassword){
                    sendUiEvent(UiEvent.ShowSnackBar("Entered passwords are not same!"))
                }else if(event.name == "" || event.email == ""){
                    sendUiEvent(UiEvent.ShowSnackBar("Your name or email could not be empty!"))
                }else if(event.password.length < 8){
                    sendUiEvent(UiEvent.ShowSnackBar("Password is too short"))
                }else{
                    if(event.asPatient){
                        createPatient(event.name, event.email, event.password)
                    }else{
                        createDoctor(event.name, event.email, event.password)
                    }
                    sendUiEvent(UiEvent.Navigate(Screen.signIn.route))
                }
            }
            else -> Unit
        }
    }

    private fun createPatient(
        name: String,
        email: String,
        password: String,
    ): Patient {

        val patient = Patient()
        patient.name = name
        patient.email = email
        patient.password = password

        viewModelScope.launch {
            repository.insertPatient(patient)
        }
        return patient
    }

    private fun createDoctor(
        name: String,
        email: String,
        password: String,
    ): Doctor {

        val doctor = Doctor()
        doctor.name = name
        doctor.email = email
        doctor.password = password

        viewModelScope.launch {
            repository.insertDoctor(doctor)
        }
        return doctor
    }

    private fun sendUiEvent(uiEvent: UiEvent){  // sends flow throw the channel
        viewModelScope.launch{   // this binds the lifecycle of coroutine with our viewmodel
            _uiEvents.send(uiEvent)
        }
    }

}