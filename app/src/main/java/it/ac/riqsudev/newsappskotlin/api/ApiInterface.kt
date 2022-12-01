package it.ac.riqsudev.newsappskotlin.api

import it.ac.riqsudev.newsappskotlin.model.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines") //endpoint for headline news
    fun getHeadlines(
        @Query("country") id: String?, //country
        @Query("apiKey") apiKey: String? //apikey
    ): Call<NewsResponse>

    @GET("top-headlines")
    fun getNewsCategory(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String?
    ): Call<NewsResponse>

    @GET("everything")
    fun getSearchNews(
        @Query("q") keyword: String?,
        @Query("language") language: String?,
        @Query("apiKey") apiKey: String?
    ): Call<NewsResponse>

}