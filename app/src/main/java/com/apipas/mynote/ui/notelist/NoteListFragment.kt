package com.apipas.mynote.ui.notelist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apipas.mynote.R
import com.apipas.mynote.data.model.Note
import com.apipas.mynote.databinding.FragmentNoteListBinding
import com.apipas.mynote.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_note_list.*

class NoteListFragment : BaseFragment<FragmentNoteListBinding, NoteListVM>(
    R.layout.fragment_note_list,
    NoteListVM::class
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(NavToNoteDetail::class, Observer<NavToNoteDetail> {
            findNavController().navigate(
                NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(
                    it.note
                )
            )
        })
    }
}