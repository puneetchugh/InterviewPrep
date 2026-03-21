package com.example.interviewprepration.ds_n_algo

import android.util.Log

object Backtracking {

    val LOG_TAG: String = Backtracking::class.java.name
    fun driverFunction() {
        nQueensWrapper(size = 5)
    }

    /*****************************************************
                N Queens Problem - Start
     *****************************************************/
    //All valid placements of the n queens problem
    fun nQueensWrapper(size: Int) {
        val array = Array(size) { BooleanArray(size) }
        val solutions = mutableListOf<String>()
        val currentSolution = Array(size) { -1 }

        nQueens(
            array = array, row = 0,
            solutions = solutions,
            currentSolution = currentSolution
        )
        Log.e(
            LOG_TAG,
            "Inside nQueensWrapper: with size: $size, solution: $solutions"
        )
    }

    fun nQueens(
        array: Array<BooleanArray>,
        row: Int,
        solutions: MutableList<String>,
        currentSolution: Array<Int>
    ) {
        if (row >= array.size) {
            Log.e(LOG_TAG, "nQueens: adding solution: ${currentSolution.contentToString()}")
            solutions.add(currentSolution.contentToString())
            return
        }
        for ((index, item) in array[row].withIndex()) {
            if (isValid(array = array, row = row, col = index) && canPlace(
                    array = array,
                    row = row,
                    col = index
                )
            ) {
                array[row][index] = true
                currentSolution[row] = index
                Log.e(
                    LOG_TAG,
                    "Inside nQueens: valid placement, row: $row, col: $index, currentSolution: ${currentSolution.contentToString()}"
                )
                nQueens(
                    array = array,
                    row = row + 1,
                    solutions = solutions,
                    currentSolution = currentSolution
                )

                currentSolution[row] = -1
                array[row][index] = false
            }
        }
    }

    fun canPlace(array: Array<BooleanArray>, row: Int, col: Int): Boolean {
        var rowCounter = row
        var colCounter = col

        while (rowCounter > 0 && colCounter > 0) {
            rowCounter--
            colCounter--
            if (array[rowCounter][colCounter]) {
                return false
            }
        }

        rowCounter = row
        colCounter = col
        while (rowCounter > 0 && colCounter < array.size - 1) {
            rowCounter--
            colCounter++
            if (array[rowCounter][colCounter]) {
                return false
            }
        }

        rowCounter = row
        colCounter = col

        while (rowCounter > 0) {
            rowCounter--
            if (array[rowCounter][colCounter]) {
                return false
            }
        }
        return true
    }

    fun isValid(array: Array<BooleanArray>, row: Int, col: Int): Boolean {
        return row >= 0 && row < array.size && col >= 0 && col < array[0].size
    }
    /*****************************************************
                N Queens Problem - END
     *****************************************************/
}