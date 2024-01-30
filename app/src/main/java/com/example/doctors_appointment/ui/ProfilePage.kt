package com.example.doctors_appointment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.doctors_appointment.R
import com.example.doctors_appointment.data.doctors_data
import com.example.doctors_appointment.data.generateDummyDoctors
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.ui.mainHome.RoundImage
import com.example.doctors_appointment.ui.mainHome.fontInria
import com.example.doctors_appointment.ui.theme.Indigo50
import com.example.doctors_appointment.ui.theme.Indigo500
import com.example.doctors_appointment.ui.theme.Indigo900

@Composable
fun ProfilePage(navController: NavController) {

    val patient: Patient = generateDummyPatient()

    Column(
        modifier = Modifier
            .background(Indigo50)
            .padding(10.dp),
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = fontInria,
            color = Indigo900,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ){
            RoundImage(
                image = painterResource(id = R.drawable.man),
                modifier = Modifier.height(100.dp)
            )
            Text(
                text = patient.name,
                fontFamily = fontInria,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 65.dp)
        ){
            item{
                Profile(patient)
            }
        }
    }


}


@Composable
fun Profile(
    patient: Patient
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 15.dp, bottom = 5.dp, top = 5.dp)
            .clip(RoundedCornerShape(5))
            .border(2.dp, Indigo500, RoundedCornerShape(5))
            .background(Color.White)
        //.background(Indigo50)

    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 12.dp, end = 10.dp, bottom = 8.dp)
        ) {


            Text(
                text = "Name:",
                fontSize = 25.sp,
                fontFamily = fontInria,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = patient.name,
                fontSize = 23.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Height",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.height.toString(),
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Weight",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.weight.toString(),
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Gender",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if(patient.gender) "Male" else "Female",
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Date of Birth:",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.dateOfBirth,
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )


            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Phone:",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.contactNumber,
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Email Address:",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.email,
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Notification:",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.notification.toString(),
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {


            }




        }
    }
}


@Composable
fun MedicalHistory(
    appointment: Appointment
){

}