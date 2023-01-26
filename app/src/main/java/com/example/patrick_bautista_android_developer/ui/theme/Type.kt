package com.example.patrick_bautista_android_developer.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.patrick_bautista_android_developer.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val TodoTypography = androidx.compose.material.Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 20.sp,
        color = Color.Black
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 12.sp,
        color = Color(0xFFB1B1B1)
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 16.sp,
        color = Color(0xFF6C6C6C)
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.inter_regular)),
        fontSize = 16.sp,
        color = Color.Black
    )
)