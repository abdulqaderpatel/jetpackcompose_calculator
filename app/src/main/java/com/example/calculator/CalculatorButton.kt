package com.example.calculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.Models.Calculator

@Preview(showBackground = true)
@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    calculator: Calculator = Calculator(operation = "A", onClick ={} , color = Color(0xff282C34))
) {

    Box(
        modifier = Modifier
            .size(95.dp)
            .background(color = calculator.color, shape = CircleShape)
            .clickable {
                calculator.onClick()
                Log.d("checking time", "CalculatorButton: ")
            }, contentAlignment = Alignment.Center

    ) {
        Text(
            text = calculator.operation,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.W600
        )
    }
}