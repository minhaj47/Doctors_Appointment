package com.example.doctors_appointment.ui.patientsUI

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doctors_appointment.data.repository.MongoRepoImplementation
import com.example.doctors_appointment.data.repository.MongoRepository
import com.example.doctors_appointment.ui.patientsUI.mainHome.MainHome
import com.example.doctors_appointment.ui.patientsUI.viewmodels.BookingViewModel
import com.example.doctors_appointment.ui.patientsUI.viewmodels.MainHomeViewModel
import com.example.doctors_appointment.ui.theme.Indigo50
import com.example.doctors_appointment.ui.theme.Indigo900
import com.example.doctors_appointment.util.Screen

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavBar() {

    val repository: MongoRepository = MongoRepoImplementation

    // viewModel Initialization

    //val othersViewModel = OthersViewModel(repository)
    val bookingViewModel = BookingViewModel(repository)
    val mainHomeViewModel = MainHomeViewModel(repository)



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

    val navController = rememberNavController()

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
                MainHome(navController = navController, mainHomeViewModel = mainHomeViewModel)
            }

//            composable(Screen.doctors.route){
//                DoctorsPage(navController = navController, othersViewModel = othersViewModel)
//            }
//
//            composable(Screen.appointment.route){
//                AppointmentPage(othersViewModel = othersViewModel)
//            }
//
//            composable(Screen.profile.route){
//                ProfilePage(navController = navController, othersViewModel = othersViewModel)
//            }
//
//            composable(
//                route = Screen.doctorsDetails.route + "/{doctorId}",
//                arguments = listOf(
//                    navArgument("doctorId"){
//                        type = NavType.StringType
//                        defaultValue = ""
//                        nullable = true
//                    }
//                )
//                ){ entry ->
//                entry.arguments?.getString("doctorId")?.let {
//                    it1 -> DoctorsDetailsPage(navController = navController, doctorId = it1, othersViewModel)
//                }
//            }
//
//            composable(
//                route = Screen.catagoryDoctors.route + "/{category}",
//                arguments = listOf(
//                    navArgument("category"){
//                        type = NavType.StringType
//                        defaultValue = "Heart"
//                        nullable = true
//                    }
//                )
//            ){entry ->
//                 CatagoryDoctorsPage(navController = navController, category =  entry.arguments?.getString("category"), othersViewModel)
//            }
//
//            composable(
//                route = Screen.booking1.route + "/{doctorId}",
//                arguments = listOf(
//                    navArgument("doctorId"){
//                        type = NavType.StringType
//                        defaultValue = "Doctor1"
//                        nullable = true
//                    }
//                )
//            ){
//                BookSchedule(
//                    navController = navController,
//                    doctorId = it.arguments?.getString("doctorId"),
//                    bookingViewModel = bookingViewModel
//                )
//            }
//
//            composable(
//                route = Screen.finalBooking.route
////                        + "/{appointmentId}",
////                arguments = listOf(
////                    navArgument("appointmentId"){
////                        type = NavType.StringType
////                        defaultValue = "appointment1"
////                        nullable = false
////                    }
////                )
//            ){
//                FinalBooking(navController = navController, bookingViewModel = bookingViewModel)
//            }
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
