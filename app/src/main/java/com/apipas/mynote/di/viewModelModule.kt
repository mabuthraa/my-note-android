package com.apipas.mynote.di

import com.apipas.mynote.ui.main.MainVM
import com.apipas.mynote.ui.notedetail.NoteDetailVm
import com.apipas.mynote.ui.notelist.NoteListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NoteListVM(get()) }
    viewModel { NoteDetailVm(get()) }
    viewModel { MainVM() }
}