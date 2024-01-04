package com.example.doctors_appointment.classes

import kotlin.random.Random

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


// Dummy Generator




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

