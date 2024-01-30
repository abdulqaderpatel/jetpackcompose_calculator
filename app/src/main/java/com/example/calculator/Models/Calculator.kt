package com.example.calculator.Models

import androidx.compose.ui.graphics.Color


data class Calculator(
    val operation: String,
    val onClick: ()-> Unit,
    val color: Color,
    )
