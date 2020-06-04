package com.apipas.mynote.ui.notelist

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.apipas.mynote.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import java.util.*

class NoteListVM : BaseViewModel() {

    val dataTest = MutableLiveData<String>().apply { value = "No Data " }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onResume() {
        launchDataLoad {
            delay(2000)
            dataTest.value = "New Data ${Date()}"
//            throw Exception("test")
        }
    }

    fun goToNoteDetail(content: String) {
        publish(NavToNoteDetail(content))
    }
}