package com.example.charitychainapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charitychainapp.R
import com.example.charitychainapp.ui.theme.CharityChainTheme
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerRegistrationStep1(
    onBackClick: () -> Unit = {},
    onContinueClick: (VolunteerFormData) -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var whatsapp by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("AB+") }
    var street by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }

    val genderOptions = listOf("Male", "Female", "Other")
    val bloodOptions = listOf("A+","A-","B+","B-","O+","O-","AB+","AB-")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            title = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Volunteer Registration", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Step 1 of 2", color = Color.Gray, fontSize = 14.sp)
                }
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Personal Information
            Text("Personal Information", fontWeight = FontWeight.Bold, color = Color.Black)

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_person), contentDescription = null) },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = whatsapp,
                onValueChange = { whatsapp = it },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_phone), contentDescription = null) },
                label = { Text("WhatsApp Number") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_email), contentDescription = null) },
                label = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Gender
            var genderExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = genderExpanded, onExpandedChange = { genderExpanded = it }) {
                OutlinedTextField(
                    value = if (gender.isEmpty()) "Select Gender" else gender,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(expanded = genderExpanded, onDismissRequest = { genderExpanded = false }) {
                    genderOptions.forEach { option ->
                        DropdownMenuItem(text = { Text(option) }, onClick = { gender = option; genderExpanded = false })
                    }
                }
            }

            // Date of Birth
            OutlinedTextField(
                value = dob,
                onValueChange = {},
                label = { Text("Date of Birth") },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // simple inline date value (for mock); hook to a proper date picker if needed
                        dob = LocalDate.now().toString()
                    },
                readOnly = true,
                shape = RoundedCornerShape(12.dp)
            )

            // Blood Group
            var bloodExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = bloodExpanded, onExpandedChange = { bloodExpanded = it }) {
                OutlinedTextField(
                    value = bloodGroup,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = bloodExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(expanded = bloodExpanded, onDismissRequest = { bloodExpanded = false }) {
                    bloodOptions.forEach { option ->
                        DropdownMenuItem(text = { Text(option) }, onClick = { bloodGroup = option; bloodExpanded = false })
                    }
                }
            }

            Text("Address Details", fontWeight = FontWeight.Bold, color = Color.Black)

            OutlinedTextField(
                value = street,
                onValueChange = { street = it },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_location), contentDescription = null) },
                label = { Text("Street Address") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_location), contentDescription = null) },
                label = { Text("Town/City") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    onContinueClick(
                        VolunteerFormData(
                            fullName, whatsapp, email, gender, dob, bloodGroup, street, city
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D32FF)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("CONTINUE", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

data class VolunteerFormData(
    val fullName: String,
    val whatsapp: String,
    val email: String,
    val gender: String,
    val dob: String,
    val bloodGroup: String,
    val street: String,
    val city: String
)

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun VolunteerRegistrationStep1Preview() {
    CharityChainTheme {
        VolunteerRegistrationStep1()
    }
}


