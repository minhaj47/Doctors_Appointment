package com.example.doctors_appointment.data

import com.example.doctors_appointment.data.DummyDataGenerator.generateDummyPrescriptions
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.model.Prescription
import java.util.Date
import java.util.UUID
import kotlin.random.Random

// Doctors data

public val doctors_data = generateDummyDoctors(30)
public val patient_data = generateDummyPatient()
public val appointments_data = generateDummyAppointments(30)
public val prescriptions_data = generateDummyPrescriptions(30)

fun generateDummyDoctors(count: Int): List<Doctor> {
    val dummyDoctors = mutableListOf<Doctor>()

    repeat(count) {
        dummyDoctors.add(
            Doctor(
                doctorID = "D${it + 1}",
                name = generateRandomName(),
                gender = Random.nextBoolean(),
                contactNumber = generateRandomPhoneNumber(),
                email = generateRandomEmail(),
                address = generateRandomAddress(),
                rating = Random.nextDouble(1.0, 5.0),
                reviews = generateRandomReviews(),
                bmdcRegistrationNumber = "BMDC${it + 1}",
                qualifications = generateRandomQualifications(),
                specialties = "Specialty${Random.nextInt(1, 6)}",
                medicalSpecialty = "MedicalSpecialty${Random.nextInt(1, 4)}",
                profileImage = "profile_image_$it.jpg",
                availabilityStatus = Random.nextBoolean(),
                consultationFee = Random.nextDouble(50.0, 300.0),
                notificationPreferences = Random.nextBoolean(),
                docoument = generateRandomDocuments(),
                appointments = generateRandomAppointments()
            )
        )
    }

    return dummyDoctors
}

fun generateRandomName(): String {
    val prefixes = listOf("Dr.", "Prof.", "Mr.", "Ms.")
    val firstNames = listOf("John", "Jane", "Alice", "Bob", "David", "Emily", "Michael", "Sophia", "Daniel", "Olivia")
    val lastNames = listOf("Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "García", "Rodriguez", "Martinez")

    return "${prefixes.random()} ${firstNames.random()} ${lastNames.random()}"
}

fun generateRandomPhoneNumber(): String {
    return "+1-${Random.nextInt(100, 999)}-${Random.nextInt(1000, 9999)}"
}

fun generateRandomEmail(): String {
    val domains = listOf("gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "example.com")
    return "${generateRandomName().replace(" ", "")}@${domains.random()}"
}

fun generateRandomAddress(): String {
    val streets = listOf("Main St", "Oak Ave", "Maple Ln", "Cedar Dr", "Pine Blvd")
    return "${Random.nextInt(100, 999)} ${streets.random()}, City${Random.nextInt(1, 10)}, Country"
}

fun generateRandomReviews(): List<String> {
    val reviewTemplates = listOf("Excellent service!", "Very knowledgeable.", "Caring and compassionate.", "Great experience!", "Highly recommended.")
    return List(Random.nextInt(1, 5)) { reviewTemplates.random() }
}

fun generateRandomQualifications(): List<String> {
    val qualificationTypes = listOf("MD", "MBBS", "PhD", "MS", "Diploma")
    return List(Random.nextInt(1, 4)) { qualificationTypes.random() }
}

fun generateRandomDocuments(): List<String> {
    val documentTypes = listOf("License", "Certificates", "Reports", "ID Proof", "Insurance")
    return List(Random.nextInt(0, 5)) { documentTypes.random() }
}

fun generateRandomAppointments(): List<String> {
    val appointmentDates = mutableListOf<String>()
    repeat(Random.nextInt(0, 10)) {
        appointmentDates.add("2023-01-${Random.nextInt(1, 31)}")
    }
    return appointmentDates
}


//Patient's Data

fun generateDummyPatient(): Patient {
    val patientID = Random.nextInt(1000)
    val name = "Patient$patientID"
    val gender = if (Random.nextBoolean()) true else false
    val dateOfBirth = "2000-01-01" // Replace with your date format and logic
    val medicalHistory = generateDummyAppointmentIds(5) // Change the number as needed
    val contactNumber = "123-456-7890"
    val email = "patient@gmail.com"
    val profileImage = "https://example.com/profiles/$patientID.jpg"
    val height = Random.nextDouble(150.0, 200.0)
    val weight = Random.nextDouble(35.0, 200.0)
    val notification = Random.nextBoolean()

    return Patient(patientID, height, weight, name, gender, dateOfBirth, medicalHistory, contactNumber, email, profileImage, notification)
}

// Generate a list of dummy patients
fun generateDummyPatients(count: Int): List<Patient> {
    return List(count) { generateDummyPatient() }
}

// Generate a list of dummy Appointment IDs
private fun generateDummyAppointmentIds(count: Int): List<String> {
    return List(count) { UUID.randomUUID().toString() }
}


// Appointment

fun generateDummyAppointment(): Appointment {
    val appointmentId = UUID.randomUUID().toString()
    val patientID = UUID.randomUUID().toString()
    val doctorID = UUID.randomUUID().toString()
    val prescriptionID = UUID.randomUUID().toString()
    val appointmentDate = Date() // You may want to customize the date logic
    val status = "Scheduled"
    val rating = Random.nextInt(1, 6) // Generates a random rating between 1 and 5
    val review = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
    val notes = "Additional notes for the appointment."

    return Appointment(
        appointmentId,
        patientID,
        doctorID,
        prescriptionID,
        appointmentDate,
        status,
        rating,
        review,
        notes
    )
}

// Generate a list of dummy appointments
fun generateDummyAppointments(count: Int): List<Appointment> {
    return List(count) { generateDummyAppointment() }
}


// Prescription

object DummyDataGenerator {

    fun generateDummyPrescription(): Prescription {
        val prescriptionId = UUID.randomUUID().toString()
        val problem = "Lorem ipsum dolor sit amet."
        val medications = generateDummyMedications()
        val diagnosis = generateDummyDiagnosis()
        val advice = "Lorem ipsum advice."

        return Prescription(prescriptionId, problem, medications, diagnosis, advice)
    }

    private fun generateDummyMedications(): List<String> {
        val medicationsCount = Random.nextInt(1, 5)
        return List(medicationsCount) {
            "Medication${it + 1}"
        }
    }

    private fun generateDummyDiagnosis(): List<String> {
        val diagnosisCount = Random.nextInt(1, 4)
        return List(diagnosisCount) {
            "Diagnosis${it + 1}"
        }
    }

    // Generate a list of dummy prescriptions
    fun generateDummyPrescriptions(count: Int): List<Prescription> {
        return List(count) { generateDummyPrescription() }
    }
}
