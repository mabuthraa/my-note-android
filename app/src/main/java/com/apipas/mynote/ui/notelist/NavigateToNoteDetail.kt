package com.apipas.mynote.ui.notelist

import com.apipas.mynote.event.common.LiveEvent

class NavToNoteDetailEvent(val idNote: String?, val editMode: Boolean = false) : LiveEvent()