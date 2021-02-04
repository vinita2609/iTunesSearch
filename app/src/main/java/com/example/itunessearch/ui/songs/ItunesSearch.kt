package com.example.itunessearch.ui.songs

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.itunessearch.R
import com.example.itunessearch.databinding.ActivityMainBinding
import com.example.itunessearch.injection.module.ViewModelFactory
import com.example.itunessearch.ui.ItunesSearchViewModel
import com.example.itunessearch.util.exceptions.hideKeyboard
import com.google.android.material.snackbar.Snackbar

class ItunesSearch : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ItunesSearchViewModel

    private var errorSnackbar: Snackbar? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.hideKeyboard()

        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true






        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(this)
        )
            .get(ItunesSearchViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })


        if (isConnected) viewModel.isConn = isConnected
        else
            viewModel.isConn = isConnected

        binding.viewModel = viewModel



    }


    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}