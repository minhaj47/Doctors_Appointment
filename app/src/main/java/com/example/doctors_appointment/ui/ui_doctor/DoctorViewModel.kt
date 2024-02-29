package com.example.doctors_appointment.ui.ui_doctor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.repository.MongoRepository
import com.example.doctors_appointment.util.ProfileEvent
import com.example.doctors_appointment.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DoctorViewModel(
    val repository: MongoRepository
) : ViewModel() {


    var user = MyApp.doctor

    var newDoctor = Doctor().apply {
        _id = user._id
        name = user.name
        email = user.email
        password = user.password
        contactNumber = user.contactNumber
        notification = user.notification
        gender = user.gender
        address = user.address
        rating = user.rating
        reviews.addAll(user.reviews) // Adding all elements from user's reviews to newDoctor's reviews
        bmdcRegistrationNumber = user.bmdcRegistrationNumber
        qualifications.addAll(user.qualifications) // Adding all elements from user's qualifications to newDoctor's qualifications
        about = user.about
        medicalSpecialty = user.medicalSpecialty
        profileImage = user.profileImage
        availabilityStatus.addAll(user.availabilityStatus) // Adding all elements from user's availabilityStatus to newDoctor's availabilityStatus
        consultationFee = user.consultationFee
        experience = user.experience
        docoument.addAll(user.docoument) // Adding all elements from user's docoument to newDoctor's docoument
        appointments.addAll(user.appointments) // Adding all elements from user's appointments to newDoctor's appointments
    }


    private val _uiEvents = Channel<UiEvent>()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun OnEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.EditEmail -> {
                newDoctor.email = event.email
            }

            is ProfileEvent.EditGender -> {
                println("inside edit gender")
                newDoctor.gender = event.gender
                assert(newDoctor.gender == event.gender)
            }

            is ProfileEvent.EditName -> {
                newDoctor.name = event.name
            }

            is ProfileEvent.EditNumber -> {
                newDoctor.contactNumber = event.contact
            }

            is ProfileEvent.EditNotificationStatus -> {
                newDoctor.notification = event.notificationStatus
            }

            is ProfileEvent.OnSave -> {
                viewModelScope.launch {
                    repository.updateDoctor(newDoctor)
                    user = newDoctor
                }
            }

            else -> {}
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent) {  // sends flow throw the channel
        viewModelScope.launch {   // this binds the lifecycle of coroutine with our viewmodel
            _uiEvents.send(uiEvent)
        }
    }

    fun getTime(slot: Int): Double {
        return when (slot) {
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
}