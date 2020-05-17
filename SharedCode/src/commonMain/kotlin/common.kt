package com.owlsdevelopers.kmpweatherdemo

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


internal expect val coroutineScope: CoroutineScope

internal abstract class Scope(
    private val dispatcher: CoroutineDispatcher
) : CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job

}


expect fun platformName(): String
fun createApplicationScreenMessage() : String {
    return "Kotlin Rocks on ${platformName()}"
}