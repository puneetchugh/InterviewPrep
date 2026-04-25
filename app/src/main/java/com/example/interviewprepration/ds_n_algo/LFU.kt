package com.example.interviewprepration.ds_n_algo

import android.util.Log
import java.util.PriorityQueue


object LFU {
    val LOG_TAG: String = LFU::class.java.name
    val MAX_SIZE = 5

    val set = mutableSetOf<Int>()
    val map = PriorityQueue<Pair<Int, Int>> { item1, item2 -> item1.second - item2.second }

    fun driverFunction() {
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(1)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(2)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(3)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(4)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(1)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(5)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(4)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(6)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(2)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(3)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(2)}")
        Log.e(LOG_TAG, "Inside driverFunction, lfuCaching: ${lfuCaching(5)}")

    }

    fun lfuCaching(page: Int): Boolean {
        Log.e(LOG_TAG, "Inside lfuCaching, page: $page, cache: $set, priorityQueue: $map")

        if (set.contains(page)) {
            val pair = map.find { it.first == page }
            map.remove(pair)
            map.add(pair!!.copy(second = pair.second + 1))
            set.add(page)
        } else {
            if (set.size == MAX_SIZE) {
                val deleted = map.remove()
                set.remove(deleted.first)
            }
            map.add(Pair(page, 1))
            set.add(page)
        }
        return false
    }

}