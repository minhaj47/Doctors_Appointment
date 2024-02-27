package com.example.doctors_appointment.util

sealed class ProfileEvent {
    data class EditName(val name: String) : ProfileEvent()
    data class EditEmail(val email: String): ProfileEvent()
    data class EditNumber(val contact: String): ProfileEvent()
    data class EditGender(val gender: Boolean): ProfileEvent()
    data class EditHeight(val height: Double): ProfileEvent()
    data class EditWeight(val weight: Double): ProfileEvent()
    data class EditNotificationStatus(val notificationStatus: Boolean): ProfileEvent()
    object OnSave: ProfileEvent()
}