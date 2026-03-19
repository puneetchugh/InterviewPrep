package com.example.interviewprepration.ds_n_algo

import android.util.Log

object Search {

    val LOG_TAG: String = Search::class.java.name
    fun driverFunction() {

        val target = 4
        val array = arrayOf(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2, 3, 4, 5)
        val pivot = findPivot(array)
        Log.e(LOG_TAG, "Inside driverFunction, pivot index: $pivot, pivot value: ${array[pivot]}")

        val foundIndex = if (target >= array[0]) {
            binarySearch(array, 0, pivot - 1, target)
        } else {
            binarySearch(array, pivot, array.size - 1, target)
        }
        Log.e(
            LOG_TAG,
            "Inside driverFunction, foundIndex1: $foundIndex, value: ${foundIndex?.let { array[it] }}"
        )

        val array1 = arrayOf(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 1, 2)
        val pivot1 = findPivot(array1)
        Log.e(
            LOG_TAG,
            "Inside driverFunction, pivot1 index1: $pivot1, pivot1 value: ${array1[pivot1]}"
        )

        val foundIndex1 = if (target >= array1[0]) {
            binarySearch(array1, 0, pivot1 - 1, target)
        } else {
            binarySearch(array1, pivot1, array1.size - 1, target)
        }
        Log.e(
            LOG_TAG,
            "Inside driverFunction, foundIndex1: $foundIndex1, value: ${foundIndex1?.let { array1[it] }}"
        )


        val array11 = arrayOf(5, 8, 11, 23, 33, 2, 3, 4)
        val pivot11 = findPivot(array11)
        Log.e(
            LOG_TAG,
            "Inside driverFunction, pivot11 index11: $pivot11, pivot11 value: ${array11[pivot11]}"
        )
        val foundIndex11 = if (target >= array11[0]) {
            binarySearch(array11, 0, pivot11 - 1, target)
        } else {
            binarySearch(array11, pivot11, array11.size - 1, target)
        }
        Log.e(
            LOG_TAG,
            "Inside driverFunction, foundIndex11: $foundIndex11, value: ${foundIndex11?.let { array11[it] }}"
        )

    }

    fun binarySearch(array: Array<Int>, start: Int, end: Int, target: Int): Int? {

        if (start > end) {
            Log.e(LOG_TAG, "start>end, number can't be found..")
        }
        val mid = (start + end) / 2
        var startIndex = start
        var endIndex = end

        if (array[mid] == target) {
            return mid
        } else if (array[mid] > target) {
            endIndex = mid - 1
        } else {
            startIndex = mid + 1
        }
        return binarySearch(array, startIndex, endIndex, target)
    }

    fun findPivot(array: Array<Int>): Int {
        var start = 0
        var end = array.size - 1
        var index = 0
        while (start < end) {

            val mid = (start + end) / 2
            Log.e(
                "Search",
                "Inside findPivot LOOP, start: $start, array[$start]: ${array[start]}, end: $end, array[$end]: ${array[end]}, mid: $mid, array[$mid]: ${array[mid]}"
            )
            if (array[start] < array[end]) {
                index = start
                break
            } else if (array[start] < array[mid]) {
                start = mid + 1
                index = start
            } else if (array[start] > array[mid]) {
                end = mid
                index = end
            } else {
                Log.e(
                    "Search",
                    "Inside findPivot else, start: $start, array[$start]: ${array[start]}, end: $end, array[$end]: ${array[end]}, mid: $mid, array[$mid]: ${array[mid]}"
                )
                if (array[start] == array[mid] && start == mid) {
                    if (array[end] < array[mid]) {
                        index = end
                    } else {
                        index = start
                    }
                    break
                } else {
                    Log.e(
                        "Search",
                        "Inside findPivot else->else, SPECIFIC CASE start: $start, array[$start]: ${array[start]}, end: $end, array[$end]: ${array[end]}, mid: $mid, array[$mid]: ${array[mid]}"
                    )
                }
            }
        }
        return index
    }
}