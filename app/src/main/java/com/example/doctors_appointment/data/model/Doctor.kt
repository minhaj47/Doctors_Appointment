package com.example.doctors_appointment.data.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
class Doctor : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var email: String = ""
    var password: String = ""
    var contactNumber: String = ""
    var notification: Boolean? = null

    var gender: Boolean? = null // true for male and false for female
    var address: String = ""
    var rating: Double = 0.0
    var reviews: RealmList<String> = realmListOf()
    var bmdcRegistrationNumber: String = ""
    var qualifications: RealmList<String> = realmListOf()
    var about: String = ""
    @Index var medicalSpecialty: String = ""
    var profileImage: String = ""
    var availabilityStatus: RealmList<Boolean> = realmListOf(*List(6000){ true }.toTypedArray())
    var consultationFee: Double = 0.0
    var experience: Int = 0
    var docoument: RealmList<String> = realmListOf()
    var appointments: RealmList<Appointment> = realmListOf()
}
