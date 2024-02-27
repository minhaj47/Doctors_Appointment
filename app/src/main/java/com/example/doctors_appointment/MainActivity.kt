package com.example.doctors_appointment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doctors_appointment.util.Screen
import com.example.doctors_appointment.data.repository.MongoRepository
import com.example.doctors_appointment.ui.BottomBar
import com.example.doctors_appointment.ui.HomePage
import com.example.doctors_appointment.ui.NavBar
import com.example.doctors_appointment.ui.mainHome.MainHome
import com.example.doctors_appointment.ui.SignIn
import com.example.doctors_appointment.ui.SignUp
import com.example.doctors_appointment.util.UiEvent
import com.example.doctors_appointment.ui.viewmodel.SignInViewModel
import com.example.doctors_appointment.ui.viewmodel.MainHomeViewModel
import com.example.doctors_appointment.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var repository: MongoRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val mainHomeViewModel: MainHomeViewModel = hiltViewModel()
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

                composable(Screen.signIn.route){
                    SignIn(navController = navController, signInViewModel = signInViewModel)
                }

                composable(Screen.signUp.route){
                    SignUp(navController = navController, signUpViewModel = signUpViewModel)
                }

                composable(route = Screen.mainHome.route){
                   NavBar(repository = repository)
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