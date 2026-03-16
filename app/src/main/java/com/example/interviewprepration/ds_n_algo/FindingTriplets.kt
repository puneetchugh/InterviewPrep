package com.example.interviewprepration.ds_n_algo

import android.util.Log

object FindingTriplets {

    data class Triplet(val first: Int, val second: Int, val third: Int)

    val LOG_TAG: String = FindingTriplets::class.java.name

    fun driverFunction() {
        val array =
            arrayOf(10, 20, 30, 40, 50, 15, 45, 55, 65, 3, 7, 9, 20, 30, 5, 9, 13, 13, 7, 7, 9)
        val triplets = findingTriplets(array.toIntArray(), 43)
        Log.e(LOG_TAG, "${triplets.size} triplets using HashMap: $triplets")
        triplets.forEach {
            Log.e(LOG_TAG, "Iterating over triplets, triplet: $it")
        }
        val triplets2 = findTriplets(array.toIntArray(), 43)
        Log.e(LOG_TAG, "${triplets2.size} triplets using two pointers: $triplets2")
        triplets2.forEach {
            Log.e(LOG_TAG, "Iterating over triplets2, triplet: $it")
        }
    }

    //finds the triplets of the actual numbers
    fun findingTriplets(array: IntArray, target: Int): List<Triplet> {

        val triplets = mutableListOf<Triplet>()
        array.sort()
        Log.e(LOG_TAG, "Inside findTriplets, array: ${array.contentToString()}")

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

    fun findTriplets(array: IntArray, target: Int): List<Triplet> {

        val triplets = mutableListOf<Triplet>()
        array.sort()
        Log.e(LOG_TAG, "Inside findTriplets, array: ${array.contentToString()}")
        for ((index, number) in array.withIndex()) {

            //Log.e(LOG_TAG, "Currently visiting index: $index, number: $number")
            var left = index + 1
            var right = array.size - 1
            while (left < array.size && right >= 0 && left < right) {
                /*Log.e(
                    LOG_TAG,
                    "Inside while loop, number: $number, left index: $left, right index: $right, left value: ${array[left]}, right value: ${array[right]}"
                )*/
                if (number + array[left] + array[right] == target) {
                    Log.e(
                        LOG_TAG,
                        "Found triplet: left index: number index: $index, left index: $left, right index: $right, Triplets: $number, ${array[left]}, ${array[right]}"
                    )
                    triplets.add(Triplet(number, array[left], array[right]))
                    // when there are multiple occurences of the numbers at left and right index, both that make up the triplets
                    if ((left + 1 < array.size && array[left] == array[left + 1]) && (right - 1 >= 0 && array[right] == array[right - 1]) && left != right - 1) {
                        triplets.add(Triplet(number, array[left], array[right]))
                        triplets.add(Triplet(number, array[left], array[right]))
                        triplets.add(Triplet(number, array[left], array[right]))
                        left+=2
                        right-=2
                    } else if (left + 1 < array.size && array[left] == array[left + 1]) {
                        left++
                    } else if ((right - 1) >= 0 && array[right] == array[right - 1]) {
                        right--
                    } else {
                        if (left + 1 < array.size) {
                            left++
                        } else {
                            right--
                        }
                    }
                } else if (number + array[left] + array[right] > target) {
                    //Log.e(LOG_TAG, "Sum is greater than target: ${number + array[left] + array[right]}")
                    right--
                } else {
                    //Log.e(LOG_TAG, "Sum is less than target: ${number + array[left] + array[right]}")
                    left++
                }
            }
        }
        return triplets
    }
}