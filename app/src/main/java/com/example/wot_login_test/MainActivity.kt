package com.example.wot_login_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

private const val TOKEN = " token"
private const val URL_REQUEST = "request"

class MainActivity : AppCompatActivity() {

    private lateinit var button : Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById<Button>(R.id.button)
        textView = findViewById<TextView>(R.id.textView)

        val intent = Intent(this,WebViewActivity::class.java).apply {
            putExtra(
                URL_REQUEST,
               // "https://api.worldoftanks.ru/wot/auth/login/?application_id=5d489c586717c2b76ade8bea16607167&display=page&redirect_uri=https%3A%2F%2Fdevelopers.wargaming.net%2Freference%2Fall%2Fwot%2Fauth%2Flogin%2F"
             "https://api.worldoftanks.eu/wot/auth/login/?application_id=5d489c586717c2b76ade8bea16607167&redirect_uri=https%3A%2F%2Fdevelopers.wargaming.net%2Freference%2Fall%2Fwot%2Fauth%2Flogin%2F"
            )
        }
        val launch =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            it?.let{ result ->
                if(result.resultCode == 1){
                    textView.text = result.data?.extras?.getString(TOKEN)
                }

            }
        }

        button.setOnClickListener {
            launch.launch(intent)
        }
    }
}