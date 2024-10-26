package com.example.youtube.di

import com.example.youtube.viewmodel.YouTubeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        YouTubeViewModel(get())
    }

}