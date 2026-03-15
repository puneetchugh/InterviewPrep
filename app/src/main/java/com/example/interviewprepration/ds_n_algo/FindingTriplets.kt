package com.example.interviewprepration.ds_n_algo

import android.util.Log

object FindingTriplets {

    data class Triplet(val first: Int, val second: Int, val third: Int)
    val LOG_TAG = FindingTriplets::class.java.name

    fun driverFunction() {
        val array =
            arrayOf(10, 20, 30, 40, 50, 15, 45, 55, 65, 3, 7, 9, 20, 30, 5, 9, 13, 13, 7, 7, 9)
        val triplets = findingTriplets(array.toIntArray(), 43)
        Log.e(LOG_TAG, "triplets: $triplets")
    }

    //finds the triplets of the actual numbers
    fun findingTriplets(array: IntArray, target: Int): List<Triplet> {

        val triplets = mutableListOf<Triplet>()
        array.sort()
        for ((index, number) in array.withIndex()) {
            val mutableMap = mutableMapOf<Int, MutableList<Int>>() // Number -> List <Position>
            for (counter in index + 1 until array.size) {
                mutableMap.getOrPut(array[counter]) { mutableListOf() }.add(counter)
            }
            for (counter in index + 1 until array.size) {
                val complement = target - number - array[counter]
                if (mutableMap[array[counter]]!!.size == 1) {
                    mutableMap.remove(array[counter])
                } else {
                    mutableMap[array[counter]]!!.remove(counter)
                }
                if (mutableMap.containsKey(complement)) {
                    var size = mutableMap[complement]!!.size
                    while (size > 0) {
                        triplets.add(Triplet(number, array[counter], complement))
                        size--
                    }

                }
            }
        }
        return triplets
    }



}