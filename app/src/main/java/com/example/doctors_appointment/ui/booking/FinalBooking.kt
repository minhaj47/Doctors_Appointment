package com.example.doctors_appointment.ui.booking

import android.telecom.Call.Details
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.doctors_appointment.data.appointments_data
import com.example.doctors_appointment.data.doctors_data
import com.example.doctors_appointment.data.model.Screen
import com.example.doctors_appointment.ui.AppointmentRow
import com.example.doctors_appointment.ui.mainHome.BottomNavigationItem
import com.example.doctors_appointment.ui.mainHome.fontInria
import com.example.doctors_appointment.ui.theme.Indigo50
import com.example.doctors_appointment.ui.theme.Indigo900

@Composable
fun FinalBooking(
    navController: NavController,
    doctorId: String?,
    slotNo: Int
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Indigo50)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        val doctor = doctors_data[0]
        val appointment = appointments_data[0]

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "APPOINTMENT\nDETAIL",
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = fontInria,
            color = Indigo900,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(40.dp))

        AppointmentRow(
            appointment, doctor,
            Modifier
                .padding(40.dp)
                .scale(1.4f)
        )

        Spacer(modifier = Modifier.height(35.dp))

        OutlinedButton(
            onClick = {
                navController.navigate(Screen.appointment.route)
            }
        ) {
            Text(
                text = "CONFIRM BOOKING",
                fontSize = 25.sp,
                fontFamily = fontInria,
            )
        }
    }
}