package com.cloundinteractive.haydencodetest

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.cloundinteractive.haydencodetest.util.OpenURLConnection
import com.cloundinteractive.haydencodetest.util.Photos

class HomePage : AppCompatActivity() {
    lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionbar = supportActionBar
        actionbar!!.title = "Home Page"
        actionbar.setBackgroundDrawable(ColorDrawable(getColor(R.color.LightGreen)))


        button = findViewById(R.id.home_button)
        button.setOnClickListener {
            button.isEnabled = false
            if (Photos.list.size == 0)
                OpenURLConnection(activity = this).execute()
            else {
                val intent = Intent(this, SecondPage::class.java)
                startActivity(intent)
            }

        }
        button.setBackgroundColor(getColor(R.color.OliveGreen))
        button.setTextColor(Color.WHITE)
    }

    override fun onResume() {
        super.onResume()
        button.isEnabled = true
    }
}

