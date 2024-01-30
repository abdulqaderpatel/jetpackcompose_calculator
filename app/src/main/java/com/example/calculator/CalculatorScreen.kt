package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Preview(showBackground = true)
@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    calculatorViewModel: CalculatorViewModel = viewModel()
) {

    val calculatorState by calculatorViewModel.calculatorState.collectAsState()

    Box(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .background(Color.Black), contentAlignment = Alignment.BottomCenter
    ) {
        Column(modifier = Modifier.padding(bottom = 10.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                text = calculatorState.expression,
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (calculatorState.answer != "") {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
                    text = calculatorState.answer,
                    color = Color.Gray,
                    fontSize = 28.sp,
                    textAlign = TextAlign.End
                )
            }
            LazyVerticalGrid(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                columns = GridCells.Fixed(count = 4)
            ) {
                itemsIndexed(calculatorViewModel.calculatorData) { index, calculator ->
                    CalculatorButton(calculator = calculator)
                }
            }
        }
    }
}
