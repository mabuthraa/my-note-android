package com.apipas.mynote.ui.notelist

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.apipas.mynote.App
import com.apipas.mynote.R
import com.apipas.mynote.data.model.Note
import com.apipas.mynote.data.remote.client.api.ApiResult
import com.apipas.mynote.data.repository.NoteRepository
import com.apipas.mynote.ui.base.BaseViewModel
import com.apipas.mynote.util.DateTimeFormat
import java.util.*

class NoteListVM(private val noteRepository: NoteRepository) : BaseViewModel() {
    val notes = ObservableArrayList<Note>()
    val lastUpdate = MutableLiveData<String>().apply { value = "" }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onResume() {
        launchDataLoad {
            notes.clear()
            //load all
            when (val res = noteRepository.getNotes()) {
                is ApiResult.Success -> notes.addAll(res.apiData)
                is ApiResult.Error -> throw res.exception
            }
            val formattedDate = DateTimeFormat.formatTimeDate(Date())
            lastUpdate.value = App.instance.getString(R.string.note_list_last_update, formattedDate)

//            //load one note
//            when (val res = noteRepository.getNote("1")) {
//                is ApiResult.Success -> notes.add(res.apiData)
//                is ApiResult.Error -> throw res.exception
//            }

            //create note
//            when (val res = noteRepository.createNote( "33333")) {
//                is ApiResult.Success -> notes.add(res.apiData)
//                is ApiResult.Error -> throw res.exception
//            }

//            //update note
//            when (val res = noteRepository.updateNote("1", "33333")) {
//                is ApiResult.Success -> notes.add(res.apiData)
//                is ApiResult.Error -> throw res.exception
//            }

            // deleteNote
//            when (val res = noteRepository.deleteNote("1")) {
//                is ApiResult.Success -> {}
//                is ApiResult.Error -> throw res.exception
//            }

        }
    }

    fun goToNoteDetail(id: String? = null, editMode: Boolean = false) {
        publish(NavToNoteDetailEvent(id, editMode))
    }
}