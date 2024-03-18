package com.example.doctors_appointment.ui.patientsUI.booking

import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.doctors_appointment.data.model.Doctor
import com.example.doctors_appointment.ui.patientsUI.mainHome.fontInria
import com.example.doctors_appointment.ui.patientsUI.viewmodels.BookingViewModel
import com.example.doctors_appointment.ui.theme.Indigo400
import com.example.doctors_appointment.ui.theme.Indigo50
import com.example.doctors_appointment.ui.theme.Indigo900
import com.example.doctors_appointment.util.Screen
import java.util.Calendar
import java.util.Date

@Composable
fun BookSchedule(
    doctorId: String?,
    navController: NavController,
    bookingViewModel: BookingViewModel
) {

    if (doctorId != null) {
        bookingViewModel.getDoctorFromId(doctorId)
    }

    val doctor = bookingViewModel.doctor1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Indigo50)
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))


        Text(
            text = "SCHEDULE APPOINTMENT",
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = fontInria,
            color = Indigo900,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(40.dp))



        val selectedDate = showDatePick(context = LocalContext.current)
        val currentDate = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

        val selectedDateAdjusted = Calendar.getInstance().apply {
            time = selectedDate
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

//        val diffInMillis = selectedDateAdjusted.time - currentDate
//        val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis).toInt()
//        val selectedTabIndex = diffInDays.coerceAtLeast(0)
        val selectedTabIndex = 3



        var selectedSlot by remember {
            mutableIntStateOf(-1)
        }

        Text(
            text = "Morning Slots:",
            fontFamily = fontInria,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Indigo900
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (i in 0..2) {
                Slot(12 * (selectedTabIndex) + i, doctor, selectedSlot, bookingViewModel) {
                    selectedSlot = it
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            for (i in 3..5) {
                Slot(12 * (selectedTabIndex) + i, doctor, selectedSlot, bookingViewModel) {
                    selectedSlot = it
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Evening Slots:",
            fontFamily = fontInria,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Indigo900
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (i in 6..8) {
                Slot(12 * (selectedTabIndex) + i, doctor, selectedSlot, bookingViewModel) {
                    selectedSlot = it
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (i in 9..11) {
                Slot(12 * (selectedTabIndex) + i, doctor, selectedSlot, bookingViewModel) {
                    selectedSlot = it
                }
            }
        }
        Spacer(modifier = Modifier.height(35.dp))

        OutlinedButton(
            onClick = {
                if (selectedSlot != -1) {
                    bookingViewModel.setDateTime(selectedSlot)
                    navController.navigate(Screen.finalBooking.route)
                }

            }
        ) {
            Text(
                text = "Continue",
                fontSize = 25.sp,
                fontFamily = fontInria,
            )
        }
    }
}


@Composable
fun showDatePick(context: Context): Date {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val selectedDate = remember { mutableStateOf(Date()) }
    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val selectedCalendar = Calendar.getInstance()
            selectedCalendar.set(year, month, dayOfMonth)
            selectedDate.value = selectedCalendar.time
        }, year, month, day
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Selected Date: ${selectedDate.value}")
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { datePickerDialog.show() }) {
            Text(text = "Open Date Picker")
        }
    }

    return selectedDate.value
}

@Composable
fun Slot(
    slotNo: Int,
    doctor: Doctor,
    selectedSlot: Int,
    bookingViewModel: BookingViewModel,
    onSlotSelect: (Int) -> Unit     // Callback to notify parent when a slot is selected
) {
    Button(
        onClick = {
            if (doctor.availabilityStatus[slotNo]) {
                onSlotSelect(slotNo) // Notify parent about the selection
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (slotNo == selectedSlot) Indigo400 else Color.White,
            contentColor = if (doctor.availabilityStatus[slotNo]) {
                if (selectedSlot == slotNo) Color.White else Indigo900
            } else Color.LightGray,
        )
    ) {
        Text(
            text = bookingViewModel.getTime(slotNo%12).toString()
        )
    }
}
