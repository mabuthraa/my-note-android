package com.apipas.mynote.di

import com.apipas.mynote.ui.notelist.NoteListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NoteListVM() }
}