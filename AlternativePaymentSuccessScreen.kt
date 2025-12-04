package com.example.charitychainapp.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
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
fun AlternativePaymentSuccessScreen(
    donationData: DonationData,
    paymentMethod: String = "PhonePe",
    paymentId: String? = null,
    onBackClick: () -> Unit = {},
    onContinueToDelivery: () -> Unit = {},
    onViewReceipt: () -> Unit = {}
) {
    // Success animation
    val infiniteTransition = rememberInfiniteTransition(label = "success_animation")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale_animation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top App Bar
        TopAppBar(
            title = { },
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

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Success Icon and Message
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Success Icon with Animation
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .scale(scale),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color(0xFF4CAF50), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check_circle),
                            contentDescription = "Success",
                            tint = Color.White,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }

                // Success Message
                Text(
                    text = "Payment Successful!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            // Payment Details
            PaymentDetailsCard(
                donationData = donationData,
                paymentMethod = paymentMethod,
                paymentId = paymentId
            )

            // Confirmation Message
            Text(
                text = "A confirmation email has been sent",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            // Action Buttons
            ActionButtons(
                onContinueToDelivery = onContinueToDelivery,
                onViewReceipt = onViewReceipt
            )
        }
    }
}

@Composable
fun PaymentDetailsCard(
    donationData: DonationData,
    paymentMethod: String,
    paymentId: String?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PaymentDetailRow(
                label = "Payment ID",
                value = paymentId ?: "null"
            )
            
            PaymentDetailRow(
                label = "Amount",
                value = "₹${donationData.amount}"
            )
            
            PaymentDetailRow(
                label = "Donor",
                value = donationData.fullName
            )
            
            PaymentDetailRow(
                label = "Location",
                value = donationData.location
            )
            
            PaymentDetailRow(
                label = "Phone",
                value = donationData.phoneNumber
            )
            
            PaymentDetailRow(
                label = "Category",
                value = donationData.category
            )
            
            PaymentDetailRow(
                label = "Payment Method",
                value = paymentMethod
            )
        }
    }
}

@Composable
fun PaymentDetailRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            fontSize = 16.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ActionButtons(
    onContinueToDelivery: () -> Unit,
    onViewReceipt: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Primary Button - Continue to delivery page
        Button(
            onClick = onContinueToDelivery,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Continue to delivery page",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Secondary Button - View Receipt
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onViewReceipt() },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF0F0F0)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "View Receipt",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2196F3)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun AlternativePaymentSuccessScreenPreview() {
    CharityChainTheme {
        AlternativePaymentSuccessScreen(
            donationData = DonationData(
                fullName = "K.Swapna",
                location = "ongole",
                phoneNumber = "9515599267",
                category = "Health care",
                amount = 500,
                numberOfParcels = 1
            ),
            paymentMethod = "PhonePe",
            paymentId = "pay_1234567890"
        )
    }
}
