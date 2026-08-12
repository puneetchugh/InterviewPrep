package com.example.interviewprepration.ds_n_algo

import android.util.Log
import kotlin.math.max
import kotlin.math.min

object DynamicProgramming {

    private val LOG_TAG = DynamicProgramming::class.java.name

    fun driverFunction() {
        val minCoins = coinChange(coinList = intArrayOf(1, 3, 5), target = 11)
        Log.e(LOG_TAG, "Inside driverFunction, coinChange: $minCoins")

        val text11 = "ABC"
        val text12 = "ACD"
        val lcs1 = longestCommonSubsequence(text11, text12)
        Log.e(LOG_TAG, "Inside driverFunction, lcs1 for $text11 and $text12: $lcs1")

        val text21 = "AGGTAB"
        val text22 = "GXTXAYB"
        val lcs2 = longestCommonSubsequence(text21, text22)
        Log.e(LOG_TAG, "Inside driverFunction, lcs1 for $text21 and $text22: $lcs2")

        knapSack()
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

    fun longestCommonSubsequence(text1: String, text2: String): Int {

        val dp = Array(text1.length + 1) { IntArray(text2.length + 1) { 0 } }

        dp[0][0] = 0

        for (input1Counter in 1..text1.length) {
            for (input2Counter in 1..text2.length) {
                if (text1[input1Counter - 1] == text2[input2Counter - 1]) {
                    Log.e(
                        LOG_TAG,
                        "Inside lcs(), input1Counter: $input1Counter, input2Counter: $input2Counter, match found: ${text1[input1Counter - 1]}"
                    )
                    dp[input1Counter][input2Counter] = dp[input1Counter - 1][input2Counter - 1] + 1
                } else {
                    dp[input1Counter][input2Counter] = max(
                        dp[input1Counter - 1][input2Counter],
                        dp[input1Counter][input2Counter - 1]
                    )
                }
            }
        }
        Log.e(LOG_TAG, "Inside lcs(), dp")
        dp.forEach {
            Log.e(LOG_TAG, "Inside lcs(), dp: ${it.contentToString()}")
        }
        return dp[text1.length][text2.length]
    }

    fun knapSack() {
        val weights = intArrayOf(1, 2, 3, 8, 7, 4)
        val values = intArrayOf(20, 5, 10, 40, 15, 25)

        Log.e(
            LOG_TAG, "Inside knapSack(), max profit: ${
                knapSackHelper(
                    values = values,
                    weights = weights,
                    remaining = 10,
                    index = 0,
                    includedPairs = emptyList()
                )
            }"
        )
    }

    fun knapSackHelper(values: IntArray, weights: IntArray, remaining: Int, index: Int, includedPairs: List<Pair<Int, Int>>): Int {
        if (remaining < 0) {
            return Int.MIN_VALUE
        }
        if (index >= values.size || remaining == 0) {
            Log.e(LOG_TAG, "Inside knapSackHelper(), total value: ${includedPairs.sumOf { it.first }}, includedPairs: ${includedPairs.joinToString()}")
            return 0
        }

        val including = values[index] + knapSackHelper(
            values = values,
            weights = weights,
            remaining = remaining - weights[index],
            index = index + 1,
            includedPairs = includedPairs + Pair(values[index], weights[index])
        )

        val excluding = knapSackHelper(
            values = values,
            weights = weights,
            remaining = remaining,
            index = index + 1,
            includedPairs = includedPairs
        )
        return max(including, excluding)
    }
}