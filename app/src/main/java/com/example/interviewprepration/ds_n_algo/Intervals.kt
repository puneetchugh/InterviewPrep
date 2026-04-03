package com.example.interviewprepration.ds_n_algo

object Intervals {

    fun driverFunction() {

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
            if(interval.end > lastEndPoint){
                lastEndPoint = interval.end
            }
        }
        return output.toList()
    }
}