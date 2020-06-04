package com.apipas.mynote.ui.notedetail

import androidx.lifecycle.MutableLiveData
import com.apipas.mynote.ui.base.BaseViewModel

class NoteDetailVm : BaseViewModel() {
    val content = MutableLiveData<String>()

    fun loadView(content: String) {
        this.content.value = content
    }

    fun goToList() {
        publish(
            NavigateToNoteList()
        )
    }
}