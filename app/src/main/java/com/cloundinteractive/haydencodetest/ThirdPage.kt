package com.cloundinteractive.haydencodetest

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.cloundinteractive.haydencodetest.util.Photos

class ThirdPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_page)

        val actionbar = supportActionBar
        actionbar!!.title = "Third Page"
        actionbar.setBackgroundDrawable(ColorDrawable(getColor(R.color.LightGreen)))
        actionbar.setDisplayHomeAsUpEnabled(true)

        val image : ImageView = findViewById(R.id.third_page_image)
        val id : TextView = findViewById(R.id.third_page_id)
        val title : TextView = findViewById(R.id.third_page_title)

        val idNum : Int = intent.getIntExtra("id", 1)
        val titleStr : String? = intent.getStringExtra("title")


        image.setImageBitmap(Photos.idThumbnailList[idNum])
        id.text = idNum.toString()
        title.text = titleStr
        image.setOnClickListener { onBackPressed() }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}