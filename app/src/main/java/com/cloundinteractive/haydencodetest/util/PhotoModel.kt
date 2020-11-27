package com.cloundinteractive.haydencodetest.util

import android.graphics.Bitmap

data class PhotoModel (
    val albumId : Int,
    val id : Int,
    val title : String,
    val url : String,
    val thumbnailUrl : String
)

object Photos {
    val list = ArrayList<PhotoModel>()
    val idThumbnailList = HashMap<Int, Bitmap>()
}