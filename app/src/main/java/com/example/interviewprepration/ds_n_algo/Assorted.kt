package com.example.interviewprepration.ds_n_algo

import android.util.Log
import com.example.interviewprepration.kotlinrefresher.LOG_TAG
import java.util.Stack
import kotlin.math.max
import kotlin.math.min

object Assorted {

    fun driverFunction() {
        rainwaterTrapping()
    }

    fun rainwaterTrapping() {

        val originalArray = intArrayOf(7, 0, 4, 2, 5, 0, 6, 4, 0, 5)
        val leftGreater = previousGreaterElement(originalArray)
        val rightGreater = nextGreaterElement(originalArray)

        var water = 0
        var right = Int.MIN_VALUE

        for (counter in (originalArray.size - 2) downTo 1) {
            right = max(right, originalArray[rightGreater[counter]])
            Log.e(LOG_TAG, "Inside rainwaterTrapping() loop, right: $right")
            if (min(originalArray[leftGreater[counter]], right) > originalArray[counter]) {
                Log.e(
                    LOG_TAG,
                    "Inside rainwaterTrapping(), adding water for counter: $counter, ${
                        min(
                            originalArray[leftGreater[counter]],
                            right
                        ) - originalArray[counter]
                    }"
                )
                water += min(originalArray[leftGreater[counter]], right) - originalArray[counter]
            }

        }
        Log.e(LOG_TAG, "Inside rainwaterTrapping(), water trapped: $water")
    }

    fun nextGreaterElement(inputArray: IntArray): IntArray {

        val rightGreater = IntArray(inputArray.size) { Int.MIN_VALUE }
        val stack = Stack<Int>()
        for ((index, element) in inputArray.withIndex().reversed()) {
            while (stack.isNotEmpty() && inputArray[stack.peek()] <= element) {
                stack.pop()
            }

            if (stack.isEmpty() || inputArray[stack.peek()] < element) {
                stack.push(index)
            }
            rightGreater[index] = stack.peek()
        }
        Log.e(
            LOG_TAG,
            "Inside rainwaterTrapping()->nextGreaterElement(), inputArray: ${inputArray.contentToString()}"
        )
        Log.e(
            LOG_TAG,
            "Inside rainwaterTrapping()->nextGreaterElement(), rightGreater: ${rightGreater.contentToString()}"
        )
        return rightGreater
    }

    fun previousGreaterElement(inputArray: IntArray): IntArray {
        val leftGreater = IntArray(inputArray.size) { Int.MIN_VALUE }
        val stack = Stack<Int>()
        for ((index, element) in inputArray.withIndex()) {
            while (stack.isNotEmpty() && inputArray[stack.peek()] <= element) {
                stack.pop()
            }

            if (stack.isEmpty() || inputArray[stack.peek()] < element) {
                stack.push(index)
            }
            leftGreater[index] = stack.peek()
        }
        Log.e(
            LOG_TAG,
            "Inside rainwaterTrapping()->previousGreaterElement(), inputArray: ${inputArray.contentToString()}"
        )
        Log.e(
            LOG_TAG,
            "Inside rainwaterTrapping()->previousGreaterElement(), leftGreater: ${leftGreater.contentToString()}"
        )
        return leftGreater
    }
}