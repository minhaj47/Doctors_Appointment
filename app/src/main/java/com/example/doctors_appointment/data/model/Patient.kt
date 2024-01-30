package com.example.doctors_appointment.data.model

import android.media.MediaSession2Service.MediaNotification
import androidx.room.Entity

@Entity
class Patient(
    var patientID: Int,
    var height: Double,
    var weight: Double,
    var name: String,
    var gender: Boolean,
    var dateOfBirth: String,
    var medicalHistory: List<String>,
    var contactNumber: String,
    var email: String,
    var profileImage: String,
    var notification: Boolean

)