package com.example.interviewprepration.ds_n_algo

import android.util.Log
import java.util.Stack

object PreviousSmaller {

    val LOG_TAG = PreviousSmaller::class.java.name

    fun driverFunction() {
        val array = intArrayOf(4, 5, 2, 10, 8)
        previousSmall(array)
    }

    fun previousSmall(array: IntArray) {

        val stack = Stack<Int>()
        val newArray = Array(array.size) { -1 }

        for ((index, item) in array.withIndex()) {

            while (stack.isNotEmpty() && stack.peek() >= item) {
                stack.pop()
            }
            if (stack.isEmpty || item <= stack.peek()) {
                stack.push(item)
            }

            newArray[index] = stack.peek()
        }

        Log.e(LOG_TAG, "previousSmall: newArray: ${newArray.contentToString()}")
    }
}