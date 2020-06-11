package com.apipas.mynote.di

import com.apipas.mynote.data.repository.NoteRepository
import com.apipas.mynote.data.repository.NoteRepositoryImp
import org.koin.dsl.module


val repositoryModule = module {
    single<NoteRepository> { NoteRepositoryImp(get()) }
}