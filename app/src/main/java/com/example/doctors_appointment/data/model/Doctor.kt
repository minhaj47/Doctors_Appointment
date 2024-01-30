package com.example.doctors_appointment.data.model

import androidx.room.Entity

@Entity
class Doctor(
    val doctorID: String,
    val name: String,
    val gender: Boolean, // true for male and false for female
    val contactNumber: String,
    val email: String,
    val address: String,
    val rating: Double,
    val reviews: List<String>,
    val bmdcRegistrationNumber: String,
    val qualifications: List<String>,
    val specialties: String,
    val medicalSpecialty: String,
    val profileImage: String,
    val availabilityStatus: Boolean,
    val consultationFee: Double,
    val notificationPreferences: Boolean,
    val docoument: List<String>,
    val appointments: List<String>
) {
    // Add any additional methods or behavior specific to Doctor if needed
}
