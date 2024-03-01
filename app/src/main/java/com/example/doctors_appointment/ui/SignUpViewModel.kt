package com.example.doctors_appointment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.authentication.AuthRepository
import com.example.doctors_appointment.authentication.AuthUser
import com.example.doctors_appointment.authentication.ResultState
import com.example.doctors_appointment.util.Screen
import com.example.doctors_appointment.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _uiEvents = Channel<UiEvent>()                  // mutable hence made private(so that no one can change it outside the viewmodel) for sending event

    val uiEvents = _uiEvents.receiveAsFlow()                   // immutable to use in the outside viewModel (UI) for collecting the flow

    fun OnSignUpClick(email: String, name:String, password: String, confirmPassword: String, asPatient: Boolean){

        if(password != confirmPassword){
            sendUiEvent(UiEvent.ShowSnackBar("Entered passwords are not same!"))
        }else if(name == "" || email == ""){
            sendUiEvent(UiEvent.ShowSnackBar("Your name or email could not be empty!"))
        }else if(password.length < 8){
            sendUiEvent(UiEvent.ShowSnackBar("Password is too short"))
        }else{
            viewModelScope.launch {
                val authUser = AuthUser(email, password)
                authRepository.registerUser(authUser).collect { result ->
                    when (result) {
                        is ResultState.Success -> {
                            if (asPatient) {
                                createPatient(name, email, password)
                            } else {
                                createDoctor(name, email, password)
                            }
                            sendUiEvent(UiEvent.Navigate(Screen.signIn.route))
                            sendUiEvent(UiEvent.ShowSnackBar("Sign Up Successful. Please Login."))
                        }

                        is ResultState.Loading -> {
                            sendUiEvent(UiEvent.ShowSnackBar("Wait for a while to register an user."))
                        }

                        is ResultState.Error -> {
                            println("error inside signup" + result.message)
                            sendUiEvent(UiEvent.ShowSnackBar("Authentication failed."))
                        }

                        else -> {}
                    }

                }
            }


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

        val signUpOp = SignUpOp()
        viewModelScope.launch {
            signUpOp.insertPatient(patient)
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

        val signUpOp = SignUpOp()
        viewModelScope.launch {
            signUpOp.insertDoctor(doctor)
        }
        return doctor
    }

    private fun sendUiEvent(uiEvent: UiEvent){  // sends flow throw the channel
        viewModelScope.launch{   // this binds the lifecycle of coroutine with our viewmodel
            _uiEvents.send(uiEvent)
        }
    }

}