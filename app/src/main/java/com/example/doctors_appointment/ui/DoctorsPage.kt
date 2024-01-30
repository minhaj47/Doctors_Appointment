package com.example.doctors_appointment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.doctors_data
import com.example.doctors_appointment.ui.mainHome.NavBar
import com.example.doctors_appointment.ui.mainHome.fontInria
import com.example.doctors_appointment.ui.theme.Indigo50
import com.example.doctors_appointment.ui.theme.Indigo500
import com.example.doctors_appointment.ui.theme.Indigo900
import com.example.doctors_appointment.ui.mainHome.NavigationViewModel

@Composable
fun DoctorsPage(
    navController: NavController,
) {

    val doctors = doctors_data

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .background(Indigo50)
                .padding(10.dp),
        ) {
            Text(
                text = "Find your Doctor",
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = fontInria,
                color = Indigo900,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 65.dp)
            ){
                items(doctors_data.size){
                    DoctorsRow(doctor = doctors_data[it])
                }
            }
        }
    }

}


@Composable
fun DoctorsRow(
    doctor: Doctor
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 15.dp, bottom = 5.dp, top = 5.dp)
            .clip(RoundedCornerShape(10))
            .border(2.dp, Indigo500, RoundedCornerShape(10))
            .background(Color.White)
        //.background(Indigo50)

    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 12.dp, end = 10.dp, bottom = 8.dp)
        ) {

            Text(
                text = doctor.name,
                fontSize = 15.sp,
                fontFamily = fontInria,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = doctor.medicalSpecialty,
                fontSize = 10.sp,
                fontFamily = fontInria,
                color = Indigo900
            )
            Text(
                text = "Qualifications:",
                fontSize = 12.sp,
                fontFamily = fontInria,
                color = Color.Black
            )
            val qualificationText = doctor.qualifications.joinToString(", ")
            Text(
                text = qualificationText,
                fontSize = 8.sp,
                fontFamily = fontInria,
                color = Indigo900
            )
            Text(
                text = "Rating:",
                fontSize = 12.sp,
                fontFamily = fontInria,
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Indigo900,
                    modifier = Modifier.width(20.dp)
                )

                Text(
                    text = String.format("%.2f", doctor.rating),
                    fontSize = 15.sp,
                    fontFamily = fontInria,
                    color = Indigo900
                )

            }

        }
    }
}