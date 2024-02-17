package com.example.doctors_appointment.di

import android.util.Log
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.data.model.Prescription
import com.example.doctors_appointment.data.repository.MongoRepoImplementation
import com.example.doctors_appointment.data.repository.MongoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.realmListOf
import org.mongodb.kbson.ObjectId
import java.nio.file.Files.write
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule{

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Doctor::class,
                Patient::class,
                Appointment::class,
                Prescription::class
            )
        ).build()

        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideMongoRepo(realm: Realm): MongoRepository{
        return MongoRepoImplementation(realm)
    }
}