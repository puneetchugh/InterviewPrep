package com.example.interviewprepration.kotlinrefresher

import android.util.Log

object LambdaFunctions {

    val LOG_TAG: String = LambdaFunctions::class.java.name

    fun driverFunction() {
        lambdaFunction()
    }

    fun lambdaFunction() {

        val difference = { input1: Int, input2: Int -> input1 - input2 }

        val input1 = 5
        val input2 = 6

        val multiplyOutput = execute(input1, input2, ::multiply)
        val sumOutput = execute(input1, input2, ::sum)
        val differenceOutput = execute(input1, input2, difference)

        Log.e(LOG_TAG, "input1: ${input1}, input2: ${input2}, multiplyOutput: $multiplyOutput, sumOutput: $sumOutput, differenceOutput: $differenceOutput")
    }

    fun execute(input1: Int, input2: Int, operation: (Int, Int) -> Int): Int {
        return operation(input1, input2)
    }

    fun sum(input1: Int, input2: Int): Int {
        return input1 + input2
    }

    fun multiply(input1: Int, input2: Int): Int {
        return input1 * input2
    }
}