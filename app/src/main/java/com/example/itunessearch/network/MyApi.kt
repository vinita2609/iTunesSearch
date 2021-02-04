package com.example.itunessearch.network

import com.example.itunessearch.data.ItunesSongsDataModel
import io.reactivex.Observable
import retrofit2.http.GET


interface MyApi {

    @GET("/search")
    fun getSearchResults(@retrofit2.http.Query("term") term: String): Observable<ItunesSongsDataModel.Response>
}