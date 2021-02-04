package com.example.itunessearch.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.itunessearch.data.ItunesSongsDataModel

@Database(entities = [ItunesSongsDataModel.Results::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ItunesSongsDao(): ItunesSongsDao
}