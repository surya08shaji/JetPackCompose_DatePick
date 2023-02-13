package com.example.jtpcomposedatepick

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import com.example.jtpcomposedatepick.ui.theme.JtpComposeDatePickTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JtpComposeDatePickTheme {

                var pickedDate by remember {
                    mutableStateOf(LocalDate.now())
                }

                val formattedDate by remember {
                    derivedStateOf {
                        DateTimeFormatter
                            .ofPattern("MMM dd yyyy")
                            .format(pickedDate)
                    }
                }

                val dateDialogState = rememberMaterialDialogState()

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        dateDialogState.show()
                    }) {
                        Text(text = "Pick date")
                    }
                    Text(text = formattedDate)
                }
                MaterialDialog(
                    dialogState = dateDialogState,
                    buttons = {
                        positiveButton(text = "Ok") {
                            Toast.makeText(
                                applicationContext,
                                "Clicked ok",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        negativeButton(text = "Cancel")
                    }
                ) {
                    datepicker(
                        initialDate = LocalDate.now(),
                        title = "Pick a date",
                        colors = DatePickerDefaults.colors(
                            headerBackgroundColor = Color.Gray,
                            headerTextColor = Color.White,
                            calendarHeaderTextColor = Color.DarkGray,
                            dateActiveBackgroundColor = Color.DarkGray,
                            dateInactiveBackgroundColor = Color.White,
                            dateActiveTextColor = Color.White,
                            dateInactiveTextColor = Color.Gray,
                        ),
                        allowedDateValidator = {
                            it.dayOfMonth % 2 == 1
                        }
                    ) {
                        pickedDate = it
                    }
                    
                }
            }
        }
    }
}

@Composable

fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JtpComposeDatePickTheme {
        Greeting("Android")
    }
}