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
        balanceParenthesis()
        subsetWithEqualSum()
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

    // eg, "()()(", "((()))())"
    fun balanceParenthesis() {
        val list1 = mutableListOf<String>()
        val input1 = "()()("
        balanceParenthesisHelper(
            input = input1,
            index = 0,
            openCount = 0,
            closeCount = 0,
            list = list1,
            output = ""
        )
        Log.e(
            LOG_TAG,
            "Inside balanceParenthesis(), input1: $input1, list1: ${list1.joinToString()}"
        )

        val max1 = list1.maxBy { it.length }.length
        Log.e(
            LOG_TAG,
            "Inside balanceParenthesis(), input1: $input1, list of max length/ min parenthesis removal: ${
                list1.filter { it.length == max1 }.joinToString()
            }"
        )

        val list2 = mutableListOf<String>()
        val input2 = "()())()"
        balanceParenthesisHelper(
            input = input2,
            index = 0,
            openCount = 0,
            closeCount = 0,
            list = list2,
            output = ""
        )
        Log.e(
            LOG_TAG,
            "Inside balanceParenthesis(), input2: $input2, list1: ${list2.joinToString()}"
        )
        val max2 = list2.maxBy { it.length }.length
        Log.e(
            LOG_TAG,
            "Inside balanceParenthesis(), input2: $input2, list of max length/ min parenthesis removal: ${
                list2.distinct().filter { it.length == max2 }.toList().joinToString()
            }"
        )
    }

    fun balanceParenthesisHelper(
        input: String,
        index: Int,
        openCount: Int,
        closeCount: Int,
        output: String,
        list: MutableList<String>
    ) {
        Log.e(
            LOG_TAG,
            "Inside balanceParenthesisHelper(), index: $index, input: $input, input size: ${input.length}, openCount: $openCount, closeCount: $closeCount, output: $output"
        )
        if (index >= input.length) {
            if (openCount == closeCount) {
                list.add(output)
            } else {
                null
            }
            return
        }

        if (input[index] == ')') {
            if (openCount > closeCount) {
                balanceParenthesisHelper(
                    input = input,
                    index = index + 1,
                    openCount = openCount,
                    closeCount = closeCount + 1,
                    output = output + input[index],
                    list = list
                )
                balanceParenthesisHelper(
                    input = input,
                    index = index + 1,
                    openCount = openCount,
                    closeCount = closeCount,
                    output = output,
                    list = list
                )

            } else {
                if (openCount == closeCount) {
                    balanceParenthesisHelper(
                        input = input,
                        index = index + 1,
                        openCount = openCount,
                        closeCount = closeCount,
                        output = output,
                        list = list
                    )
                }
            }
        } else if (input[index] == '(') {
            balanceParenthesisHelper(
                input = input,
                index = index + 1,
                openCount = openCount + 1,
                closeCount = closeCount,
                output = output + input[index],
                list = list
            )

            balanceParenthesisHelper(
                input = input,
                index = index + 1,
                openCount = openCount,
                closeCount = closeCount,
                output = output,
                list = list
            )
        } else {
            balanceParenthesisHelper(
                input = input,
                index = index + 1,
                openCount = openCount,
                closeCount = closeCount,
                output = output + input[index],
                list = list
            )
        }
    }

    // Partition Problem using Dynamic Programming
    fun subsetWithEqualSum() {
        Log.e(LOG_TAG, "Inside subsetWithEqualSum()")
        val input1 = intArrayOf(7, 3, 1, 5, 4, 8)
        val isSumEven1 = input1.sum().rem(2) == 0
        val target1 = input1.sum() / 2
        val output1 = subset(input = input1, target = target1, index = 0)
        Log.e(
            LOG_TAG,
            "Inside subsetWithEqualSum(), for input one equal partition exists ? ${isSumEven1 && output1}"
        )

        val input2 = intArrayOf(7, 3, 1, 5, 4, 7)
        val isSumEven2 = input2.sum().rem(2) == 0
        val target2 = input2.sum() / 2
        val output2 = subset(input = input2, target = target2, index = 0)
        Log.e(
            LOG_TAG,
            "Inside subsetWithEqualSum(), for input one equal partition exists ? ${isSumEven2 && output2}"
        )
    }

    fun subset(input: IntArray, target: Int, index: Int): Boolean {
        if (target == 0) {
            return true
        }


        if (index >= input.size || target < 0) {
            return false
        }

        val included = subset(input = input, target = target - input[index], index + 1)
        if (included) {
            return true
        }
        return subset(input = input, target = target, index + 1)
    }
}