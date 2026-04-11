package com.example.interviewprepration.ds_n_algo

import android.util.Log

object SlidingWindow {

    val LOG_TAG: String = SlidingWindow::class.java.name
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

        val maxOnes = maxOnesSequence(input = arrayOf(0, 0, 1, 0, 1, 1, 1, 0, 1, 1))
        Log.e(LOG_TAG, "Inside driverFunction, maxOnes: $maxOnes")

        val maxOnesKZeros1 =
            maxOnesSequenceKZeros1(array = arrayOf(1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0), k = 2)
        Log.e(LOG_TAG, "Inside driverFunction, k = 2, maxOnesKZeros: $maxOnesKZeros1")

        val maxOnesKZeros2 =
            maxOnesSequenceKZeros2(array = arrayOf(1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0), k = 2)
        Log.e(LOG_TAG, "Inside driverFunction, k = 2, maxOnesKZeros2: $maxOnesKZeros2")

        val maxOnesKZeros11 =
            maxOnesSequenceKZeros1(array = arrayOf(1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0), k = 1)
        Log.e(LOG_TAG, "Inside driverFunction, k = 1, maxOnesKZeros11: $maxOnesKZeros11")

        val maxOnesKZeros22 =
            maxOnesSequenceKZeros2(array = arrayOf(1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0), k = 1)
        Log.e(LOG_TAG, "Inside driverFunction, k = 1, maxOnesKZeros22: $maxOnesKZeros22")

        val input1 = arrayOf(0, 5, -7, 1, -4, 7, 6, 1, 4, 1, 10)
        val range = subArrayWithGivenSum1(array = input1, target = -3)
        Log.e(
            LinkedList.LOG_TAG,
            "Inside driverFunction()->subArrayWithGivenSum(), range: $range for input: ${input1.contentToString()}, target: -3, array slice :${
                input1.sliceArray(
                    range!!
                ).contentToString()
            }"
        )
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

    //Find maximum length sequence of continuous ones
    fun maxOnesSequence(input: Array<Int>): Int {
        if (input.isEmpty())
            return 0

        var maxLength = 0
        var start = 0

        for ((index, item) in input.withIndex()) {
            if (item == 1) {
                if (index - start + 1 > maxLength) {
                    maxLength = index - start + 1
                }
            } else {
                if (index - start > maxLength) {
                    maxLength = index - start
                }
                start = index + 1
            }
        }
        return maxLength
    }

    // find maximum sequence of ones with given k zeros
    fun maxOnesSequenceKZeros1(array: Array<Int>, k: Int): Int {
        if (array.isEmpty()) {
            return 0
        }

        var start = 0
        var zerosCount = 0
        var end = 0
        var maxLength = 0

        while (end < array.size) {
            Log.e(
                LOG_TAG,
                "Inside maxOnesSequenceKZeros() while loop, current value: ${array[end]} start: $start, end: $end, zerosCount: $zerosCount, maxLength: $maxLength"
            )
            if (array[end] == 1) {
                if (end - start + 1 > maxLength) {
                    maxLength = end - start + 1
                }
            } else {
                zerosCount++
                while (zerosCount > k && start <= end) {
                    if (array[start] == 0) {
                        zerosCount--
                    }
                    start++
                }
                if (end - start + 1 > maxLength) {
                    maxLength = end - start + 1
                }
            }
            end++
        }
        return maxLength
    }

    // find maximum sequence of ones with given k zeros - clean code
    fun maxOnesSequenceKZeros2(array: Array<Int>, k: Int): Int {
        if (array.isEmpty()) {
            return 0
        }

        var start = 0
        var zerosCount = 0
        var end = 0
        var maxLength = 0

        while (end < array.size) {
            Log.e(
                LOG_TAG,
                "Inside maxOnesSequenceKZeros() while loop, current value: ${array[end]} start: $start, end: $end, zerosCount: $zerosCount, maxLength: $maxLength"
            )

            if (array[end] == 0) {
                zerosCount++
            }
            while (zerosCount > k && start <= end) {
                if (array[start] == 0) {
                    zerosCount--
                }
                start++
            }
            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1
            }
            /*
            if (array[end] == 1) {
                if (end - start + 1 > maxLength) {
                    maxLength = end - start + 1
                }
            } else {
                zerosCount++
                while (zerosCount > k && start <= end) {
                    if (array[start] == 0) {
                        zerosCount--
                    }
                    start++
                }
                if (end - start + 1 > maxLength) {
                    maxLength = end - start + 1
                }
            }*/
            end++
        }
        return maxLength
    }

    fun subArrayWithGivenSum1(array: Array<Int>, target: Int): IntRange? {
        var sumSoFar = 0
        var high = 0
        var map = mutableMapOf<Int, Int>()

        for ((index, item) in array.withIndex()) {

            sumSoFar += item
            Log.e(
                LinkedList.LOG_TAG,
                "Inside subArrayWithGivenSum1(), index: $index, item: $item, sumSoFar: $sumSoFar, high: $high"
            )
            if (map.contains(sumSoFar - target)) {
                return IntRange(start = (map[sumSoFar - target]!!).plus(1), endInclusive = index)
            }
            map[sumSoFar] = index
            Log.e(LinkedList.LOG_TAG, "Inside subArrayWithGivenSum1(), map: $map")
        }
        return null
    }
}