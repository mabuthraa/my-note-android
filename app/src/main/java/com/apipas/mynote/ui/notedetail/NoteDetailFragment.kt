package com.apipas.mynote.ui.notedetail

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.apipas.mynote.R
import com.apipas.mynote.databinding.FragmentNoteDetailBinding
import com.apipas.mynote.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_note_detail.*


class NoteDetailFragment : BaseFragment<FragmentNoteDetailBinding, NoteDetailVm>(
    R.layout.fragment_note_detail,
    NoteDetailVm::class
) {

    private val args: NoteDetailFragmentArgs by navArgs()
    private var menu: Menu? = null

    override fun initVM() {
        super.initVM()
        viewModel.loadView(args.noteId, args.editMode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribers()
        listeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_discard -> {
                viewModel.goToList(false)
                true
            }

            R.id.action_delete -> {
                viewModel.delete()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribers() {
        subscribe(NavigateToNoteListEvent::class, Observer<NavigateToNoteListEvent> {
            if (it.forceUpdate) {
                findNavController().navigate(R.id.action_NoteDetailFragment_to_NoteListFragment)
            } else {
                findNavController().popBackStack()
            }
        })

        subscribe(EditMenuEvent::class, Observer<EditMenuEvent> {
            this.menu?.findItem(R.id.action_discard)?.isVisible = it.showEditMenu
            if (it.showEditMenu) {
                note_et.requestFocus()
                val inputMethodManager =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(note_et, InputMethodManager.SHOW_IMPLICIT)


            }
        })
    }

    private fun listeners() {

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.onBackPressed()
                }
            })
    }
}