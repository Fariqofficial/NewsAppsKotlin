package it.ac.riqsudev.newsappskotlin.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.ac.riqsudev.newsappskotlin.BuildConfig
import it.ac.riqsudev.newsappskotlin.api.ApiService
import it.ac.riqsudev.newsappskotlin.model.Article
import it.ac.riqsudev.newsappskotlin.model.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {

    val listArticle = MutableLiveData<ArrayList<Article>?>()

    val country = "id"
    val apiKey = yourApiKey


    fun setNews(search: String) {
        ApiService.apiInstance
            .getSearchNews(search,country, apiKey)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        listArticle.postValue(response.body()?.modelArticle)
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.d("Failed to get data :(", t.message!!)
                }
            })
    }

    fun getNews(): MutableLiveData<ArrayList<Article>?> {
        return listArticle
    }

    companion object {
        const val yourApiKey = BuildConfig.API_KEY
    }

}