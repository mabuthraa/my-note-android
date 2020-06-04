package com.apipas.mynote.ui.base


import androidx.lifecycle.*
import com.apipas.mynote.App
import com.apipas.mynote.BuildConfig
import com.apipas.mynote.event.common.LiveEvent
import com.apipas.mynote.event.common.LiveEventMap
import com.apipas.mynote.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val liveEventMap = LiveEventMap()

    fun <T : LiveEvent> subscribe(
        lifecycleOwner: LifecycleOwner,
        eventClass: KClass<T>,
        eventObserver: Observer<T>
    ) {
        liveEventMap.subscribe(lifecycleOwner, eventClass, eventObserver)
    }

    fun <T : LiveEvent> publish(event: T) {
        liveEventMap.publish(event)
    }


    /**
     *  [loading] to display a loading
     */
    val loading = MutableLiveData<Boolean>().apply { value = false }

    /**
     * [snackbar] to display a msg.
     */
    val snackbar = MutableLiveData<String>()

    fun resetSnackBar() {
        snackbar.value = null
    }

    /**
     * Helper function to call a data load function with a loading, errors will trigger a
     * snackbar.
     *
     * By marking `block` as `suspend` this creates a suspend lambda which can call suspend
     * functions.
     *
     * @param block lambda to actually load data. It is called in the viewModelScope. Before calling the
     *              lambda the loading loading will display, after completion or error the
     *              loading will stop
     */
    open fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                loading.value = true
                block()
            } catch (error: Exception) { //can be override with custom Exception for better handling
                onException(error)

            } finally {
                loading.value = false
            }
        }
    }

    private fun handleError(error: Exception) {
        onException(error)
    }

    protected open fun onException(error: Exception) {

        Log.d(":Error:${error.message}") // be aware sometimes there logs dont appear in console
        val unknownErrorMsg =
            App.instance.getString(com.apipas.mynote.R.string.global_unknown_error)
        val msg: String = if (!BuildConfig.DEBUG) {
            //debug
            unknownErrorMsg
        } else {
            //debug
            unknownErrorMsg + error.message
        }
        snackbar.value = msg
    }
}