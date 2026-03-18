package com.example.interviewprepration.ds_n_algo

import android.util.Log

object WebScrapping {

    val LOG_TAG: String = WebScrapping::class.java.name
    fun driverFunction() {

        val input = listOf(
            "www.example1.com" to "I'm a website, I'm the first website, and I'm the first. Did you hear, I'm the first",
            "www.example2.com" to "I'm a website, I'm the second website, and I'm the second. Did you hear, I'm the second from the top"
        )

        val output = webScrapping(input)
        Log.e(LOG_TAG, "Inside driverFunction webScrapping, output: $output")
    }

    // input: List of Pairs of Website to content

    // output: Map of words to List of pair of websites
    // that contain that word with the count of occurrences
    fun webScrapping(input: List<Pair<String, String>>): Map<String, MutableList<Pair<String, Int>>> {

        val mutableMapOutput: MutableMap<String, MutableList<Pair<String, Int>>> = mutableMapOf()

        for (pair in input) {
            val wordsCount = pair.second.split(" ", ".", ",", ":", ";", "?", "!", "\n").toList()
                .groupingBy { it }.eachCount()

            for ((word, count) in wordsCount) {
                mutableMapOutput.getOrPut(word) { mutableListOf<Pair<String, Int>>() }
                    .add(Pair(pair.first, count))
            }
        }
        return mutableMapOutput
    }
}