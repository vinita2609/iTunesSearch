package com.example.itunessearch.injection.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.itunessearch.data.db.AppDatabase
import com.example.itunessearch.ui.ItunesSearchViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItunesSearchViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "result").build()
            @Suppress("UNCHECKED_CAST")
            return ItunesSearchViewModel(db.ItunesSongsDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}