package com.example.doctors_appointment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doctors_appointment.data.model.Screen
import com.example.doctors_appointment.ui.AppointmentPage
import com.example.doctors_appointment.ui.DoctorsPage
import com.example.doctors_appointment.ui.mainHome.MainHome
import com.example.doctors_appointment.ui.ProfilePage
import com.example.doctors_appointment.ui.homePage
import com.example.doctors_appointment.ui.mainHome.NavBar
import com.example.doctors_appointment.ui.signIn
import com.example.doctors_appointment.ui.signUp
import com.example.doctors_appointment.ui.mainHome.NavigationViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigationViewModel = viewModel<NavigationViewModel>()
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.home.route
            ){
                composable(Screen.home.route){
                    homePage(navController = navController)
                }

                composable(Screen.signIn.route){
                    signIn(navController = navController)
                }

                composable(Screen.signUp.route){
                    signUp(navController = navController)
                }

                composable(Screen.mainHome.route){
                    NavBar()
                }

//                composable(Screen.doctors.route){
//                    DoctorsPage(navController = navController, navigationViewModel = navigationViewModel)
//                }
//
//                composable(Screen.appointment.route){
//                    AppointmentPage(navController = navController)
//                }
//
//                composable(Screen.profile.route){
//                    ProfilePage(navController = navController)
//                }
            }
        }
    }
}