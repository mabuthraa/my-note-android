package com.apipas.mynote.ui.notelist

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.apipas.mynote.R
import com.apipas.mynote.databinding.FragmentNoteListBinding
import com.apipas.mynote.ui.base.BaseFragment

class NoteListFragment : BaseFragment<FragmentNoteListBinding, NoteListVM>(
    R.layout.fragment_note_list,
    NoteListVM::class
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        subscribe(NoteListEvent::class, Observer<NoteListEvent?> {
//            textview_first.text = "Data arrived ${Date()}"
//        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(
                NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment()
            )
        }
    }
}