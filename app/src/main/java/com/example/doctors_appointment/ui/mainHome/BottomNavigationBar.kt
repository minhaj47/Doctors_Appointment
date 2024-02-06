package com.example.doctors_appointment.ui.mainHome

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarViewMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.CalendarViewMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.doctors_appointment.data.model.Screen
import com.example.doctors_appointment.ui.AppointmentPage
import com.example.doctors_appointment.ui.CatagoryDoctorsPage
import com.example.doctors_appointment.ui.DoctorsDetailsPage
import com.example.doctors_appointment.ui.DoctorsPage
import com.example.doctors_appointment.ui.ProfilePage
import com.example.doctors_appointment.ui.booking.BookSchedule
import com.example.doctors_appointment.ui.booking.FinalBooking
import com.example.doctors_appointment.ui.theme.Indigo50
import com.example.doctors_appointment.ui.theme.Indigo900

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    //navController: NavController,          // we will use an independent navcontroller for this bar navigation for simplicity
) {
    val items = listOf(

        BottomNavigationItem(
            title = "Home",
            route = "mainHome",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Doctors",
            route = "doctors",
            selectedIcon = Icons.Filled.People,
            unselectedIcon = Icons.Outlined.People,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Appoint..",
            selectedIcon = Icons.Filled.CalendarViewMonth,
            unselectedIcon = Icons.Outlined.CalendarViewMonth,
            hasNews = true,
            route = "appointment"
        ),
        BottomNavigationItem(
            title = "Profile",
            route = "profile",
            selectedIcon = Icons.Filled.Man,
            unselectedIcon = Icons.Outlined.Man,
            hasNews = true
        )
    )

    var navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController, items)
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = Screen.mainHome.route
        ){
            composable(Screen.mainHome.route){
                MainHome(navController = navController)
            }

            composable(Screen.doctors.route){
                DoctorsPage(navController = navController)
            }

            composable(Screen.appointment.route){
                AppointmentPage(navController = navController)
            }

            composable(Screen.profile.route){
                ProfilePage(navController = navController)
            }

            composable(Screen.doctorsDetails.route){
                DoctorsDetailsPage(navController = navController)
            }

            composable(
                route = Screen.catagoryDoctors.route + "/{category}",
                arguments = listOf(
                    navArgument("category"){
                        type = NavType.StringType
                        defaultValue = "Heart"
                        nullable = true
                    }
                )
            ){entry ->
                 CatagoryDoctorsPage(navController = navController, category =  entry.arguments?.getString("category"))
            }

            composable(
                route = Screen.booking1.route + "/{doctorId}",
                arguments = listOf(
                    navArgument("doctorId"){
                        type = NavType.StringType
                        defaultValue = "Doctor1"
                        nullable = true
                    }
                )
            ){
                BookSchedule(navController = navController, doctorId = it.arguments?.getString("doctorId"))
            }

            composable(
                route = Screen.finalBooking.route + "/{doctorId}/{slotNo}",
                arguments = listOf(
                    navArgument("doctorId"){
                        type = NavType.StringType
                        defaultValue = "Doctor1"
                        nullable = true
                    },
                    navArgument("slotNo"){
                        type = NavType.StringType
                        defaultValue = "0"
                        nullable = true
                    }
                )
            ){
                FinalBooking(navController = navController, doctorId = it.arguments?.getString("doctorId"), slotNo = it.arguments?.getString("slotNo")!!.toInt())
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavController, items: List<BottomNavigationItem>) {

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar(
        modifier = Modifier
            .clip(RoundedCornerShape(22)),
        containerColor = Indigo50,
        contentColor = Indigo900,
        tonalElevation = 5.dp,

    ){
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = false,
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}
