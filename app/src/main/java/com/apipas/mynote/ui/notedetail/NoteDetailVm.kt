package com.apipas.mynote.ui.notedetail

import androidx.lifecycle.MutableLiveData
import com.apipas.mynote.data.model.Note
import com.apipas.mynote.ui.base.BaseViewModel

class NoteDetailVm : BaseViewModel() {
    val content = MutableLiveData<String>()
    val title = MutableLiveData<String>()

    fun loadView(note: Note) {
        with(this) {
            content.value = note.content
            title.value = note.content
        }
    }

    fun goToList() {
        publish(
            NavigateToNoteList()
        )
    }
}