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

        boogle1()
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

    // Boogle using Trie

    data class TrieNode(
        val children: Array<TrieNode?> = Array(26) { null },
        var word: String = ""
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as TrieNode

            if (!children.contentEquals(other.children)) return false
            if (word != other.word) return false

            return true
        }

        override fun hashCode(): Int {
            var result = children.contentHashCode()
            result = 31 * result + word.hashCode()
            return result
        }
    }

    fun createTrie(input: Array<String>): TrieNode {
        val words = input
        val parentNode = TrieNode()
        words.forEach {
            insertWord(word = it, parentNode = parentNode)
        }
        return parentNode
    }

    fun insertWord(word: String, parentNode: TrieNode) {

        var current = parentNode
        val wordLowerCase = word.lowercase()
        for (eachChar in wordLowerCase) {
            Log.e(
                LOG_TAG,
                "Inside boogle1, trie->insertWord(), word: $word, eachChar: $eachChar, character code: ${eachChar.code}"
            )
            val indexValue = eachChar.code - 'a'.code
            if (current.children[indexValue] == null) {
                current.children[indexValue] = TrieNode()
            }
            current = current.children[indexValue]!!
        }
        current.word = word
    }

    // not used
    fun checkWord(word: String, parentNode: TrieNode): Boolean {

        var current = parentNode
        val wordLowerCase = word.lowercase()
        for (eachChar in wordLowerCase) {
            val indexValue = eachChar.code - 'a'.code
            if (current.children[indexValue] == null) {
                return false
            }
        }
        return if (current.word == word) true else false
    }

    fun boogle1() {
        val matrix = arrayOf(
            "PAIRIJ".lowercase().toCharArray(),
            "SUTLCK".lowercase().toCharArray(),
            "NECMKL".lowercase().toCharArray(),
            "EETTAM".lowercase().toCharArray(),
            "ONSWYN".lowercase().toCharArray(),
            "YVAZPO".lowercase().toCharArray(),
        )
        val visited = Array(matrix.size) { BooleanArray(matrix[0].size) }
        val words = arrayOf("Puneet", "Avneet", "Emily", "Patrick", "Katy")
        val parentNode = createTrie(words)

        words.forEach {
            insertWord(it, parentNode)
        }

        //words.forEach {
        for (xCounter in 0 until matrix.size) {
            for (yCounter in 0 until matrix[xCounter].size) {
                if (parentNode.children[matrix[xCounter][yCounter].code - 'a'.code] != null) {
                    Log.e(LOG_TAG, "Inside boogle1(), visiting: ($xCounter, $yCounter)")
                    Log.e(LOG_TAG, "Inside boogle1(), visited: ")
                    visited.forEach {
                        Log.e(LOG_TAG, "Inside boogle1(), visited: ${it.contentToString()}")
                    }
                    if (boogleHelper(
                            matrix = matrix,
                            x = xCounter,
                            y = yCounter,
                            visited = visited,
                            trie = parentNode.children[matrix[xCounter][yCounter].code - 'a'.code]!!
                        )
                    ) {
                        Log.e(LOG_TAG, "Inside boogle1(), found word: $")
                    }
                }
            }
        }
        //}
    }

    fun boogleHelper(
        matrix: Array<CharArray>,
        x: Int,
        y: Int,
        visited: Array<BooleanArray>,
        trie: TrieNode
    ): Boolean {

        Log.e(
            LOG_TAG,
            "Inside boogle1(), boogleHelper(), visiting: ($x, $y), trie word: ${trie.word}"
        )
        if (trie.word.isNotBlank()) {
            Log.e(LOG_TAG, "Inside boogle1(), boogleHelper(), found word: ${trie.word}")
            return true
        }

        val points = listOf(
            Pair(-1, -1),
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, -1),
            Pair(0, 1),
            Pair(1, -1),
            Pair(1, 0),
            Pair(1, 1)
        )
        visited[x][y] = true
        points.forEach { point ->
            Log.e(
                LOG_TAG,
                "Inside boogle1() loop, boogleHelper(), visiting: (${x + point.first}, ${y + point.second})"
            )
            if (isValid(
                    x + point.first,
                    y + point.second,
                    matrix,
                    visited
                ) && trie.children[matrix[x + point.first][y + point.second].code - 'a'.code] != null
            ) {

                val isTrue = boogleHelper(
                    matrix = matrix,
                    x = x + point.first,
                    y = y + point.second,
                    visited = visited,
                    trie = trie.children[matrix[x + point.first][y + point.second].code - 'a'.code]!!
                )
                if (isTrue) {
                    return true
                }
            }
        }
        visited[x][y] = false
        return false
    }

    fun isValid(x: Int, y: Int, matrix: Array<CharArray>, visited: Array<BooleanArray>): Boolean {
        return x >= 0 && y >= 0 && x < matrix.size && y < matrix[0].size && !visited[x][y]
    }
}