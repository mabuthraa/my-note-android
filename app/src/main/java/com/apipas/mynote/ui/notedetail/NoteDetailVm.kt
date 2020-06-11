package com.apipas.mynote.ui.notedetail

import com.apipas.mynote.data.NonNullMutableLiveData
import com.apipas.mynote.data.model.Note
import com.apipas.mynote.data.remote.client.api.ApiResult
import com.apipas.mynote.data.repository.NoteRepository
import com.apipas.mynote.ui.base.BaseViewModel
import kotlinx.coroutines.delay

class NoteDetailVm(private val noteRepository: NoteRepository) : BaseViewModel() {

    private var initialTitle: String? = null
    val note = NonNullMutableLiveData<Note>(Note())
    val editMode = NonNullMutableLiveData(false)

    init {
        editMode.observeForever {
            publish(EditMenuEvent(it))
        }
    }

    fun loadView(noteId: String?, editMode: Boolean) {
        if(note.value.id !=null || !note.value.title.isNullOrEmpty()) {
            return
        }
        launchDataLoad {
            this.editMode.value = editMode
            if (noteId != null) {
                when (val res = noteRepository.getNote(noteId)) {
                    is ApiResult.Success -> {
                        note.value = res.apiData
                        initialTitle = res.apiData.title
                    }
                    is ApiResult.Error -> throw res.exception
                }

            } else {
                note.value = Note()
            }
        }
    }

    fun goToList(forceUpdate: Boolean) {
        publish(
            NavigateToNoteListEvent(forceUpdate)
        )
    }

    fun toEditMode(enableEditMode: Boolean) {
        editMode.value = enableEditMode
    }

    fun delete() {
        if (note.value.id == null) {
            goToList(false) //  new note which has never been sent to BE . -> just go back
            return
        }

        launchDataLoad {
            when (val res = noteRepository.deleteNote(note.value.id!!)) {
                is ApiResult.Success -> goToList(true)
                is ApiResult.Error -> throw res.exception
            }
        }
    }

    fun onBackPressed() {
        when {
            // empty for existing note -> delete
            note.value.title.isNullOrEmpty() -> delete()
            // no change -> goToList(false)
            note.value.title == initialTitle -> goToList(false) // no change
            // nonEmpty title for new note -> create goToList(true)
            note.value.id == null -> createNote(note)
            // nonEmpty title for existing note -> update goToList(true)
            else -> updateNote(note)
        }
    }

    private fun updateNote(note: NonNullMutableLiveData<Note>) {
        launchDataLoad {
            when (val res = noteRepository.updateNote(note.value.id!!, note.value.title!!)) {
                is ApiResult.Success -> goToList(true)
                is ApiResult.Error -> throw res.exception
            }
        }
    }

    private fun createNote(note: NonNullMutableLiveData<Note>) {
        launchDataLoad {
            when (val res = noteRepository.createNote(note.value.title!!)) {
                is ApiResult.Success -> goToList(true)
                is ApiResult.Error -> throw res.exception
            }
        }
    }
}