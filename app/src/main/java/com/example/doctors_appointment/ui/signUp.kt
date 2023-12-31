package com.example.doctors_appointment.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.doctors_appointment.R
import com.example.doctors_appointment.ui.theme.Indigo100
import com.example.doctors_appointment.ui.theme.Indigo900

@Composable
fun signUp(navController: NavController){

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
            text = "Create Account",
            textAlign = TextAlign.Center,
            fontFamily = fontInria,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold

        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Text(
            text = "Already have an Account?",
            fontFamily = fontInria,
            fontSize = 12.sp,
            modifier = Modifier.alpha(0.5f),
            fontWeight = FontWeight.Light,
        )
        TextButton(
            onClick = {
                      navController.popBackStack()
                      },
            modifier = Modifier
                .width(80.dp)
                .height(40.dp)

        ) {
            Text(text = "Sign in",
                fontFamily = fontInria,
                fontSize = 12.sp,
                modifier = Modifier.alpha(0.5f),
                fontWeight = FontWeight.Light,
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // textfield for name

        var filledName by remember{
            mutableStateOf("")
        }
        OutlinedTextField(
            value = filledName ,
            onValueChange = {
                filledName = it
            },
            label = {
                Text(text = "Enter your name:")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "name" )
            }
        )

//        Spacer(
//            modifier = Modifier.height(25.dp)
//        )

        // textfield for mail

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
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Outlined.Person,
//                    contentDescription = "user" )
//            },
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

//        Spacer(
//            modifier = Modifier.height(25.dp)
//        )


        var filledPass by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = filledPass,
            onValueChange = {
                filledPass = it
            },
            label = {
                Text(text = "Enter Password: ")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = "Pass" )
            },
        )

//        Spacer(
//            modifier = Modifier.height(60.dp)
//        )

        var filledPass1 by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = filledPass1,
            onValueChange = {
                filledPass1 = it
            },
            label = {
                Text(text = "Confirm Password:")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = "Pass" )
            }
        )
        Spacer(
            modifier = Modifier.height(30.dp)
        )


        Button(
            onClick = {
//                navController.popBackStack()
            },
            modifier = Modifier
                .height(50.dp)
                .width(210.dp) ,
            colors = ButtonDefaults.buttonColors(
                containerColor = Indigo100,
                contentColor = Indigo900
            )
        ) {
            Text(
                text = "As a Patient",
                fontSize = 24.sp,
                //fontWeight = FontWeight.Bold,
                fontFamily = fontActor
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = {
//                navController.popBackStack()
            },
            modifier = Modifier
                .height(50.dp)
                .width(210.dp) ,
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Indigo100,
//                contentColor = Indigo900
//            )
        ) {
            Text(
                text = "As a Doctor",
                fontSize = 24.sp,
                //fontWeight = FontWeight.Bold,
                fontFamily = fontActor
            )
        }

    }
}