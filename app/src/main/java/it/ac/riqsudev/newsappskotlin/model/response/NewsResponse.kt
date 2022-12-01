package it.ac.riqsudev.newsappskotlin.model.response

import com.google.gson.annotations.SerializedName
import it.ac.riqsudev.newsappskotlin.model.Article

data class NewsResponse (

    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResult")
    val totalResult: Int = 0,

    @SerializedName("articles")
    val modelArticle: ArrayList<Article>? = null

)