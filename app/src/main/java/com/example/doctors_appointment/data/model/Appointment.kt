package com.example.doctors_appointment.data.model

import androidx.room.Entity
import java.util.Date
import java.util.UUID

@Entity
class Appointment(
    val appointmentId: String = UUID.randomUUID().toString(),
    val patientID: String,
    val doctorID: String,
    val prescriptionID: String,
    val appointmentDate: Date,
    val status: String,
    val rating: Int,
    val review: String,
    val notes: String
)

