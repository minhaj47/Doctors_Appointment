package com.example.doctors_appointment.data.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.Date


class Patient: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var email: String = ""
    var password: String = ""
    var contactNumber: String = ""
    var notification: Boolean? = null
    var height: Double = 0.0
    var weight: Double = 0.0
    var gender: Boolean? = null
    var dateOfBirth: String = ""
    var medicalHistory: RealmList<Prescription> = realmListOf()
    var profileImage: String = ""
}
