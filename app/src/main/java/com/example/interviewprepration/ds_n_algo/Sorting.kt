package com.example.interviewprepration.ds_n_algo

import android.util.Log

object Sorting {

    val LOG_TAG: String = Sorting::class.java.name

    fun driverFunction(){
        val array = intArrayOf(0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1)
        sort0s1s2s(array)
    }

    // Sort an array of 0’s, 1’s, and 2’s (Dutch National Flag Problem)
    fun sort0s1s2s(array: IntArray) {

        Log.e(LOG_TAG, "Inside sort0s1s2s(), input array: ${array.contentToString()}")
        var oneIndex = 0
        var twoIndex = array.size - 1
        var counter = 0

        while (counter <= twoIndex) {
            if(array[counter] == 0){
                swap(counter, oneIndex, array)
                oneIndex++
                counter++
            } else if(array[counter] == 2){
                swap(counter, twoIndex, array)
                twoIndex--
            }
            else{
                counter++
            }
        }
        Log.e(LOG_TAG, "Inside sort0s1s2s(), output array: ${array.contentToString()}")

    }

    fun swap(index1: Int, index2: Int, array: IntArray){
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }
}