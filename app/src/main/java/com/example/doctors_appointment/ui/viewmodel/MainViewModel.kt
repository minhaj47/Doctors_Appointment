package com.example.doctors_appointment.ui.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.doctors_appointment.MyApp
//import com.example.doctors_appointment.data.model.Appointment
//import com.example.doctors_appointment.data.model.Doctor
//import com.example.doctors_appointment.data.model.Patient
//import com.example.doctors_appointment.data.model.Prescription
//import io.realm.kotlin.UpdatePolicy
//import io.realm.kotlin.ext.query
//import io.realm.kotlin.ext.realmListOf
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.launch
//import org.mongodb.kbson.ObjectId
//import java.text.SimpleDateFormat
//import java.time.LocalDate.*
//import java.util.Date
//import java.util.Locale
//
//class MainViewModel: ViewModel() {
//
//    private val realm = MyApp.realm
//
//    val patients = realm
//        .query<Patient>()
//        .asFlow()
//        .map { results ->
//            results.list.toList()
//        }
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(),
//            emptyList()
//        )
//
////    init {
////        createSampleEntries()
////    }
//    private fun createSampleEntries() {
//        viewModelScope.launch {
//            realm.write{
//
//                val doctor1 = Doctor().apply {
//                    _id = ObjectId()
//                    name = "Dr. John Doe"
//                    about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
//                    address = "123 Main Street, City, Country"
//                    email = "john.doe@example.com"
//                    appointments = realmListOf() // Initialize appointments list
//                    availabilityStatus = realmListOf<Boolean>().apply { add(true) }
//                    bmdcRegistrationNumber = "123456789"
//                    consultationFee = 52.0
//                    contactNumber = "123-456-7890"
//                    gender = false
//                    experience = 10
//                    docoument = realmListOf("document_link")
//                    medicalSpecialty = "General Medicine"
//                    qualifications = realmListOf("MBBS", "MD")
//                    profileImage = "profile_image_link"
//                }
//
//                val doctor2 = Doctor().apply {
//                    _id = ObjectId()
//                    name = "Dr. Jane Smith"
//                    about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
//                    address = "456 Elm Street, City, Country"
//                    email = "jane.smith@example.com"
//                    appointments = realmListOf() // Initialize appointments list
//                    availabilityStatus = realmListOf<Boolean>().apply { add(true) }
//                    bmdcRegistrationNumber = "987654321"
//                    consultationFee = 60.0
//                    contactNumber = "987-654-3210"
//                    gender = true
//                    experience = 8
//                    docoument = realmListOf("document_link")
//                    medicalSpecialty = "Pediatrics"
//                    qualifications = realmListOf("MBBS", "MD (Pediatrics)")
//                    profileImage = "profile_image_link"
//                }
//
//                val prescription1 = Prescription().apply {
//                    problem = "Headache"
//                    medications = realmListOf("Paracetamol")
//                    diagnosis = realmListOf("Migraine")
//                    advice = "Rest and plenty of fluids"
//                }
//
//                val prescription2 = Prescription().apply {
//                    problem = "Fever"
//                    medications = realmListOf("Ibuprofen")
//                    diagnosis = realmListOf("Viral infection")
//                    advice = "Bed rest and hydrate"
//                }
//
//                val prescription3 = Prescription().apply {
//                    problem = "Fever"
//                    medications = realmListOf("Ibuprofenfklf")
//                    diagnosis = realmListOf("Viral infecfkjftion")
//                    advice = "Bed rest andfoj hydrate"
//                }
//
//
//                val user1 = Patient().apply {
//                    _id = ObjectId()
//                    name = "Alice Johnson"
//                    height = 170.0
//                    weight = 65.0
//                    gender = true // Assuming true for female, false for male
//                    dateOfBirth = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("1990-01-01")
//                    contactNumber = "123-456-7890"
//                    email = "alice.johnson@example.com"
//                    profileImage = "profile_image_link"
//                    notification = true // Assuming true for enabled, false for disabled
//                }
//
//                val user2 = Patient().apply {
//                    _id = ObjectId()
//                    name = "Bob Smith"
//                    height = 180.0
//                    weight = 75.0
//                    gender = false // Assuming true for female, false for male
//                    dateOfBirth = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("1985-05-15")
//                    contactNumber = "987-654-3210"
//                    email = "bob.smith@example.com"
//                    profileImage = "profile_image_link"
//                    notification = true // Assuming true for enabled, false for disabled
//                }
//
//
//                val appointment1 = Appointment().apply {
//                    _id = ObjectId()
//                    notes = "Follow-up appointment"
//                    review = "Good"
//                    status = "Confirmed"
//                    doctor = doctor1
//                    patient = user1
//                    appointmentDate = Date() // Use current date as appointment date
//                    prescription = prescription1
//                    rating = 4
//                }
//
//                val appointment2 = Appointment().apply {
//                    _id = ObjectId()
//                    notes = "Annual check-up"
//                    review = "Excellent"
//                    status = "Confirmed"
//                    doctor = doctor2
//                    patient = user2
//                    appointmentDate = Date()
//                    prescription = prescription2
//                    rating = 5
//                }
//
//                val appointment3 = Appointment().apply {
//                    _id = ObjectId()
//                    notes = "Regular check-up"
//                    review = "Satisfactory"
//                    status = "Confirmed"
//                    doctor = doctor2
//                    patient = user1
//                    appointmentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("1985-05-15")// Use current date as appointment date
//                    prescription = prescription3
//                    rating = 4
//                }
//
//
//                // schema relations that are not defined
//
//                prescription1.appointment = appointment1
//                prescription2.appointment = appointment2
//                prescription3.appointment = appointment3
//
//
//                user1.medicalHistory = realmListOf(
//                    prescription3,
//                    prescription1
//                )
//                user2.medicalHistory = realmListOf(
//                    prescription2
//                )
//
//                doctor2.appointments = realmListOf(appointment2, appointment3)
//                doctor1.appointments = realmListOf(appointment1)
//
//
//
//                // make unmanaged managed
//
//                copyToRealm(user1, updatePolicy = UpdatePolicy.ALL)
//                copyToRealm(user2, updatePolicy = UpdatePolicy.ALL)
//
//                copyToRealm(doctor1, updatePolicy = UpdatePolicy.ALL)
//                copyToRealm(doctor2, updatePolicy = UpdatePolicy.ALL)
//
//                copyToRealm(appointment1, updatePolicy = UpdatePolicy.ALL)
//                copyToRealm(appointment2, updatePolicy = UpdatePolicy.ALL)
//                copyToRealm(appointment3, updatePolicy = UpdatePolicy.ALL)
//
//            }
//
//        }
//    }
//}