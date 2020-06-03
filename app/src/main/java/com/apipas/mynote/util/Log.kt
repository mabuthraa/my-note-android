package com.apipas.mynote.util

import com.apipas.mynote.BuildConfig

object Log {

    fun d(text: String) {
        if (BuildConfig.DEBUG) {
            val logStrings = createLogStrings(text)
            android.util.Log.d(logStrings[0], logStrings[1])
        }
    }

    fun e(text: String) {
        if (BuildConfig.DEBUG) {
            val logStrings = createLogStrings(text)
            android.util.Log.e(logStrings[0], logStrings[1])
        }
    }

    fun e(text: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            val logStrings = createLogStrings(text)
            android.util.Log.e(logStrings[0], logStrings[1])
            throwable.printStackTrace()
        }
    }

    fun i(text: String) {
        if (BuildConfig.DEBUG) {
            val logStrings = createLogStrings(text)
            android.util.Log.i(logStrings[0], logStrings[1])
        }
    }

    fun printStackTrace(e: Throwable) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        }
    }

    private fun createLogStrings(text: String): Array<String> {
        val ste = Thread.currentThread().stackTrace

        val line = "(" + (ste[4].fileName + ":" + ste[4].lineNumber + ")")
        val method = ste[4].methodName + ": " + text
        return arrayOf(line,method)
    }
}
