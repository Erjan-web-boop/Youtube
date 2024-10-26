package com.example.youtube.di

import com.example.youtube.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        Repository(get())
    }
}