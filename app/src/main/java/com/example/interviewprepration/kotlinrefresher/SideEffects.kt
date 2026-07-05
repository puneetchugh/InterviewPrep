package com.example.interviewprepration.kotlinrefresher

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import android.util.Log
import androidx.compose.runtime.mutableStateOf


//object SideEffects {
    val LOG_TAG: String = "SideEffects"
//}


@Composable
fun LaunchedEffectComposable() {

    //var counter = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        delay(2000)
       // counter.value = 10
        //Log.e(LOG_TAG, "Inside LaunchedEffectComposable, counter: ${counter.value}")
    }

    //AnotherLaunchedEffectComposable(counter.value)

}

/*@Composable
fun AnotherLaunchedEffectComposable(input: Int) {

    Log.e(LOG_TAG, "Inside AnotherLaunchedEffectComposable, input: $input")
    LaunchedEffect(Unit) {
        delay(5000)
    }
}*/
