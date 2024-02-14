package com.monstar.newsinshort.data.datasource

import com.monstar.newsinshort.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.Query

interface NewsDataSource {
    fun getNewsHeadline(country : String): Response<NewsResponse>
}