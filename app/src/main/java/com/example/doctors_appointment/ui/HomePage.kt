package com.example.doctors_appointment.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctors_appointment.R
import com.example.doctors_appointment.ui.theme.*

@Composable
fun homePage(){

    var fontActor = FontFamily(
        Font(R.font.actor)
    )
    var fontInria = FontFamily(
        Font(R.font.inriasans_regular, FontWeight.Normal),
        Font(R.font.inriasans_bold, FontWeight.Bold),
        Font(R.font.inriasans_light, FontWeight.Light)
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Indigo100)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Doctor's Appointment",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = Indigo900,
                fontFamily = fontInria,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.doctors_appointment__1_),
                modifier = Modifier
                    .height(220.dp),
//                    .clip(RoundedCornerShape(30.dp)),
                contentDescription = null,
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(60.dp)
                    .width(210.dp),
                shape = ButtonDefaults.elevatedShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Indigo400
                )
            ) {
                Text(
                    text = "Get Started",
                    fontFamily = fontActor,
                    fontSize =  28.sp,
                    //fontWeight =  FontWeight.Bold
                )
            }

        }

    }
}
