package com.example.doctors_appointment.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.doctors_appointment.MyApp
import com.example.doctors_appointment.R
import com.example.doctors_appointment.data.model.Appointment
import com.example.doctors_appointment.data.model.Patient
import com.example.doctors_appointment.ui.mainHome.RoundImage
import com.example.doctors_appointment.ui.mainHome.fontInria
import com.example.doctors_appointment.ui.theme.Indigo200
import com.example.doctors_appointment.ui.theme.Indigo50
import com.example.doctors_appointment.ui.theme.Indigo500
import com.example.doctors_appointment.ui.theme.Indigo900
import com.example.doctors_appointment.ui.viewmodel.OthersViewModel
import com.example.doctors_appointment.util.ProfileEvent
import com.example.doctors_appointment.util.Screen

@Composable
fun ProfilePage(
    navController: NavController,
    othersViewModel: OthersViewModel
) {

    val patient = MyApp.patient

    var onEdit by remember {
        mutableStateOf(false)
    }

    var newPatient: Patient = patient

    Column(
        modifier = Modifier
            .background(Indigo50)
            .padding(10.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Profile",
                fontSize = 20.sp,
                fontFamily = fontInria,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(.9f)
                    .padding(start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Indigo200)
                    .padding(5.dp)
            )
            IconButton(
                modifier = Modifier
                    .weight(.2f)
                    .padding(end = 15.dp),
                onClick = {
                    onEdit = !onEdit
                }
            ) {
                Icon(
                    imageVector = if(onEdit) Icons.Filled.Edit else Icons.Outlined.Edit,
                    contentDescription = null
                )
            }
        }

        Column(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ){
            RoundImage(
                image = painterResource(id = R.drawable.man),
                modifier = Modifier.height(80.dp)
            )

            var filledName by remember {
                mutableStateOf(patient.name)
            }

            if(onEdit){
                OutlinedTextField(

                    value = filledName,
                    onValueChange = { newText ->
                        filledName = newText
                        othersViewModel.OnEvent(ProfileEvent.EditName(newText))
                    },
                    label = {
                        Text(
                            text = "Name",
                            fontFamily = fontInria
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent, // Text color
                        unfocusedContainerColor = Color.Transparent, // Background color
//                        focusedIndicatorColor = Color.Transparent, // No indicator when focused
//                        unfocusedIndicatorColor = Color.Transparent // No indicator when not focused
                    ),
                )
            }else{
                Text(
                    text = patient.name,
                    fontFamily = fontInria,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            }


        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 65.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item{

                if(onEdit){
                    EditProfile(newPatient, othersViewModel)
                    OutlinedButton(
                        onClick = {
                            othersViewModel.OnEvent(ProfileEvent.OnSave)
                            onEdit = !onEdit
                        }
                    ) {
                        Text(
                            text = "SAVE",
                            fontSize = 20.sp,
                            fontFamily = fontInria,
                        )
                    }
                }else Profile(patient)
                
                Spacer(modifier = Modifier.height(7.dp))
                
                Text(
                    text = "Medical History:",
                    fontSize = 25.sp,
                    fontFamily = fontInria,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(7.dp))

                for(item in patient.medicalHistory){
                    AppointmentView(item)
                }

            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
    patient: Patient,
    othersViewModel: OthersViewModel,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(5))
//            .border(2.dp, Indigo500, RoundedCornerShape(5))
            .background(Color.White)

    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 12.dp, end = 10.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            var filledHeight by remember {
                mutableStateOf(patient.height.toString())
            }

            OutlinedTextField(

                value = filledHeight,
                onValueChange = { newText ->
                    if (newText.isBlank() || newText.toDoubleOrNull() != null) {
                        filledHeight = newText
                        val newHeight = newText.toDoubleOrNull() ?: 0.0
                        othersViewModel.OnEvent(ProfileEvent.EditHeight(newHeight))
                    }
                },
                label = {
                    Text(
                        text = "Height",
                        fontFamily = fontInria,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // Text color
                    unfocusedContainerColor = Color.Transparent, // Background color
//                    focusedIndicatorColor = Color.Transparent, // No indicator when focused
//                    unfocusedIndicatorColor = Color.Transparent // No indicator when not focused
                ),
            )

            Spacer(modifier = Modifier.height(5.dp))

            var filledWeight by remember {
                mutableStateOf(patient.weight.toString())
            }

            OutlinedTextField(

                value = filledWeight,
                onValueChange = { newText ->
                    if (newText.isBlank() || newText.toDoubleOrNull() != null) {
                        filledWeight = newText
                        val newWeight = newText.toDoubleOrNull() ?: 0.0
                        othersViewModel.OnEvent(ProfileEvent.EditWeight(newWeight))
                    }
                },
                label = {
                    Text(
                        text = "Weight",
                        fontFamily = fontInria,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // Text color
                    unfocusedContainerColor = Color.Transparent, // Background color
//                    focusedIndicatorColor = Color.Transparent, // No indicator when focused
//                    unfocusedIndicatorColor = Color.Transparent // No indicator when not focused
                ),
            )

            Spacer(modifier = Modifier.height(5.dp))

            var filledGender by remember {
                mutableStateOf(if(patient.gender == null)"Unknown" else if(patient.gender == true) "Male" else "Female")
            }
            var isExpanded by remember {
                mutableStateOf(false)
            }

            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it }
            ) {

                OutlinedTextField(

                    value = filledGender,
                    onValueChange = {
                        filledGender = it
                        val gender = if(filledGender == "Male") true else false
                        othersViewModel.OnEvent(ProfileEvent.EditGender(gender))
                    },
                    label = {
                        Text(
                            text = "Gender",
                            fontFamily = fontInria
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent, // Text color
                        unfocusedContainerColor = Color.Transparent, // Background color
//                        focusedIndicatorColor = Color.Transparent, // No indicator when focused
//                        unfocusedIndicatorColor = Color.Transparent // No indicator when not focused
                    ),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    modifier = Modifier.menuAnchor(),
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {

                    DropdownMenuItem(
                        text = {
                            Text(text = "Male", fontFamily = fontInria) },
                        onClick = {
                            filledGender = "Male"
                            isExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Female", fontFamily = fontInria) },
                        onClick = {
                            filledGender = "Female"
                            isExpanded = false
                        }
                    )
                }
            }


            Spacer(modifier = Modifier.height(5.dp))

            var filledNumber by remember {
                mutableStateOf(patient.contactNumber)
            }
            OutlinedTextField(

                value = filledNumber,
                onValueChange = { newText ->
                    filledNumber = newText
                    othersViewModel.OnEvent(ProfileEvent.EditNumber(filledNumber))
                },
                label = {
                    Text(
                        text = "Contact Number",
                        fontFamily = fontInria
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // Text color
                    unfocusedContainerColor = Color.Transparent, // Background color
//                        focusedIndicatorColor = Color.Transparent, // No indicator when focused
//                        unfocusedIndicatorColor = Color.Transparent // No indicator when not focused
                ),
            )

            Spacer(modifier = Modifier.height(5.dp))

            var filledMail by remember {
                mutableStateOf(patient.email)
            }
            OutlinedTextField(

                value = filledMail,
                onValueChange = { newText ->
                    filledMail = newText
                    othersViewModel.OnEvent(ProfileEvent.EditEmail(filledMail))
                },
                label = {
                    Text(
                        text = "Email",
                        fontFamily = fontInria
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // Text color
                    unfocusedContainerColor = Color.Transparent, // Background color
//                        focusedIndicatorColor = Color.Transparent, // No indicator when focused
//                        unfocusedIndicatorColor = Color.Transparent // No indicator when not focused
                ),
            )

            Spacer(modifier = Modifier.height(5.dp))

            var notificationStatus by remember {
                mutableStateOf(false)
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Notification:",
                    fontSize = 10.sp,
                    fontFamily = fontInria,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(5.dp))

                Switch(
                    checked = notificationStatus,
                    onCheckedChange = {
                        notificationStatus = it
                        othersViewModel.OnEvent(ProfileEvent.EditNotificationStatus(notificationStatus))
                    },
                    thumbContent = {
                        if(notificationStatus){
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    )
                )
            }
        }
    }

}


@Composable
fun Profile(
    patient:Patient,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(5))
            .border(2.dp, Indigo500, RoundedCornerShape(5))
            .background(Color.White)

    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 12.dp, end = 10.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Height",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.height.toString(),
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Weight",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.weight.toString(),
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Gender",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if(patient.gender == null){ "Unknown" } else if(patient.gender == true){ "Male" } else "Female",
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Date of Birth:",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.dateOfBirth,
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )


            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Phone:",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.contactNumber,
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Email Address:",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.email,
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Notification:",
                fontSize = 19.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = patient.notification.toString(),
                fontSize = 17.sp,
                fontFamily = fontInria,
                color = Indigo900
            )

        }
    }
}



@Composable
fun AppointmentView(
    appointment: Appointment
){
    val prescription = appointment.prescription

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10))
            .border(2.dp, Indigo500, RoundedCornerShape(10))
            .background(Color.White)
        //.background(Indigo50)

    ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 12.dp, end = 10.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            if (prescription != null) {
                Text(
                    text = prescription.appointment?.appointmentDate?.toString() ?: "Unknown",
                    fontSize = 10.sp,
                    fontFamily = fontInria,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
            if (prescription != null) {
                Text(
                    text = "Problem: ${prescription.problem}",
                    fontSize = 15.sp,
                    fontFamily = fontInria,
                    color = Indigo900,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Diagnosis:",
                fontSize = 11.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            if (prescription != null) {
                for (item in prescription.diagnosis){
                    Text(
                        text = item,
                        fontSize = 13.sp,
                        fontFamily = fontInria,
                        color = Indigo900
                    )
                }
            }

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Medications:",
                fontSize = 11.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            if (prescription != null) {
                for (item in prescription.medications){
                    Text(
                        text = item,
                        fontSize = 12.sp,
                        fontFamily = fontInria,
                        color = Indigo900
                    )
                }
            }

            Text(
                text = "Advice:",
                fontSize = 11.sp,
                fontFamily = fontInria,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            if (prescription != null) {
                Text(
                    text = prescription.advice,
                    fontSize = 10.sp,
                    fontFamily = fontInria,
                    color = Color.Black
                )
            }


        }
    }

}