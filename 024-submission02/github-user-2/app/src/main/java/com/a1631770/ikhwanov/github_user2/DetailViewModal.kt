package com.a1631770.ikhwanov.github_user2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DetailViewModal : ViewModel() {
  var listUser = MutableLiveData<ArrayList<UserItem>>()

  fun setDataUserDetail(username: String) {
    val listDetailItems = ArrayList<UserItem>()

    val apiKey = "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"
    val url = "https://api.github.com/users/${username}"

    val client = AsyncHttpClient()
    client.addHeader("Authorization", "token $apiKey")
    client.addHeader("User-Agent", "request")
    client.get(url, object : AsyncHttpResponseHandler() {
      override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
        try {
          val result = String(responseBody!!)
          Log.d(DetailActivity::class.java.simpleName, result)
          val responseObject = JSONObject(result)

          val usersList = UserItem()

          usersList.avatar = responseObject.getString("avatar_url")
          usersList.username = responseObject.getString("login")
          usersList.repository = responseObject.getInt("public_repos")
          usersList.follower = responseObject.getInt("followers")
          usersList.following = responseObject.getInt("following")
          usersList.location = responseObject.getString("location")
          usersList.company = responseObject.getString("company")

          listDetailItems.add(usersList)

          listUser.postValue(listDetailItems)

        } catch (e: Exception) {
          Log.d("Exception", e.message.toString())
        }
      }

      override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
        Log.d("onFailure", error?.message.toString())
      }
    })
  }

  fun getDataUserDetail(): MutableLiveData<ArrayList<UserItem>> {
    return listUser
  }
}
