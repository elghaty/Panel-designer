package com.basha.paneldesigner

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

/**
 * The whole app (load calculations, cable/breaker tables, SLD drawing) lives in
 * app/src/main/assets/index.html as HTML/CSS/JS and runs fully on-device inside
 * this WebView. All project data is saved with localStorage, so it persists
 * between launches without needing an internet connection.
 */
class MainActivity : Activity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        setContentView(webView)

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true   // required for localStorage persistence
            allowFileAccess = true
            useWideViewPort = true
            loadWithOverviewMode = true
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                Toast.makeText(this@MainActivity, "خطأ في تحميل التطبيق: $description", Toast.LENGTH_LONG).show()
            }
        }

        webView.loadUrl("file:///android_asset/index.html")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
