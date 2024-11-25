package com.example.taniapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Task() {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFF55AA68),
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .width(250.dp)
            .height(100.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomText(
                    "DONE",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF55AA68)
                )

                CustomText(
                    "Project",
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }

            HeadingText("Produktivitas Petani")

            CustomText("November 26, 2024")
        }
    }
}