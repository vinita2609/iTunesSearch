package com.example.itunessearch.injection.component

import com.example.itunessearch.ui.ItunesSearchViewModel
import com.example.itunessearch.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

interface ViewModellInjector {

}
    @Singleton
    @Component(modules = [(NetworkModule::class)])
    interface ViewModelInjector {

        fun inject(itunesDetailsSearchViewModel: ItunesSearchViewModel )

        @Component.Builder
        interface Builder {
            fun build(): ViewModelInjector

            fun networkModule(networkModule: NetworkModule): Builder
        }
    }


