package com.example.interviewprepration.ds_n_algo

import android.util.Log
import kotlin.math.max

object SlidingWindow {

    val LOG_TAG = SlidingWindow::class.java.name
    fun driverFunction() {

        val input = "abcbdbdbbdcdabd"
        Log.e(
            LOG_TAG,
            "Inside driverFunction, longestSubstringKDistinct: ${
                longestSubstringKDistinct(
                    input,
                    2
                )
            }"
        )

        allPermutations()
    }

    //Find the longest substring of a string containing k distinct characters
    fun longestSubstringKDistinct(input: String, k: Int): String {

        var start = 0
        var end = 0

        var longest = ""

        var map = mutableMapOf<Char, Int>()

        while (end < input.length) {
            Log.e(
                LOG_TAG,
                "Inside while loop, start: $start, end: $end, map: $map, current: ${input[end]}"
            )
            if (!map.contains(input[end])) {
                while (start <= end && map.size == k) {
                    if (map[input[start]]!! > 1) {
                        map[input[start]] = map[input[start]]?.minus(1)!!
                    } else {
                        map.remove(input[start])
                    }
                    start++
                }
            }

            map[input[end]] = map.getOrDefault(input[end], 0) + 1
            Log.e(
                LOG_TAG,
                "Inside while loop at the end, start: $start, end: $end, map: $map, current: ${input[end]}, longest: $longest, current: ${end - start + 1}"
            )

            if (longest.length < end - start + 1) {
                longest = input.substring(start, end + 1)
            }
            end++
        }
        return longest
    }

    //List all permutations of a string
    fun allPermutations() {

        val input = "abc"
        val outputList = mutableListOf<String>()
        allPermutations(
            input = input,
            remaining = input,
            output = "",
            list = outputList
        )
        Log.e(LOG_TAG, "Inside allPermutations: $outputList")
    }

    fun allPermutations(
        input: String,
        remaining: String,
        output: String,
        list: MutableList<String>
    ) {

        if (remaining.isBlank()) {
            list.add(output)
            return
        }

        for ((index, eachChar) in remaining.withIndex()) {
            allPermutations(
                input = input,
                remaining = remaining.removeRange(index, index + 1),
                output = output + eachChar,
                list = list
            )
        }
    }

}