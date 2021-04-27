package com.a1631770.ikhwanov.github_user2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MainViewModal : ViewModel() {

    val listUser = MutableLiveData<ArrayList<UserItem>>()

    fun setUsers(username: String) {
        val listItems = ArrayList<UserItem>()

        val apiKey = "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"
        val url = "https://api.github.com/search/users?q=${username}"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token $apiKey")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    Log.d(MainActivity::class.java.simpleName, result)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("items")

                    for (i in 0 until list.length()) {
                        val users = list.getJSONObject(i)
                        val usersList = UserItem()

                        usersList.username = users.getString("login")
                        usersList.gitUrl = users.getString("html_url")
                        usersList.avatar = users.getString("avatar_url")

                        listItems.add(usersList)
                    }

                    listUser.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }

        })
    }

    fun getUsers() : LiveData<ArrayList<UserItem>> {
        return listUser
    }
}