package com.monstar.newsinshort.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.monstar.newsinshort.ui.components.LoadingSpinner
import com.monstar.newsinshort.ui.components.NewsList
import com.monstar.newsinshort.ui.theme.NewsInShortTheme
import com.monstar.newsinshort.ui.viewmodel.NewsViewModel
import com.monstar.utilities.ResourceState

const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
) {

    val newsResponse by newsViewModel.news.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when (newsResponse) {
            is ResourceState.Loading -> {
                LoadingSpinner()
                Log.d(TAG, "Inside Loading")
            }

            is ResourceState.Success -> {
                val response = (newsResponse as ResourceState.Success).data
                Log.d(TAG, "Inside Success ${response.status} ${response.totalResults}")
                NewsList(response)
            }

            is ResourceState.Error -> {
                val error = (newsResponse as ResourceState.Error)
                Log.d(TAG, "Inside Error $error")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    NewsInShortTheme {
        HomeScreen()
    }
}