package com.example.doctors_appointment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarViewMonth
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.outlined.CalendarViewMonth
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.doctors_appointment.data.appointments_data
import com.example.doctors_appointment.data.doctors_data
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.data.model.Screen
import com.example.doctors_appointment.ui.mainHome.BottomBar
import com.example.doctors_appointment.ui.mainHome.BottomNavigationItem
import com.example.doctors_appointment.ui.mainHome.MainHome
import com.example.doctors_appointment.ui.mainHome.fontInria
import com.example.doctors_appointment.ui.mainHome.generateStars
import com.example.doctors_appointment.ui.theme.Indigo50
import com.example.doctors_appointment.ui.theme.Indigo500
import com.example.doctors_appointment.ui.theme.Indigo900


@Composable
fun AppointmentPage(navController: NavController){

    val appointments = appointments_data
    val doctor = doctors_data[0]

    val items = listOf(

        BottomNavigationItem(
            title = "Upcoming",
            route = "upcoming appointments",
            selectedIcon = Icons.Filled.Upcoming,
            unselectedIcon = Icons.Outlined.Upcoming,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Past",
            route = "past appointments",
            selectedIcon = Icons.Filled.Done,
            unselectedIcon = Icons.Outlined.Done,
            hasNews = false
        )
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
                .background(Indigo50)
                .padding(15.dp)
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 0.dp, start = 40.dp, end = 50.dp)
                .clip(RoundedCornerShape(30))
        ) {
            items.fastForEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex ,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(text = item.title)
                    },
                    icon = {
                        Icon(
                            imageVector = if(index == selectedTabIndex)
                                item.selectedIcon else item.unselectedIcon,
                            contentDescription = null
                        )
                    }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 65.dp)
        ){
            items(doctors_data.size){
                AppointmentRow(
                    appointments_data[it], doctor,
                    Modifier
                        .padding(10.dp)
                        .scale(1.1f)
                )
            }
        }
    }
}

@Composable
fun AppointmentRow(
    appointment: Appointment,
    doctor: Doctor,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(10))
            .border(2.dp, Indigo500, RoundedCornerShape(10))
            .background(Color.White)

    ){

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 12.dp, end = 12.dp, bottom = 10.dp)
        ) {

            Text(
                text = appointment.appointmentDate.toString(),
                fontSize = 13.sp,
                fontFamily = fontInria,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Text(
                text = "\nDoctor:",
                fontSize = 12.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = doctor.name,
                fontSize = 10.sp,
                fontFamily = fontInria,
                color = Indigo900
            )
            Text(
                text = "Status:",
                fontSize = 12.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = appointment.status,
                fontSize = 8.sp,
                fontFamily = fontInria,
                color = Indigo900
            )
            Text(
                text = "Location:",
                fontSize = 12.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = doctor.address,
                fontSize = 8.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

        }
    }

}
