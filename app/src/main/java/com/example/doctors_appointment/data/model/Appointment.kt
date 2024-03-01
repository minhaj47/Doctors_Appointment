package com.example.doctors_appointment.data.model

import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Appointment: RealmObject{
    @PrimaryKey var _id: ObjectId = ObjectId()
    val patient: RealmResults<Patient> by backlinks(Patient::medicalHistory)
    val doctor: RealmResults<Doctor> by backlinks(Doctor::appointments)
    var prescription: Prescription? = Prescription()
    var appointmentDate: Long? = null
    var status: String = ""
    var rating: Int = 0
    var review: String = ""
    var notes: String = ""
}
