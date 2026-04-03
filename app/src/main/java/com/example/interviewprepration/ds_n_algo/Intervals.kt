package com.example.interviewprepration.ds_n_algo

import android.util.Log

object Intervals {

    val LOG_TAG: String = Intervals::class.java.name

    fun driverFunction() {
        val inputIntervals = arrayOf(
            Interval(name = "Interval", start = 0, end = 3),
            Interval(name = "Interval", start = 1, end = 5),
            Interval(name = "Interval", start = 4, end = 8),
            Interval(name = "Interval", start = 10, end = 15),
            Interval(name = "Interval", start = 12, end = 18),
            Interval(name = "Interval", start = 20, end = 25),
            Interval(name = "Interval", start = 22, end = 24),
        ).apply {
            shuffle()
        }

        Log.e(LOG_TAG, "Insert empty intervals: Input intervals")
        for (interval in inputIntervals) {
            Log.e(
                LOG_TAG,
                "Interval: ${interval.name}, start: ${interval.start}, end: ${interval.end}"
            )
        }
        val outputIntervals = insertEmptyIntervals(inputIntervals)
        Log.e(LOG_TAG, "Insert empty intervals: Output intervals")
        for (interval in outputIntervals) {
            Log.e(
                LOG_TAG,
                "Interval: ${interval.name}, start: ${interval.start}, end: ${interval.end}"
            )
        }
    }

    data class Interval(var name: String? = null, var start: Int, var end: Int)

    fun insertEmptyIntervals(intervals: Array<Interval>): List<Interval> {

        val emptyInterval = "Empty interval"
        val output = mutableListOf<Interval>()
        var lastEndPoint = 0
        for (interval in intervals.sortedBy { it.start }) {
            if (interval.start > lastEndPoint) {
                output.add(
                    Interval(
                        name = emptyInterval,
                        start = lastEndPoint,
                        end = interval.start
                    )
                )
            }
            output.add(interval)
            if (interval.end > lastEndPoint) {
                lastEndPoint = interval.end
            }
        }
        return output.toList()
    }
}