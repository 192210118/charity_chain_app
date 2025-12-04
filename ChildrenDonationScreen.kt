package com.example.charitychainapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charitychainapp.R
import com.example.charitychainapp.ui.theme.CharityChainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildrenDonationScreen(
    onBackClick: () -> Unit = {},
    onDonateClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Support Children",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )
        
        // Subtitle
        Text(
            text = "Make a difference in a child's life",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        // Scrollable Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            // Children Donation Items
            item {
                ChildrenDonationItem(
                    title = "Donate Sweater",
                    price = "Starting from ₹500",
                    imageRes = R.drawable.ic_sweater,
                    onDonateClick = { onDonateClick("Donate Sweater") }
                )
            }
            
            item {
                ChildrenDonationItem(
                    title = "Donate Slippers",
                    price = "Starting from ₹150",
                    imageRes = R.drawable.ic_slippers,
                    onDonateClick = { onDonateClick("Donate Slippers") }
                )
            }
            
            item {
                ChildrenDonationItem(
                    title = "School Bag",
                    price = "Starting from ₹500",
                    imageRes = R.drawable.ic_school_bag,
                    onDonateClick = { onDonateClick("School Bag") }
                )
            }
            
            item {
                ChildrenDonationItem(
                    title = "Board Games",
                    price = "Starting from ₹500-2000",
                    imageRes = R.drawable.ic_board_games,
                    onDonateClick = { onDonateClick("Board Games") }
                )
            }
            
            item {
                ChildrenDonationItem(
                    title = "Annual Health Checkup",
                    price = "Starting from ₹1500",
                    imageRes = R.drawable.ic_health_checkup,
                    onDonateClick = { onDonateClick("Annual Health Checkup") }
                )
            }
            
            item {
                ChildrenDonationItem(
                    title = "Full Day Meals",
                    price = "Starting from ₹165",
                    imageRes = R.drawable.ic_full_day_meals,
                    onDonateClick = { onDonateClick("Full Day Meals") }
                )
            }
            
            // Impact Statistics Section
            item {
                ChildrenImpactSection()
            }
        }
    }
}

@Composable
fun ChildrenDonationItem(
    title: String,
    price: String,
    imageRes: Int,
    onDonateClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier.size(64.dp),
                    tint = Color(0xFFE91E63) // Pink color for children
                )
            }
            
            // Content
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    
                    Text(
                        text = price,
                        fontSize = 16.sp,
                        color = Color(0xFFE91E63), // Pink color for children
                        fontWeight = FontWeight.Medium
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Donate Button
                Button(
                    onClick = onDonateClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE91E63) // Pink color for children
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "DONATE NOW",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ChildrenImpactSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Make a Difference",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Children Supported
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "1000+",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE91E63) // Pink color for children
                    )
                    Text(
                        text = "Children Supported",
                        fontSize = 14.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Schools Reached
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "50+",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE91E63) // Pink color for children
                    )
                    Text(
                        text = "Schools Reached",
                        fontSize = 14.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun ChildrenDonationScreenPreview() {
    CharityChainTheme {
        ChildrenDonationScreen()
    }
}
