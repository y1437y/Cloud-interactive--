package com.cloundinteractive.haydencodetest

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import com.cloundinteractive.haydencodetest.util.PhotoAdapter
import com.cloundinteractive.haydencodetest.util.Photos

class SecondPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)

        val actionbar = supportActionBar
        actionbar!!.title = "Second Page"
        actionbar.setBackgroundDrawable(ColorDrawable(getColor(R.color.LightGreen)))
        actionbar.setDisplayHomeAsUpEnabled(true)

        val gridView : GridView = findViewById(R.id.photo_grid)
        gridView.adapter = PhotoAdapter(this)
        gridView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ThirdPage::class.java)
            intent.putExtra("id", Photos.list[position].id)
            intent.putExtra("title", Photos.list[position].title)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}