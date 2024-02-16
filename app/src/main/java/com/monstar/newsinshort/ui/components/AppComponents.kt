package com.monstar.newsinshort.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.monstar.newsinshort.R
import com.monstar.newsinshort.data.entity.Article
import com.monstar.newsinshort.data.entity.NewsResponse
import com.monstar.newsinshort.data.entity.Source
import com.monstar.newsinshort.ui.theme.Purple40

@Composable
fun LoadingSpinner() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = Purple40
        )
    }

}

@Composable
fun NewsList(response: NewsResponse, page: Int) {
    LazyColumn {
        items(response.articles) { article ->
            NormalTextComponent(textValue = article.title ?: "NA")
        }
    }
}

@Composable
fun NewsRowComponent(page: Int, article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            model = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_placeholder_image),
            error = painterResource(id = R.drawable.ic_placeholder_image),

            )

        Spacer(modifier = Modifier.size(20.dp))

        HeadingTextComponent(textValue = article.title ?: "")

        Spacer(modifier = Modifier.size(10.dp))

        NormalTextComponent(textValue = article.content ?: "")

        Spacer(modifier = Modifier.weight(1f))

        AuthorDetailsComponent(authorName = article.author, source = article.source?.name)
    }
}


@Composable
fun NormalTextComponent(textValue: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            color = Purple40
        )
    )
}

@Composable
fun HeadingTextComponent(textValue: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
    )
}

@Composable
fun AuthorDetailsComponent(authorName: String?, source: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 24.dp)
    ) {
        if (authorName != null) {
            Text(text = authorName)
        }
        Spacer(modifier = Modifier.weight(1f))
        if (source != null) {
            Text(text = source)
        }
    }
}

//@Preview
//@Composable
//fun NewsRowComponentPreview() {
//    val article = Article(
//        source = Source(
//            id = "google-news",
//            name = "Google News"
//        ),
//        author = "Vanguard",
//        title = "US aviation lawyer speaks out on crash that killed Wigwe, others - Vanguard",
//        description = null,
//        url = "https = //news.google.com/rss/articles/CBMiZGh0dHBzOi8vd3d3LnZhbmd1YXJkbmdyLmNvbS8yMDI0LzAyL3VzLWF2aWF0aW9uLWxhd3llci1zcGVha3Mtb3V0LW9uLWNyYXNoLXRoYXQta2lsbGVkLXdpZ3dlLW90aGVycy_SAQA?oc=5",
//        urlToImage = null,
//        publishedAt = "2024-02-15T05:10:40Z",
//        content = null
//    )
//    NewsRowComponent(page = 0, article = article)
//}

