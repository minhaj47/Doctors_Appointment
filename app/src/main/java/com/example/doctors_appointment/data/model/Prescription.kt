package com.example.doctors_appointment.data.model

import androidx.room.Entity
import java.util.*

@Entity
class Prescription(
    val prescriptionId: String = UUID.randomUUID().toString(),
    val problem: String,
    val medications: List<String>,
    val diagnosis: List<String>,
    val advice: String
)
