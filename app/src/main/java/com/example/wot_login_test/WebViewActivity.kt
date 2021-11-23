
package com.example.wot_login_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

private const val URL_REQUEST = "request"
private const val REDIRECT_URI = "https://developers.wargaming.net/reference/all/wot/auth/login/"
private const val TOKEN = " token"

class WebViewActivity : AppCompatActivity() {

    private var url: String? = null
    private lateinit var browser: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        browser = findViewById(R.id.webView)
        browser.webViewClient = AuthWebViewClient()


        val webSettings = browser.settings
        true.also { webSettings.javaScriptEnabled = it }
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE


        intent.extras?.let {
            url = it.getString(URL_REQUEST)
            Log.d("Log", url ?: "error")
            browser.loadUrl(url ?: "https://www.google.com/")
            //browser.loadUrl("https://api.worldoftanks.ru/wot/auth/login/?application_id=5d489c586717c2b76ade8bea16607167&redirect_uri=https%3A%2F%2Fdevelopers.wargaming.net%2Freference%2Fall%2Fwot%2Fauth%2Flogin%2F&display=popup")
        }

    }

    fun returnResult(string: String){
        val intent = Intent(this,MainActivity::class.java).apply {
            this.putExtra(
                TOKEN,string)
        }
        setResult(1,intent)
        finish()
    }

    inner class AuthWebViewClient : WebViewClient(){

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

            // если URL содержит REDIRECT_URI, значит это нам пришел код
            // и WebView не надо открывать этот URL

            if (url?.contains(REDIRECT_URI, true)!!) {
                // парсим URL, чтобы извлечь из него код

                Log.d("Log", url)
                returnResult(url)
                return true
            }
            return false
        }
    }
}