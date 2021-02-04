package com.example.itunessearch.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.itunessearch.data.ItunesSongsDataModel

@Dao
interface ItunesSongsDao {

    @get:Query("SELECT * FROM results")
    val all: List<ItunesSongsDataModel.Results>

    @Insert
    fun insertAll(vararg results: ItunesSongsDataModel.Results)

    @Query("select * from results where artistName like :arg0")
    fun getParticular(arg0: String): List<ItunesSongsDataModel.Results>



}