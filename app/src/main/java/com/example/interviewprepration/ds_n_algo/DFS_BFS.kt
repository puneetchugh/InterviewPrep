package com.example.interviewprepration.ds_n_algo

import kotlin.math.max
import android.util.Log

object DFS_BFS {

    val LOG_TAG: String
        get() = DFS_BFS::class.java.simpleName

    fun driverFunction() {

        val inputArray = createInputArray()
        longestPath(array = inputArray, start = Pair(0, 0), target = Pair(4, 4))

        val size = 30
        val ladders = listOf(
            Pair(3, 22),
            Pair(4, 8),
            Pair(11, 26),
            Pair(20, 21)
        )
        val snakes = listOf(
            Pair(27, 1),
            Pair(21, 9),
            Pair(17, 4),
            Pair(19, 7)
        )
        snakesNLadders(size, snakes.toTypedArray(), ladders.toTypedArray())
    }

    // longest path
    fun visitedArray(rows: Int, cols: Int): Array<BooleanArray> {
        return Array(rows) { BooleanArray(size = cols) }
    }

    fun createInputArray(): Array<IntArray> {
        return arrayOf(
            intArrayOf(1, 1, 0, 1, 0),
            intArrayOf(0, 1, 0, 1, 1),
            intArrayOf(0, 1, 1, 1, 1),
            intArrayOf(1, 1, 0, 1, 0),
            intArrayOf(0, 1, 0, 1, 1),
        )
    }

    fun longestPath(array: Array<IntArray>, start: Pair<Int, Int>, target: Pair<Int, Int>) {
        Log.e(LOG_TAG, "Inside longestPath(), start: $start, target: $target")
        val visited = visitedArray(rows = array.size, cols = array[0].size)
        val longest = longestPathHelper(
            array = array,
            longest = 0,
            currentLength = 0,
            current = start,
            target = target,
            visited = visited
        )
        Log.e(LOG_TAG, "Inside driverFunction(), longestPath: $longest")
    }

    fun longestPathHelper(
        array: Array<IntArray>,
        longest: Int,
        currentLength: Int,
        current: Pair<Int, Int>,
        target: Pair<Int, Int>,
        visited: Array<BooleanArray>
    ): Int {
        if (current == target) {
            Log.e(
                LOG_TAG,
                "Inside longestPathHelper(), found target, currentLength: $currentLength, longest: $longest"
            )
            return max(longest, currentLength)
        }
        Log.e(
            LOG_TAG,
            "Inside longestPathHelper(), current: $current, target: $target, current length: $currentLength"
        )
        visited[current.first][current.second] = true
        val points = arrayOf(
            Pair(-1, -1),
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, -1),
            Pair(0, 1),
            Pair(1, -1),
            Pair(1, 0),
            Pair(1, 1)
        )

        var currentLongest = longest
        for (point in points) {
            if (isValid(
                    current.first + point.first,
                    current.second + point.second,
                    array,
                    visited
                )
            ) {
                currentLongest = max(
                    currentLongest,
                    longestPathHelper(
                        array,
                        currentLongest,
                        currentLength + 1,
                        Pair(current.first + point.first, current.second + point.second),
                        target,
                        visited
                    )
                )
            }
        }
        visited[current.first][current.second] = false
        return currentLongest
    }

    fun isValid(x: Int, y: Int, array: Array<IntArray>, visited: Array<BooleanArray>): Boolean {
        return x >= 0 && y >= 0 && x < array.size && y < array[0].size && array[x][y] == 1 && !visited[x][y]
    }

    // shortest path - Snakes and Ladders
    data class Point(val point: Int, val moves: Int)

    fun snakesNLadders(
        size: Int,
        snakes: Array<Pair<Int, Int>>,
        ladders: Array<Pair<Int, Int>>
    ) {
        val board = Array(size) { -1 }
        snakes.forEach { (key, value) -> board[key - 1] = value - 1 }
        ladders.forEach { (key, value) -> board[key - 1] = value - 1 }

        val queue = ArrayDeque<Point>()
        queue.add(Point(point = 0, moves = 0))

        while (!queue.isEmpty()) {
            val visiting = queue.removeFirst()

            if (visiting.point == size - 1) {
                Log.e(LOG_TAG, "SnakesNLadders: shortest path: ${visiting.moves}")
                break
            }

            for (counter in 0 until 6) {

                if (visiting.point + counter >= size) {
                    continue
                }

                val nextStep = if (board[visiting.point + counter] != -1) {
                    board[visiting.point + counter]
                } else {
                    visiting.point + counter
                }

                val nextNode = Point(point = nextStep, moves = visiting.moves + 1)
                queue.add(nextNode)
            }
        }
    }
}