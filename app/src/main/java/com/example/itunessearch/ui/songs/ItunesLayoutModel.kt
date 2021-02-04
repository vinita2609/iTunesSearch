package com.example.itunessearch.ui.songs

import androidx.lifecycle.MutableLiveData
import com.example.itunessearch.viewmodel.BaseViewModel
import com.example.itunessearch.data.ItunesSongsDataModel

class ItunesLayoutModel : BaseViewModel() {
    private val trackTitle = MutableLiveData<String>()
    private val artist = MutableLiveData<String>()
    private val collection = MutableLiveData<String>()
    private val url = MutableLiveData<String>()


    fun bind(result: ItunesSongsDataModel.Results){
        trackTitle.value = result.trackName
        artist.value = result.artistName
        collection.value = result.collectionName
        url.value = result.artworkUrl100
    }

    fun getTrackTitle(): MutableLiveData<String> {
        return trackTitle
    }

    fun getArtist(): MutableLiveData<String> {
        return artist
    }


    fun getCollection(): MutableLiveData<String> {
        return collection
    }

    fun getUrl(): MutableLiveData<String> {
        return url
    }
}