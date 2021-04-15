package com.a1631770.ikhwanov.myquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.a1631770.ikhwanov.myquote.databinding.ActivityListQuoteBinding
import com.a1631770.ikhwanov.myquote.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class ListQuoteActivity : AppCompatActivity() {

  private lateinit var binding: ActivityListQuoteBinding

  companion object {
    private val TAG = ListQuoteActivity::class.java.simpleName
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityListQuoteBinding.inflate(layoutInflater)
    setContentView(binding.root)

    supportActionBar?.title = "List of Quotes"

    getAllQuote()
  }

  private fun getAllQuote() {
    binding.progressBar.visibility = View.VISIBLE
    val client = AsyncHttpClient()
    val url = "https://quote-api.dicoding.dev/list"
    client.get(url, object : AsyncHttpResponseHandler() {
      override fun onSuccess(
        statusCode: Int,
        headers: Array<out Header>?,
        responseBody: ByteArray?
      ) {
        //jika koneksi sukses
        binding.progressBar.visibility = View.INVISIBLE

        val listQuotes = ArrayList<String>()

        val result = String(responseBody!!)
        Log.d(TAG, result)
        try {
          val ArrayJSON = JSONArray(result)

          for (i in 0 until ArrayJSON.length()) {
            val ObjectJSON = ArrayJSON.getJSONObject(i)
            val quote = ObjectJSON.getString("en")
            val author = ObjectJSON.getString("author")
            listQuotes.add("\n$quote\n - $author\n")
          }

          val adapter = ArrayAdapter(this@ListQuoteActivity, android.R.layout.simple_list_item_1, listQuotes)
          binding.lvQuotes.adapter = adapter

        } catch (e : Exception) {
          Toast.makeText(this@ListQuoteActivity, e.message, Toast.LENGTH_SHORT).show()
          e.printStackTrace()
        }
      }

      override fun onFailure(
        statusCode: Int,
        headers: Array<out Header>?,
        responseBody: ByteArray?,
        error: Throwable?
      ) {
        //jika koneksi gagal
        binding.progressBar.visibility = View.INVISIBLE

        val errorMessage = when (statusCode) {
          401 -> "$statusCode: Bad Request"
          403 -> "$statusCode: Forbidden"
          404 -> "$statusCode: Not Found"
          else -> "$statusCode: ${error?.message}"
        }
        Toast.makeText(this@ListQuoteActivity, errorMessage, Toast.LENGTH_SHORT).show()
      }

    })
  }
}