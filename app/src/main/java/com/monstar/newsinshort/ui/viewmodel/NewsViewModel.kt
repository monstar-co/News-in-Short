package com.monstar.newsinshort.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstar.newsinshort.data.AppConstants.COUNTRY
import com.monstar.newsinshort.data.entity.NewsResponse
import com.monstar.newsinshort.ui.repository.NewsRepository
import com.monstar.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _news: MutableStateFlow<ResourceState<NewsResponse>> = MutableStateFlow(ResourceState.Loading())
    val news: StateFlow<ResourceState<NewsResponse>> = _news

    init {
        getNews(COUNTRY)
    }

 private fun getNews(country: String){
     viewModelScope.launch(Dispatchers.IO) {
         newsRepository.getNewsHeadline(country)
             .collectLatest { newsResponse ->
                _news.value = newsResponse
             }
     }
 }



    companion object {
        const val TAG = "NewsViewmodel"
    }
}