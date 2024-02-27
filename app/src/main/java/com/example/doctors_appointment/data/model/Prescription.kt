package com.example.doctors_appointment.data.model

import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.query.RealmResults
import java.util.*
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

// Appointment (one to one) -> Prescription
// hence no need of id for prescription

class Prescription: EmbeddedRealmObject{
    val appointment: Appointment by backlinks(Appointment::prescription)
    var problem: String = ""
    var medications: RealmList<String> = realmListOf()
    var diagnosis: RealmList<String> = realmListOf()
    var advice: String = ""
}





