package com.example.interviewprepration.ds_n_algo

import android.util.Log

object Boogle {

    val LOG_TAG: String = Boogle::class.java.name

    fun driverFunction() {
        val word = "PUNEET"
        val array = arrayOf<CharArray>(
            "PUNEX".toCharArray(),
            "TENEA".toCharArray(),
            "DECTB".toCharArray(),
            "QTWZK".toCharArray(),
            "QPOML".toCharArray()
        )

        val visited = Array(array.size) { BooleanArray(array[0].size) }

        for (row in 0 until array.size) {
            for (col in 0 until array[0].size) {
                Log.e(LOG_TAG, "Inside driverFunction visiting ($row, $col), ${array[row][col]}")
                if (array[row][col] == word[0]) {
                    Log.e(
                        LOG_TAG,
                        "boogle driverFunction: ${boogle(array, visited, word, 1, row, col)}"
                    )
                }

            }
        }
    }

    // single word search without Trie
    fun boogle(
        array: Array<CharArray>,
        visited: Array<BooleanArray>,
        word: String,
        index: Int,
        row: Int,
        col: Int
    ): Boolean {
        if (index >= word.length) {
            Log.e(LOG_TAG, "boolge, found word: $word")
            return true
        }
        Log.e(
            LOG_TAG,
            "Inside boogle: row: $row, col: $col, visiting : ${array[row][col]}, index: $index, word: $word, letter at index: ${word[index]}"
        )
        visited[row][col] = true

        val points = arrayOf(
            -1 to -1,
            -1 to 0,
            -1 to 1,
            0 to -1,
            0 to 1,
            1 to -1,
            1 to 0,
            1 to 1
        )

        for (point in points) {
            Log.e(
                LOG_TAG,
                "boogle: Inside loop:  visiting: (${row + point.first}, ${col + point.second}), index: ${index + 1}"
            )
            if (isSafe(
                    row + point.first,
                    col + point.second,
                    array
                ) && array[row + point.first][col + point.second] == word[index]
            ) {
                val isTrue =
                    boogle(array, visited, word, index + 1, row + point.first, col + point.second)
                Log.e(
                    LOG_TAG,
                    "boogle: isTrue: $isTrue, visiting: (${row + point.first}, ${col + point.second}), visiting ${array[row + point.first][col + point.second]}"
                )
                if (isTrue)
                    return isTrue
            }
        }
        visited[row][col] = false
        return false
    }

    fun isSafe(x: Int, y: Int, array: Array<CharArray>): Boolean {
        return x >= 0 && y >= 0 && x < array.size && y < array[0].size
    }
}