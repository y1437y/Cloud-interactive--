package com.cloundinteractive.haydencodetest.util

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import com.cloundinteractive.haydencodetest.SecondPage
import com.cloundinteractive.haydencodetest.ThirdPage
import org.json.JSONArray
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class OpenURLConnection(val activity: Activity) : AsyncTask<Unit, Unit, String>() {
    private var jsonstring : String? = null

    override fun doInBackground(vararg params: Unit?): String? {
        val url = URL("https://jsonplaceholder.typicode.com/photos")
        val urlConnection = url.openConnection() as HttpURLConnection
        if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
            try {
                val stream = BufferedInputStream(urlConnection.inputStream)
                val bufferedReader = BufferedReader(InputStreamReader(stream))
                val stringBuilder = StringBuilder()
                bufferedReader.forEachLine { stringBuilder.append(it) }
                jsonstring = stringBuilder.toString()
                val jsonArray = JSONArray(jsonstring)
                for (x in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(x)
                    Photos.list.add(PhotoModel(
                        obj.getInt("albumId"),
                        obj.getInt("id"),
                        obj.getString("title"),
                        obj.getString("url"),
                        obj.getString("thumbnailUrl")
                    ))
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        } else {
            println("ERROR ${urlConnection.responseCode}")
        }
        return jsonstring
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != null) {
            val intent = Intent(activity, SecondPage::class.java)
            activity.startActivity(intent)
        }
    }
}

class LoadBitmap(private val id: Int, private val imageView: ImageView, private val str : String) : AsyncTask<Unit, Unit, Bitmap?>() {
    override fun doInBackground(vararg params: Unit?): Bitmap? {
        val thumbUrl = URL(str)
        var bitmap : Bitmap? = null
        val urlConnection : HttpURLConnection = thumbUrl.openConnection() as HttpURLConnection
        urlConnection.setRequestProperty(
            "User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36")
        val code = urlConnection.responseCode
        if (code == HttpURLConnection.HTTP_OK) {
            try {
                val inputStream = urlConnection.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e : java.lang.Exception) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        }

        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        if (result != null) {
            Photos.idThumbnailList[id] = result
            imageView.setImageBitmap(result)
        }

    }
}