package com.example.itunessearch.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.itunessearch.viewmodel.BaseViewModel
import com.example.itunessearch.R
import com.example.itunessearch.network.MyApi
import com.example.itunessearch.data.db.ItunesSongsDao
import com.example.itunessearch.data.ItunesSongsDataModel
import com.example.itunessearch.ui.songs.ItunesTrackListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ItunesSearchViewModel (private val itunesDAO: ItunesSongsDao): BaseViewModel() {

    var isConn:Boolean = false

    @Inject
    lateinit var itunesApi: MyApi
    var itunesListAdapter: ItunesTrackListAdapter =
        ItunesTrackListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { searchDetails(queryString) }

    private lateinit var subscription: Disposable


    val editText = MutableLiveData<String>()
    var queryString: String = ""



    fun onClickSearch(){

        searchDetails(queryString)


    }

    var queryTextWatcher: TextWatcher = object: TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            queryString = p0.toString()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    }


    private fun searchDetails(inputQuery: String) {
        if(isConn) {
            subscription =
                itunesApi.getSearchResults(inputQuery).concatMap { apiItunesList ->
                    itunesDAO.insertAll(*apiItunesList.results.toTypedArray())
                    Observable.just(apiItunesList.results) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { onRetrieveListStart() }
                    .doOnTerminate { onRetrieveListFinish() }
                    .subscribe(
                        { result ->   onRetrieveSuccess(result)},
                        { onRetrieveError() }
                    )
        }
        else{
            var conditionDB = "%$inputQuery%"
            subscription =
                Observable.fromCallable { itunesDAO.getParticular(conditionDB) }
                    .concatMap { dbItunesList ->
                        Observable.just(dbItunesList)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { onRetrieveListStart() }
                    .doOnTerminate { onRetrieveListFinish() }
                    .subscribe(
                        { result -> onRetrieveSuccess(result) },
                        { onRetrieveError() }
                    )
        }
    }

    private fun onRetrieveListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }


    private fun onRetrieveError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveSuccess(response: List<ItunesSongsDataModel.Results>) {
        itunesListAdapter.updatePostList(response)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }





}