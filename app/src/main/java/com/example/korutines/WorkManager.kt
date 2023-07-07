package com.example.korutines

import kotlinx.coroutines.*

class WorkManager {

    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + job)

    fun doWork1(): Job = scope.launch { /* do work */ } // (2)

    fun doWork2(): Job = scope.launch { /* do work */ } // (2)

    fun cancelAllWork() {
        scope.coroutineContext.cancelChildren()         // (1)
    }
}