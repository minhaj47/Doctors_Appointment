package com.example.doctors_appointment.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.repository.MongoRepository
import com.example.doctors_appointment.util.Screen
import com.example.doctors_appointment.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val repository: MongoRepository
): ViewModel() {

    var doctors = mutableStateOf(emptyList<Doctor>())
    var patient = MyApp.patient


//    fun OnEvent(event: UiEvent){
//        when(event){
//            //is UiEvent.Navigate()
//
//            else -> Unit
//        }
//    }



    init {
        viewModelScope.launch {
            repository.getAllDoctors().collect{
                doctors.value = it
            }
        }
    }


}