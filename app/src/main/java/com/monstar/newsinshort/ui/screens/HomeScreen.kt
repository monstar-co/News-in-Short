package com.monstar.newsinshort.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.monstar.newsinshort.ui.components.EmptyStateComponent
import com.monstar.newsinshort.ui.components.LoadingSpinner
import com.monstar.newsinshort.ui.components.NewsRowComponent
import com.monstar.newsinshort.ui.theme.NewsInShortTheme
import com.monstar.newsinshort.ui.viewmodel.NewsViewModel
import com.monstar.utilities.ResourceState

const val TAG = "HomeScreen"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
) {
    val newsResponse by newsViewModel.news.collectAsState()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        100
    }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp
    ) { page: Int ->
        when (newsResponse) {
            is ResourceState.Loading -> {
                LoadingSpinner()
                Log.d(TAG, "Inside Loading")
            }

            is ResourceState.Success -> {
                val response = (newsResponse as ResourceState.Success).data

                if (response.articles.isNotEmpty()) {
                    NewsRowComponent(page, response.articles[page])
                } else {
                    EmptyStateComponent()
                }
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