package com.example.doctors_appointment.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Appointment: RealmObject{
    @PrimaryKey var _id: ObjectId = ObjectId()
    var patientId: String = ""
    var doctorID: String = ""
    var prescription: Prescription? = Prescription()
    var appointmentDate: Long? = null
    var status: String = ""
    var rating: Int = 0
    var review: String = ""
    var notes: String = ""
//    var owner_id: String = ""
//
//    constructor(ownerId: String) {
//        owner_id = ownerId
//    }
}
