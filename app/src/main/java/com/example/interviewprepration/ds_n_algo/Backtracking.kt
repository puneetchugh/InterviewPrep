package com.example.interviewprepration.ds_n_algo

import android.util.Log

object Backtracking {

    val LOG_TAG: String = Backtracking::class.java.name
    fun driverFunction() {
        nQueensWrapper(size = 5)

        allStringsPermutations(input = "abc")
        allStringsPermutations(input = "abcd")
        //allStringsPermutations(input = "puneet")

        wordBreakProblem()
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

    fun allStringsPermutations(input: String) {
        val output = mutableListOf<String>()

        allStringsPermutationsHelper(remaining = input, output = "", list = output)
        output.forEach {
            Log.e(LOG_TAG, "Inside allStringsPermutations: $it, input: $input")
        }
    }

    fun allStringsPermutationsHelper(remaining: String, output: String, list: MutableList<String>) {
        if (remaining.isEmpty()) {
            list.add(output)
        }

        for ((index, eachChar) in remaining.withIndex()) {
            allStringsPermutationsHelper(
                remaining = remaining.removeRange(index, index + 1),
                output = output + eachChar,
                list = list
            )
        }
    }

    fun wordBreakProblem() {
        Log.e(LOG_TAG, "Inside wordBreakProblem()")
        val inputWord = "pineapplepenapple"
        val dictionary = arrayOf<String>("apple", "pen", "applepen", "pine", "pineapple")
        val output = wordBreakProblemHelper(inputWord, dictionary, 0)
        Log.e(LOG_TAG, "Inside wordBreakProblem, input: $inputWord output: $output")

        val inputWord1 = "catsanddog"
        val dictionary1 = arrayOf<String>("cats", "dog", "sand", "and", "cat")
        val output1 = wordBreakProblemHelper(inputWord1, dictionary1, 0)
        Log.e(LOG_TAG, "Inside wordBreakProblem, input: $inputWord1 output: $output1")
    }

    fun wordBreakProblemHelper(
        inputWord: String,
        dictionary: Array<String>,
        index: Int
    ): Boolean {

        if (index == inputWord.length) {
            return true
        }

        for (count in index + 1..inputWord.length) {
            /*Log.e(
                LOG_TAG,
                "wordBreakProblemHelper(), index: $index, count: $count, checking substring ${
                    inputWord.substring(
                        index,
                        count
                    )
                }, is in dictionary: ${inputWord.substring(index, count) in dictionary}"
            )*/
            if (inputWord.substring(index, count) in dictionary && wordBreakProblemHelper(
                    inputWord,
                    dictionary,
                    count
                )
            ) {
                return true
            }
        }
        return false
    }
}