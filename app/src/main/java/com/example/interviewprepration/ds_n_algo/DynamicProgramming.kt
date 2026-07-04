package com.example.interviewprepration.ds_n_algo

import android.util.Log
import kotlin.math.min

object DynamicProgramming {

    private val LOG_TAG = DynamicProgramming::class.java.name

    fun driverFunction() {
        val minCoins = coinChange(coinList = intArrayOf(1, 3, 5), target = 11)
        Log.e(LOG_TAG, "Inside driverFunction, coinChange: $minCoins")
    }

    // min number of coins to make a target sum
    fun coinChange(coinList: IntArray, target: Int): Int {
        val outputArray = IntArray(target + 1) { target + 1 }

        outputArray[0] = 0

        for (counter in 1..target) {
            for (coin in coinList) {
                Log.e(LOG_TAG, "Inside inner loop, counter: $counter, coin: $coin")
                if (coin <= counter) {
                    outputArray[counter] =
                        min(outputArray[counter - coin] + 1, outputArray[counter])
                }
            }
        }
        return if (outputArray[target] > target) {
            -1
        } else {
            outputArray[target]
        }
    }
}