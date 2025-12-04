package com.example.charitychainapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charitychainapp.R
import com.example.charitychainapp.ui.theme.CharityChainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceProviderDashboard(
    name: String = "swapna",
    email: String = "kamepalliswapna62@gmail.com",
    phone: String = "+9515599267",
    rating: Double = 4.8,
    totalCompleted: Int = 156,
    onEditProfile: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val tab = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7FB))
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFBDBDBD))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column { Text(name, fontWeight = FontWeight.Bold, fontSize = 18.sp); Text("Service Provider", color = Color.Gray, fontSize = 12.sp) }
            }
            Icon(painter = painterResource(id = R.drawable.ic_bell), contentDescription = null, tint = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEAEAEE))
                    .padding(6.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                val selected = tab.value == 0
                Button(
                    onClick = { tab.value = 0 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selected) Color(0xFF2F7BFA) else Color.Transparent,
                        contentColor = if (selected) Color.White else Color(0xFF2F7BFA)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) { Text("Upcoming Services") }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Service cards
        ServiceItem(
            icon = R.drawable.ic_education_cap,
            title = "Education Services",
            dateTime = "May 2, 9:00 AM",
            address = "789 School Rd, Queens",
            status = "Confirmed",
            statusColor = Color(0xFF66BB6A)
        )
        Spacer(modifier = Modifier.height(12.dp))
        ServiceItem(
            icon = R.drawable.ic_heart,
            title = "Health Care Services",
            dateTime = "May 3, 2:00 PM",
            address = "321 Clinic Ave, Bronx",
            status = "Pending",
            statusColor = Color(0xFFFFB74D)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFBDBDBD))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(name, fontWeight = FontWeight.Bold)
                Text(email, color = Color.Gray, fontSize = 12.sp)
                Text(phone, color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("★", color = Color(0xFFFFC107))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("$rating/5", color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text("$totalCompleted Services Completed", color = Color(0xFF6D7580))

                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onEditProfile,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2F7BFA)),
                    shape = RoundedCornerShape(8.dp)
                ) { Text("EDIT PROFILE") }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = onLogout,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) { Text("Logout", color = Color(0xFFD84343)) }
            }
        }
    }
}

@Composable
private fun ServiceItem(
    icon: Int,
    title: String,
    dateTime: String,
    address: String,
    status: String,
    statusColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE9EDF5)),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = icon), contentDescription = null, tint = Color(0xFF2F7BFA))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.SemiBold)
                Text(dateTime, color = Color.Gray, fontSize = 12.sp)
                Text(address, color = Color.Gray, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Surface(color = statusColor.copy(alpha = 0.2f), shape = RoundedCornerShape(50)) {
                Text(status, color = statusColor, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun ServiceProviderDashboardPreview() {
    CharityChainTheme { ServiceProviderDashboard() }
}


