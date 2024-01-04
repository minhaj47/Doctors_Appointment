package com.example.doctors_appointment.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.doctors_appointment.R
import com.example.doctors_appointment.Screen
import com.example.doctors_appointment.ui.theme.Indigo100
import com.example.doctors_appointment.ui.theme.Indigo400
import com.example.doctors_appointment.ui.theme.Indigo900

@Composable
fun signIn(navController: NavController){

    var fontInria = FontFamily(
        Font(R.font.inriasans_regular, FontWeight.Normal),
        Font(R.font.inriasans_bold, FontWeight.Bold),
        Font(R.font.inriasans_light, FontWeight.Light)
    )
    var fontActor = FontFamily(
        Font(R.font.actor)
    )


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Welcome to Doctor's Appointment",
            textAlign = TextAlign.Center,
            fontFamily = fontInria,
            fontSize = 28.sp,
            fontWeight = FontWeight.Light

        )

        Spacer(
            modifier = Modifier.height(70.dp)
        )

        Text(
            text = "Sign in to continue",
            fontFamily = fontInria,
            fontSize = 13.sp,
            modifier = Modifier.alpha(0.5f),
            fontWeight = FontWeight.Light,
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )

        var filledMail by remember{
            mutableStateOf("")
        }
        OutlinedTextField(
            value = filledMail ,
            onValueChange = {
                filledMail = it
            },
            label = {
                    Text(text = "Enter Email address: ")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Email" )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "user" )
            },
//            textStyle = LocalTextStyle.current.copy(
//                textAlign = TextAlign.Center
//            ),
            suffix = {
                     Text(text = "@gmail.com")
            },
//            supportingText = {
//                Text(text = "*required")
//            },
//            isError = true
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )


        var filledPass by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = filledPass,
            onValueChange = {
                filledPass = it
            },
            label = {
                Text(text = "Enter your Password: ")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = "Pass" )
            },
//            textStyle = LocalTextStyle.current.copy(
//                textAlign = TextAlign.Center
//            )
        )
        Spacer(
            modifier = Modifier.height(60.dp)
        )

        Button(
            onClick = {
                navController.navigate(Screen.mainHome.route)
            },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp) ,
            colors = ButtonDefaults.buttonColors(
                containerColor = Indigo100,
                contentColor = Indigo900
            )
            ) {
            Text(
                text = "Log in",
                fontSize = 24.sp,
                //fontWeight = FontWeight.Bold,
                fontFamily = fontActor
                )
        }

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        Text(
            text = "Don't Have an Account?",
            fontFamily = fontInria,
            fontSize = 12.sp,
            modifier = Modifier.alpha(0.5f),
            fontWeight = FontWeight.Light,
        )
        TextButton(
            onClick = {
                      navController.navigate(Screen.signUp.route)
                      },
            modifier = Modifier
                .width(80.dp)
                .height(40.dp)

            ) {
            Text(text = "Sign Up",
                fontFamily = fontInria,
                fontSize = 12.sp,
                modifier = Modifier.alpha(0.5f),
                fontWeight = FontWeight.Light,
            )
        }
    }
}