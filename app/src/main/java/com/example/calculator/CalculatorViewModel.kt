package com.example.calculator

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.calculator.Models.Calculator
import com.example.calculator.ui.theme.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.exp

class CalculatorViewModel : ViewModel() {

    private val _calculatorState = MutableStateFlow(CalculatorState())
    var calculatorState: StateFlow<CalculatorState> = _calculatorState.asStateFlow()

    val calculatorData: List<Calculator> =
        listOf(
            Calculator(
                operation = "%",
                onClick = { onNumberClicked("%") },
                color = Color(0xffA8A8AC)
            ),
            Calculator(
                operation = "Del",
                onClick = { clearExpression("Del") },
                color = Color(0xffA8A8AC)
            ),
            Calculator(
                operation = "C",
                onClick = { clearExpression("C") },
                color = Color(0xffA8A8AC)
            ),
            Calculator(
                operation = "/",
                onClick = { onOperationClicked("/") },
                color = Color(0xffF2AD39)
            ),
            Calculator(
                operation = "1",
                onClick = { onNumberClicked("1") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "2",
                onClick = { onNumberClicked("2") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "3",
                onClick = { onNumberClicked("3") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "X",
                onClick = { onOperationClicked("*") },
                color = Color(0xffF2AD39)
            ),
            Calculator(
                operation = "4",
                onClick = { onNumberClicked("4") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "5",
                onClick = { onNumberClicked("5") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "6",
                onClick = { onNumberClicked("6") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "-",
                onClick = { onOperationClicked("-") },
                color = Color(0xffF2AD39)
            ),
            Calculator(
                operation = "7",
                onClick = { onNumberClicked("7") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "8",
                onClick = { onNumberClicked("8") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "9",
                onClick = { onNumberClicked("9") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "+",
                onClick = { onOperationClicked("+") },
                color = Color(0xffF2AD39)
            ),
            Calculator(
                operation = "0",
                onClick = { onNumberClicked("0") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "Hi",
                onClick = { onOperationClicked("Hi") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = ".",
                onClick = { onNumberClicked(".") },
                color = Color(0xff282C34)
            ),
            Calculator(
                operation = "=",
                onClick = { getAnswer(answer = _calculatorState.asStateFlow().value.expression) },
                color = Color(0xffF2AD39)
            )
        )

    private fun onNumberClicked(number: String) {
        val newCalculation = calculatorState.value.expression + number

        _calculatorState.value = calculatorState.value.copy(
            expression = newCalculation,
            answer = evaluateExpression(newCalculation)
        )

    }

    private fun evaluateExpression(expression: String): String {

        val result = ExpressionBuilder(expression).build().evaluate()
        return if (result % 1.0 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }
    }


    private fun onOperationClicked(operation: String) {
        val newCalculation = calculatorState.value.expression + " " + operation + " "
        _calculatorState.value = calculatorState.value.copy(expression = newCalculation)
    }

    private fun getAnswer(answer: String) {

    }

    private fun clearExpression(operation: String) {
        if (_calculatorState.value.expression.isNotEmpty()) {


            //to clear last digit
            if (operation == "Del") {
                val newExpression = _calculatorState.value.expression.substring(
                    0,
                    _calculatorState.value.expression.length - 1
                )
                val answer = _calculatorState.value.answer
                val secondLastCharacter=_calculatorState.value.expression[_calculatorState.value.expression.length-2]
                Log.d("checksecondlast", secondLastCharacter.toString())
                _calculatorState.update { calculatorState ->
                    calculatorState.copy(
                        expression = newExpression,
                        answer = if (secondLastCharacter.isDigit()

                        ) evaluateExpression(newExpression) else (answer)
                    )
                }
            }
            //to clear entire string
            else {
                _calculatorState.update { calculatorState ->
                    calculatorState.copy(
                        expression = "",
                        answer = ""
                    )
                }
            }
        }
    }


}