
package com.example.wot_login_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView

private const val URL_REQUEST = "request"

class WebViewActivity : AppCompatActivity() {

    private var url: String? = null
    private lateinit var browser: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        browser = findViewById(R.id.webView)
        intent.extras?.let {
            url = it.getString(URL_REQUEST)
            Log.d("Log",url ?: "error")
            browser.loadUrl(url ?: "https://www.google.com/")

        }

    }
}