package com.a1631770.ikhwanov.github_user2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.a1631770.ikhwanov.github_user2.databinding.FragmentFollowingBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.util.ArrayList

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: ListUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = activity?.intent?.getParcelableExtra<UserItem>(DetailActivity.EXTRA_USER)
        val username = intent?.username

        showRecyclerView()
        getDataUserFollowing(username)
    }

    private fun getDataUserFollowing(username: String?) {
        val listItem = ArrayList<UserItem>()

        val apiKey = "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"
        val url = "https://api.github.com/users/${username}/following"

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
                    Log.d(FollowingFragment::class.java.simpleName, result)
                    val list = JSONArray(result)

                    for (i in 0 until list.length()) {
                        val users = list.getJSONObject(i)
                        val userList = UserItem()

                        userList.username = users.getString("login")
                        userList.gitUrl = users.getString("html_url")
                        userList.avatar = users.getString("avatar_url")

                        listItem.add(userList)
                    }

                    adapter.setData(listItem)

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

    private fun showRecyclerView() {
        adapter = ListUserAdapter()
        adapter.notifyDataSetChanged()

        binding?.recyclerViewFollowing?.layoutManager = LinearLayoutManager(activity)
        binding?.recyclerViewFollowing?.adapter = adapter

        adapter.setOnItemClickCallback( object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserItem) {
                showSelectUser(data)
            }

        })
    }

    private fun showSelectUser(data: UserItem) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER, data)
        startActivity(intent)

        Toast.makeText(activity, data.username, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}