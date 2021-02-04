package com.example.itunessearch.data

import androidx.room.Entity
import androidx.room.PrimaryKey

class ItunesSongsDataModel {


    data class Response(

        val results: List<Results>
    )


    @Entity
    data class Results(
        val artistName: String,
        val collectionName: String,
        val trackName: String,
        val artistId: Int,
        @field:PrimaryKey
        val trackId: Int,
        val artworkUrl100: String
    )

}