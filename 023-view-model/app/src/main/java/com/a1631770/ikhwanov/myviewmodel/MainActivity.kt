package com.a1631770.ikhwanov.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.a1631770.ikhwanov.myviewmodel.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception
import java.text.DecimalFormat
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

  private lateinit var adapter: WeatherAdapter
  private lateinit var mainViewModel: MainViewModel
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

    adapter = WeatherAdapter()
    adapter.notifyDataSetChanged()

    binding.recyclerView.layoutManager = LinearLayoutManager(this)
    binding.recyclerView.adapter = adapter

    binding.btnCity.setOnClickListener {
      val city = binding.edtCity.text.toString()
      if (city.isEmpty()) return@setOnClickListener
      showLoading(true)
//      setWeather(city)
      mainViewModel.setWeather(city)
    }

    mainViewModel.getWeather().observe(this, {weatherItems ->
      if(weatherItems != null) {
        adapter.setData(weatherItems)
        showLoading(false)
      }
    })
  }

//  private fun setWeather(city: String) {
//    val listItems = ArrayList<WeatherItems>()
//
//    val apiKey = "a24c261edb26f53991dcb9bd6224dacb"
//    val url = "https://api.openweathermap.org/data/2.5/group?id=${city}&units=metric%appid=${apiKey}"
//
//    val client = AsyncHttpClient()
//    client.get(url, object : AsyncHttpResponseHandler() {
//      override fun onSuccess(
//        statusCode: Int,
//        headers: Array<out Header>?,
//        responseBody: ByteArray?
//      ) {
//        try {
//          //parsing json
//          val result = String(responseBody!!)
//          val responseObject = JSONObject(result)
//          val list = responseObject.getJSONArray("list")
//
//          for (i in 0 until list.length()) {
//            val weather = list.getJSONObject(i)
//            val weatherItems = WeatherItems()
//            weatherItems.id = weather.getInt("id")
//            weatherItems.name = weather.getString("name")
//            weatherItems.currentWeather =
//              weather.getJSONArray("weather").getJSONObject(0).getString("main")
//            weatherItems.description =
//              weather.getJSONArray("weather").getJSONObject(0).getString("description")
//            val tempInKelvin = weather.getJSONObject("main").getDouble("temp")
//            val tempInCelcius = tempInKelvin - 273
//            weatherItems.temperature = DecimalFormat("##.##").format(tempInCelcius)
//            listItems.add(weatherItems)
//          }
//
//          //set data ke adapter
//          adapter.setData(listItems)
//          showLoading(false)
//        } catch (e: Exception) {
//          Log.d("Exception", e.message.toString())
//        }
//      }
//
//      override fun onFailure(
//        statusCode: Int,
//        headers: Array<out Header>?,
//        responseBody: ByteArray?,
//        error: Throwable?
//      ) {
//        Log.d("onFailure: ", error?.message.toString())
//      }
//
//    })
//  }

  private fun showLoading(state: Boolean) {
    if (state) {
      binding.progressBar.visibility = View.VISIBLE
    }else {
      binding.progressBar.visibility = View.GONE
    }
  }
}