package com.example.interviewprepration.kotlinrefresher

import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Coroutines {

    val LOG_TAG: String = Coroutines::class.java.name

    fun driverFunction() {
        testingSequence1()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun testingSequence1() {
        // Coroutines starting with launch are fire and forget blocks,
        // They don't run in any sequence, they are non-blocking
        val job1 = GlobalScope.launch(Dispatchers.IO) {
            Log.e(
                LOG_TAG,
                "Inside testingSequence1, job1-launch(), thread: ${Thread.currentThread()}"
            )
            for (count in 1..50) {
                delay(500)
                Log.e(
                    LOG_TAG,
                    "Inside for loop job1, count: $count, thread: ${Thread.currentThread()}"
                )
            }
        }

        val job2 = GlobalScope.launch(Dispatchers.IO) {
            Log.e(
                LOG_TAG,
                "Inside testingSequence1, job2-launch(), thread: ${Thread.currentThread()}"
            )
            for (count in 1..100) {
                delay(500)
                Log.e(
                    LOG_TAG,
                    "Inside for loop job2, count: $count, thread: ${Thread.currentThread()}"
                )
            }
        }


        // withContext() is blocking.
        // So withing this launch coroutine, withContext() block will run before moving to the async-await
        GlobalScope.launch(Dispatchers.IO) {

            val withContextRetValue = withContext(Dispatchers.IO) {
                for (count in 1..50) {
                    delay(500)
                    Log.e(
                        LOG_TAG,
                        "Inside for loop withContext(), count: $count, thread: ${Thread.currentThread()}"
                    )
                }
            }

            Log.e(
                LOG_TAG,
                "Inside testingCoroutines: retValue from withContext: $withContextRetValue, thread: ${Thread.currentThread()}"
            )

            val retValue = GlobalScope.async(Dispatchers.IO) {
                for (count in 1..50) {
                    delay(500)
                    Log.e(
                        LOG_TAG,
                        "Inside for loop async, count: $count, thread: ${Thread.currentThread()}"
                    )
                }
                "Returned Value"
            }

            Log.e(
                LOG_TAG,
                "Inside testingCoroutines: retValue from async: ${retValue.await()}, thread: ${Thread.currentThread()}"
            )
        }
    }
}