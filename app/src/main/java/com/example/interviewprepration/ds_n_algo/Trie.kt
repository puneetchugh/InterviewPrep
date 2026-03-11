package com.example.interviewprepration.ds_n_algo

import android.util.Log


data class TrieNode(
    var children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isWord: Boolean = false,
    var isEnd: Boolean = false,
)

data class TrieNode1(
    var children: Array<TrieNode1?> = arrayOfNulls(26),
    var isWord: Boolean = false,
    var isEnd: Boolean = false,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrieNode1

        if (isWord != other.isWord) return false
        if (isEnd != other.isEnd) return false
        if (!children.contentEquals(other.children)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isWord.hashCode()
        result = 31 * result + isEnd.hashCode()
        result = 31 * result + children.contentHashCode()
        return result
    }
}

object Trie {

    val LOG_TAG = Trie::class.java.name
    fun driverFunction() {
        // Testing Trie implementation using TrieNode
        Log.e(LOG_TAG, "Inside Trie driverFunction(), testing using TrieNode")
        val root = TrieNode()
        val words = arrayOf("Puneet", "Mitchelle", "Rita", "Mike", "Marni")
        words.forEach {
            insert(it, root)
        }

        Log.e(LOG_TAG, "Searching for Puneet: ${search(words[0], root)}")
        Log.e(LOG_TAG, "Searching for Mike: ${search(words[3], root)}")
        Log.e(LOG_TAG, "Searching for Dave: ${search("Dave", root)}")

        // Testing Trie implementation using TrieNode1
        Log.e(LOG_TAG, "Inside Trie driverFunction(), testing using TrieNode1")
        val root1 = TrieNode1()
        val words1 = arrayOf("Puneet", "Mitchelle", "Rita", "Mike", "Marni")
        words.forEach {
            insert1(it, root1)
        }

        Log.e(LOG_TAG, "Searching for Puneet: ${search1(words1[0], root1)}")
        Log.e(LOG_TAG, "Searching for Mike: ${search1(words1[3], root1)}")
        Log.e(LOG_TAG, "Searching for Dave: ${search1("Dave", root1)}")
    }

    //Trie implementation using TrieNode
    //--------------------------START----------------------//
    fun insert(word: String, root: TrieNode) {
        var currentNode = root
        if (word.isBlank()) {
            return
        }

        for (eachCharacter in word) {
            if (!currentNode.children.contains(eachCharacter)) {
                currentNode.children[eachCharacter] = TrieNode()
            }
            currentNode = currentNode.children[eachCharacter]!!
        }
        currentNode.isWord = true
    }

    fun search(word: String, root: TrieNode): Boolean {
        var currentNode = root
        if (word.isBlank()) {
            return false
        }

        for (eachCharacter in word) {
            if (currentNode.children.contains(eachCharacter)) {
                currentNode = currentNode.children[eachCharacter]!!
            } else {
                return false
            }
        }
        return currentNode.isWord
    }
    //------------------------END-------------------------//


    //Trie implementation using TrieNode1
    //----------------------START-----------------------//
    fun insert1(word: String, root: TrieNode1){
        var currentNode = root

        if(word.isBlank()){
            return
        }

        for(eachCharacter in word.lowercase()){
            val index = eachCharacter - 'a'
            if(currentNode.children[index] == null){
                currentNode.children[index] = TrieNode1()
            }
            currentNode = currentNode.children[index]!!
        }
        currentNode.isWord = true
    }

    fun search1(word: String, root: TrieNode1): Boolean{
        var currentNode = root
        if (word.isBlank()){
            return false
        }

        for(eachChar in word.lowercase()){
            val currentIndexOfChar = eachChar - 'a'
            if(currentNode.children[currentIndexOfChar] == null){
                return false
            }
            currentNode = currentNode.children[currentIndexOfChar]!!
        }
        return currentNode.isWord
    }
}