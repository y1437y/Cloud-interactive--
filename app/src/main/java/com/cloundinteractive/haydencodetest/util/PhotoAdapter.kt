package com.cloundinteractive.haydencodetest.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cloundinteractive.haydencodetest.R

class PhotoAdapter(private val context: Context) : BaseAdapter() {
    private lateinit var imageView : ImageView
    private lateinit var txtId : TextView
    private lateinit var txtTitle : TextView

    override fun getCount(): Int {
        return Photos.list.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val convertView = LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false)
        imageView = convertView.findViewById(R.id.item_image)
        txtId = convertView.findViewById(R.id.item_id)
        txtTitle = convertView.findViewById(R.id.item_title)
        if (Photos.idThumbnailList[Photos.list[position].id] == null)
            LoadBitmap(Photos.list[position].id, imageView, Photos.list[position].thumbnailUrl).execute()
        else
            imageView.setImageBitmap(Photos.idThumbnailList[Photos.list[position].id])
        txtId.text = Photos.list[position].id.toString()
        txtTitle.text = Photos.list[position].title
        return convertView
    }

}