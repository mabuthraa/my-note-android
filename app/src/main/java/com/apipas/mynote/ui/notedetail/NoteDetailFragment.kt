package com.apipas.mynote.ui.notedetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.apipas.mynote.R
import com.apipas.mynote.databinding.FragmentNoteDetailBinding
import com.apipas.mynote.ui.base.BaseFragment

class NoteDetailFragment : BaseFragment<FragmentNoteDetailBinding, NoteDetailVm>(
    R.layout.fragment_note_detail,
    NoteDetailVm::class
) {

    private val args: NoteDetailFragmentArgs by navArgs()

    override fun initVM() {
        super.initVM()
        viewModel.loadView(args.content)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribers()
    }

    private fun subscribers() {
        subscribe(NavigateToNoteList::class, Observer<NavigateToNoteList> {
            findNavController().navigate(R.id.action_NoteDetailFragment_to_NoteListFragment)
            //on navUp of not change to content
    //            findNavController().popBackStack()
        })
    }

}