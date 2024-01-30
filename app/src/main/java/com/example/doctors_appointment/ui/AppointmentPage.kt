package com.example.doctors_appointment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentPage(navController: NavController){
    Column {
        TopAppBar(
            title = { Text("Animated LazyColumn Element") },
            modifier = Modifier.fillMaxWidth()
        )
        ElementList(Modifier.weight(1f))
    }
}
@Composable
fun ElementList(modifier: Modifier = Modifier) {
    // State to track the size reduction animation
    var reducedSize by remember { mutableStateOf(0.dp) }

    // LazyColumn state
    val lazyListState = rememberLazyListState()

    // Elements to display in LazyColumn
    val elements = (1..50).toList()

    LazyColumn(
        state = lazyListState,
        modifier = modifier
    ) {
        items(elements.size) { elements ->
            // Animated size reduction
            val animatedSize by animateDpAsState(targetValue = reducedSize)

            // Wrap the item content with an AnimatedVisibility to hide/show based on size
            AnimatedVisibility(
                visible = animatedSize != 0.dp,
                enter = fadeIn() + expandVertically(),
                exit = shrinkVertically() + fadeOut()
            ) {
                // Content of each element
                ElementItem(
                    text = "Element $elements[it]",
                    onSizeChange = { newSize ->
                        // Update the reducedSize when the size changes
                        reducedSize = newSize
                    }
                )
            }
        }
    }
}


@Composable
fun ElementItem(text: String, onSizeChange: (Dp) -> Unit) {
    var textSize by remember { mutableStateOf(16.sp) }

    // Measure the height of the item content
    val density = LocalDensity.current.density
    val itemHeight = rememberUpdatedState(density * 50) // Adjust as needed

    // Update the size when the item is laid out
    LaunchedEffect(itemHeight.value) {
        onSizeChange(itemHeight.value.dp)
    }

    // Content of each element item
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray)
            .clip(MaterialTheme.shapes.medium)
            .height(textSize.value.dp)
            .clickable {
                // Change the text size on click
                textSize = if (textSize == 16.sp) 24.sp else 16.sp
            }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            fontSize = 16.sp,
            color = Color.White
        )
    }
}
