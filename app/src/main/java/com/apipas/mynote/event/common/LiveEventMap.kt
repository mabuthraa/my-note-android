package com.apipas.mynote.event.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import java.util.HashMap
import kotlin.reflect.KClass

class LiveEventMap {

    private val events = HashMap<KClass<out LiveEvent>, SingleLiveEvent<out LiveEvent>>()

    fun <T : LiveEvent> subscribe(lifecycleOwner: LifecycleOwner, eventClass: KClass<T>, eventObserver: Observer<T>) {
        var liveEvent: SingleLiveEvent<T>? = events[eventClass] as SingleLiveEvent<T>?
        if (liveEvent == null) {
            liveEvent = initUiEvent(eventClass)
        }
        liveEvent.observe(lifecycleOwner, eventObserver)
    }


    fun <T : LiveEvent> publish(event: T) {
        var liveEvent: SingleLiveEvent<T>? = events[event::class] as SingleLiveEvent<T>?
        if (liveEvent == null) {
            liveEvent = initUiEvent(event::class)
        }
        liveEvent.value = event
    }

    private fun <T : LiveEvent> initUiEvent(eventClass: KClass<out T>): SingleLiveEvent<T> {
        val liveEvent = SingleLiveEvent<T>()
        events[eventClass] = liveEvent
        return liveEvent
    }

}