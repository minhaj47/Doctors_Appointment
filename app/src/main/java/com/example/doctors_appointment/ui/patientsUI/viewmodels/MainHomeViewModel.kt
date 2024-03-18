package com.example.doctors_appointment.ui.patientsUI.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.repository.MongoRepository
import kotlinx.coroutines.launch

class MainHomeViewModel(
    repository: MongoRepository
) : ViewModel() {


    var doctors = mutableStateOf(emptyList<Doctor>())
    var patient = MyApp.patient


    init {
        viewModelScope.launch {
            doctors.value = repository.getAllDoctors()
        }
    }

}