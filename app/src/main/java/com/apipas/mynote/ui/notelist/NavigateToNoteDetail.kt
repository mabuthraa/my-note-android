package com.apipas.mynote.ui.notelist

import com.apipas.mynote.data.model.Note
import com.apipas.mynote.event.common.LiveEvent

class NavToNoteDetail(val note: Note) : LiveEvent()