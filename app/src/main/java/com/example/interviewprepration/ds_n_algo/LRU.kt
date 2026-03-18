package com.example.interviewprepration.ds_n_algo

import android.util.Log
import java.util.LinkedList
import java.util.Queue

object LRU {

    val LOG_TAG: String = LRU::class.java.name
    fun driverFunction() {
        add(1)
        add(2)
        add(3)
        add(1)
        add(4)
        add(5)
        add(1)
        add(8)
        add(6)
        add(9)
        add(4)
        add(11)
        add(12)
        add(22)
        add(8)
        add(17)
    }

    val set: MutableSet<Int> = mutableSetOf()
    val queue: Queue<Int> = LinkedList()

    fun add(page: Int, capacity: Int = 10) {
        Log.e(LOG_TAG, "Inside lru add, page: $page, set: $set, queue: $queue")
        if (set.contains(page)) {
            val iterator = queue.iterator()
            while (iterator.hasNext()) {
                if (iterator.next() == page) {
                    iterator.remove()
                }
            }
        } else {
            if (set.size == capacity) {
                set.remove(queue.remove())
            }
        }
        set.add(page)
        queue.add(page)
        Log.e(LOG_TAG, "Inside add, set: $set, queue: $queue")
    }
}
