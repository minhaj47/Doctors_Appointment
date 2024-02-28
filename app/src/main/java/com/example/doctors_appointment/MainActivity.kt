package com.example.doctors_appointment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.doctors_appointment.util.Screen
import com.example.doctors_appointment.ui.CheckUser
import com.example.doctors_appointment.ui.HomePage
import com.example.doctors_appointment.ui.SignIn
import com.example.doctors_appointment.ui.SignUp
import com.example.doctors_appointment.ui.SignInViewModel
import com.example.doctors_appointment.ui.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val signInViewModel: SignInViewModel = hiltViewModel()
            val signUpViewModel: SignUpViewModel = hiltViewModel()
            val navController = rememberNavController()


            NavHost(
                navController = navController,
                startDestination = Screen.home.route
            ){
                composable(Screen.home.route){
                    HomePage(navController = navController)
                }

                composable(Screen.signIn.route) {
                    SignIn(navController = navController, signInViewModel = signInViewModel)
                }

                composable(Screen.signUp.route) {
                    SignUp(navController = navController, signUpViewModel = signUpViewModel)
                }

                composable(
                    route = Screen.doctorsDetails.route + "/{email}" + "/{password}",
                    arguments = listOf(
                        navArgument("email") {
                            type = NavType.StringType
                            defaultValue = ""
                            nullable = true
                        },
                        navArgument("password") {
                            type = NavType.StringType
                            defaultValue = ""
                            nullable = true
                        }
                    )
                ) { entry ->
                    entry.arguments?.getString("email")
                        ?.let {
                            CheckUser(
                                email = it,
                                password = entry.arguments?.getString("password")!!
                            )
                        }
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