package com.apipas.mynote.ui.notelist

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.apipas.mynote.data.model.Note
import com.apipas.mynote.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import java.util.*

class NoteListVM : BaseViewModel() {
    val notes = ObservableArrayList<Note>()
    val dataTest = MutableLiveData<String>().apply { value = "No Data " }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onResume() {
        launchDataLoad {
            delay(2000)

            val arr = Array<Note>(100) { i -> Note("title #$i", "content #$i") }
            val arrayList = ArrayList<Note>(arr.toList())
            notes.addAll(arrayList)
            dataTest.value = "New Data ${Date()}"
//            throw Exception("test")
        }
    }

    fun goToNoteDetail(note: Note) {
        publish(NavToNoteDetail(note))
    }
}