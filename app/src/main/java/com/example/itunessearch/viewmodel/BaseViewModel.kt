package com.example.itunessearch.viewmodel

import androidx.lifecycle.ViewModel
import com.example.itunessearch.injection.component.DaggerViewModelInjector
import com.example.itunessearch.injection.component.ViewModelInjector
import com.example.itunessearch.ui.ItunesSearchViewModel
import com.example.itunessearch.injection.module.NetworkModule

abstract class BaseViewModel:ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ItunesSearchViewModel -> injector.inject(this)
        }
    }

}