package com.example.interviewprepration.ds_n_algo

import android.util.Log
import java.util.PriorityQueue

object Sorting {

    val LOG_TAG: String = Sorting::class.java.name

    fun driverFunction() {
        val array = intArrayOf(0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1)
        sort0s1s2s(array)

        checkingPriorityQueue()

        val arrayInput = intArrayOf(10, 20, 5, 1, 4, 50, 70, 33, 22, 11, 10, 33, 50, 1, 1)
        topKFreq(input = arrayInput, topK = 4)
    }

    // Sort an array of 0’s, 1’s, and 2’s (Dutch National Flag Problem)
    fun sort0s1s2s(array: IntArray) {

        Log.e(LOG_TAG, "Inside sort0s1s2s(), input array: ${array.contentToString()}")
        var oneIndex = 0
        var twoIndex = array.size - 1
        var counter = 0

        while (counter <= twoIndex) {
            if (array[counter] == 0) {
                swap(counter, oneIndex, array)
                oneIndex++
                counter++
            } else if (array[counter] == 2) {
                swap(counter, twoIndex, array)
                twoIndex--
            } else {
                counter++
            }
        }
        Log.e(LOG_TAG, "Inside sort0s1s2s(), output array: ${array.contentToString()}")

    }

    fun swap(index1: Int, index2: Int, array: IntArray) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }

    //Top K Frequent in an Array
    fun topKFreq(input: IntArray, topK: Int) {
        /*if (input.isEmpty())
            return null*/

        val map = input.toTypedArray().groupingBy { it }.eachCount()

        val minPriorityQueue = PriorityQueue<Pair<Int, Int>> { pair1, pair2 ->
            if (pair1.first == pair2.first) {
                pair1.first - pair2.first
            } else {
                pair1.first - pair2.first
            }
        }

        map.forEach { pair ->
            minPriorityQueue.add(Pair(pair.value, pair.key))

            if (minPriorityQueue.size > topK) {
                minPriorityQueue.poll()
            }
        }

        while (minPriorityQueue.isNotEmpty()) {
            Log.e(LOG_TAG, "Inside topKFreq(), minPriorityQueue: ${minPriorityQueue.poll()}")
        }
    }

    // Testing with Priority Queue
    fun checkingPriorityQueue() {

        val array = intArrayOf(10, 20, 5, 1, 4, 50, 70, 33, 22, 11, 10, 33, 50, 1, 1)
        val freqArray = array.toTypedArray().groupingBy { it }.eachCount()
        Log.e(LOG_TAG, "Inside checkingPriorityQueue(), freqArray: $freqArray")

        val minPriorityQueue = PriorityQueue<Pair<Int, Int>> { pair1, pair2 ->
            if (pair1.first == pair2.first) {
                pair1.second - pair2.second
            } else {
                pair1.first - pair2.first
            }
        }

        freqArray.forEach {
            minPriorityQueue.add(Pair(it.value, it.key))
        }

        Log.e(
            LOG_TAG,
            "Inside checkingPriorityQueue(), total elements in minPriorityQueue: ${minPriorityQueue.size}"
        )

        while (minPriorityQueue.isNotEmpty()) {
            Log.e(
                LOG_TAG,
                "Inside checkingPriorityQueue(), minPriorityQueue, removing top: ${minPriorityQueue.poll()}"

            )
        }
    }
}