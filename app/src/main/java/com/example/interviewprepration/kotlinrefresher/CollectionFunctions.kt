package com.example.interviewprepration.kotlinrefresher

import android.util.Log

object CollectionFunctions {

    val LOG_TAG = CollectionFunctions::class.java.name
    fun driverFunction() {
        createMaps()
        createMapFromList()
        createLists()
        createArray()
    }

    fun createMaps() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        val map2 = mapOf(Pair("a", 1), Pair("b", 2), Pair("c", 3))

        Log.e(LOG_TAG, "Inside createMaps: map: $map, map2: $map2")

        val map3 = mutableMapOf<String, Int>()
        map3["one"] = 1
        map3["two"] = 2
        map3["three"] = 3
        Log.e(LOG_TAG, "Inside createMaps: map3: $map3")

    }

    fun createLists() {
        val list = listOf(1, 2, 3)
        val list1 = listOf<String>("Puneet", "Mike", "Steph", "Marni", "Dave", "John", "Rajesh")

    }

    fun createArray() {
        val array = arrayOf(1, 2, 3)

        val array2 = Array<Int>(5) { it * it }
        Log.e(LOG_TAG, "Inside createArray: array2: ${array2.contentToString()}")
    }

    fun createMapFromList() {
        val list = listOf(1, 2, 3)
        val map = list.associate { it to it * it }
        Log.e(LOG_TAG, "Inside createMapFromList: map: $map")

        val names =
            listOf<String>("Puneet", "Mike", "Steph", "Marni", "Dave", "John", "Rajesh", "Puneet", "PuneetC", "PuneetChugh")

        //This will not preserve multiple values starting with the first character,
        //it will only preserve the last value encountered
        val map2 = names.associateBy { it.first() }
        Log.e(LOG_TAG, "Inside createMapFromList: initial list: ${names}, map2: $map2")

        //val map3 = names.associate { it to it.length }
        val map3 = names.associate { Pair(it, it.length) }
        Log.e(LOG_TAG, "Inside createMapFromList: map3: $map3")

        val map4 = names.groupBy { it.first() }
        Log.e(LOG_TAG, "Inside createMapFromList: map4: $map4")

        val map5 = names.groupingBy { it }.eachCount()
        Log.e(LOG_TAG, "Inside createMapFromList: map5: $map5")

        val mutableMap = mutableMapOf<String, MutableList<Int>>()
        for (name in names) {
            mutableMap.getOrPut(name) { mutableListOf() }.add(name.length)
        }
        Log.e(LOG_TAG, "Inside createMapFromList: mutableMap: $mutableMap")
        map.forEach { entry ->
            Log.e(LOG_TAG, "forEach -> Inside createMapFromList, iterating over: (${entry.key}, ${entry.value})")
        }
    }
}