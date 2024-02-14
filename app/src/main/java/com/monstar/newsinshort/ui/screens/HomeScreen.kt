package com.monstar.newsinshort.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.monstar.newsinshort.ui.theme.NewsInShortTheme

@Composable
fun HomeScreen(){
    Surface(
        modifier = Modifier.fillMaxSize()
    ){

    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    NewsInShortTheme {
        HomeScreen()
    }
}