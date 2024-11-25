package com.example.taniapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 14.sp,
    color: Color = Color.Black
) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color
    )
}