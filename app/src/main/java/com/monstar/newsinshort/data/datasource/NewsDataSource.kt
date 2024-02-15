package com.monstar.newsinshort.data.datasource

import com.monstar.newsinshort.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {
    suspend fun getNewsHeadline(country: String): Response<NewsResponse>
}