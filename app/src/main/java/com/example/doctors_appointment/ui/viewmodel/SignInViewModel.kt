package com.example.doctors_appointment.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.MyApp.Companion.realm
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.repository.AuthRepository
import com.example.doctors_appointment.data.repository.AuthRepositoryImpl
import com.example.doctors_appointment.data.repository.MongoRepository
import com.example.doctors_appointment.util.Resource
import com.example.doctors_appointment.util.Screen
import com.example.doctors_appointment.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.query
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val repository: MongoRepository
): ViewModel() {
     // One Time events receives flow One to one

    private val _uiEvents = Channel<UiEvent>()                  // mutable hence made private(so that no one can change it outside the viewmodel) for sending event

    val uiEvents = _uiEvents.receiveAsFlow()                   // immutable to use in the outside viewModel (UI) for collecting the flow

    fun OnLoginClick(email: String, password: String){

        // firebase authentication

        viewModelScope.launch {
            authRepository.loginUser(email, password).collect{ result ->
                when(result){
                    is Resource.Success -> {

                        val doctor = auThenticateUserAsDoctor(email, password)
                        val patient = auThenticateUserAsPatient(email, password)

                        if (doctor != null) {
                            MyApp.doctor = doctor
//                            sendUiEvent(UiEvent.Navigate(Screen.fkldflk.route))
                            sendUiEvent(UiEvent.ShowSnackBar("login as a patient."))
                        } else if (patient != null) {
                            MyApp.patient = patient
                            sendUiEvent(UiEvent.Navigate(Screen.mainHome.route))
                        }

                    }
                    is Resource.Loading -> {
                        sendUiEvent(UiEvent.ShowSnackBar("Wait for a while for authentication."))
                    }
                    is Resource.Error -> {
                        sendUiEvent(UiEvent.ShowSnackBar("Invalid email or password."))
                    }
                }

            }
        }
    }

    fun auThenticateUserAsPatient(email: String, password: String): Patient? {

        //Log.d("entered as patient", "entered to auth patient")

        val user = realm
            .query<Patient>("email == $0 and password == $1", email, password)
            .first()
            .find()

        //if(user is Patient) Log.d("entered as patient", "auth patient successful")

        return user

    }

    fun auThenticateUserAsDoctor(email: String, password: String): Doctor? {
        val user = realm
            .query<Doctor>("email == $0 and password == $1", email, password)
            .first()
            .find()
        Log.d("entered as doctor", "auth  doctor done")
        return user
    }
    private fun sendUiEvent(uiEvent: UiEvent){  // sends flow throw the channel
        viewModelScope.launch{   // this binds the lifecycle of coroutine with our viewmodel
            _uiEvents.send(uiEvent)
        }
    }

}