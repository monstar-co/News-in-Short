package com.monstar.newsinshort.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(): ViewModel() {

    val value ="value"
    init {
        Log.d(TAG,"Init block of NewsViewmodel" )
    }

    fun printSomething() {
        println("do something")
    }

    companion object {
        const val TAG = "NewsViewmodel"
    }
}