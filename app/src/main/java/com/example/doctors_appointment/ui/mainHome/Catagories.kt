package com.example.doctors_appointment.ui.mainHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctors_appointment.R
import com.example.doctors_appointment.ui.theme.Indigo50

data class Category(
    val name: String,
    //val doctors: Array<Doctor>,
    val icon: Painter
)

@Composable
fun CatagoryRow(){

    val categories = listOf(
        Category(
            name = "Heart",
            icon = painterResource(id = R.drawable.heart_disease)
        ),
        Category(
            name = "Eye",
            icon = painterResource(id = R.drawable.eye)
        ),
        Category(
            name = "Kidney",
            icon = painterResource(id = R.drawable.kidney)
        ),
        Category(
            name = "Neurology",
            icon = painterResource(id = R.drawable.neuro)
        ),
        Category(
            name = "Lung",
            icon = painterResource(id = R.drawable.lungs)
        ),
        Category(
            name = "Dental",
            icon = painterResource(id = R.drawable.dental)
        ),
        Category(
            name = "Mental Health",
            icon = painterResource(id = R.drawable.mental_health)
        ),
        Category(
            name = "Skin",
            icon = painterResource(id = R.drawable.skin_disease)
        ),
        Category(
            name = "Nose, Ear and Throat",
            icon = painterResource(id = R.drawable.nose_ear)
        ),
        Category(
            name = "Cancer Disease",
            icon = painterResource(id = R.drawable.cancer)
        )
    )

    Text(
        text = " Category",
        fontFamily = fontInria,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.labelLarge
    )

    LazyRow{
        items(categories.size){
            CatagoryCard(
                categories[it]
            )
        }
    }
}

@Composable
fun CatagoryCard(
    category: Category
) {

    Column(
         modifier = Modifier
             .padding(top = 7.dp, bottom = 15.dp, end = 15.dp)
             .clip(RoundedCornerShape(10))
             .background(Indigo50)
             .padding(15.dp),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
    ) {
         Image(
             modifier = Modifier.height(45.dp),
             painter = category.icon,
             contentDescription = "disease category"
         )
         Text(
             text = category.name,
             fontFamily = fontActor,
             textAlign = TextAlign.Center,
             fontSize = 12.sp
         )
    }
}
