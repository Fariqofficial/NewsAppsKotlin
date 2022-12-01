package it.ac.riqsudev.newsappskotlin.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import it.ac.riqsudev.newsappskotlin.R
import it.ac.riqsudev.newsappskotlin.databinding.ActivityDetailBinding
import it.ac.riqsudev.newsappskotlin.model.Article

private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var article: Article? = null
    private lateinit var webView: WebView

    private var tvTitle: String? = null
    private var tvUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        article = intent.getParcelableExtra(DETAIL_NEWS)

        if (article!=null) {
            tvTitle = article?.title
            tvUrl = article?.url
        }
        initActionBar()
        initWebview()
    }

    private fun initActionBar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        //Back button
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)

        //Title and subtitle
        binding.apply {
            newsTitle.text = tvTitle
            newsSubtitle.text = tvUrl
        }
    }

    private fun initWebview() {
        webView = binding.webview
        webView.settings.javaScriptEnabled = true
        tvUrl?.let { webView.loadUrl(it) }

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, newUrl: String): Boolean {
                view.loadUrl(newUrl)
                binding.progressBarLinear.visibility = View.VISIBLE
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBarLinear.visibility = View.GONE
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }
}