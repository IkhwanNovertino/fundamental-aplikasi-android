package com.a1631770.ikhwanov.github_user2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.a1631770.ikhwanov.github_user2.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var adapter: ListUserAdapter
  private lateinit var viewModal: MainViewModal

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    supportActionBar?.title = "Github User"

    viewModal = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModal::class.java)

    binding.imgButton.setOnClickListener {
      val username = binding.inputUsername.text.toString()
      if (username.isEmpty()) return@setOnClickListener
      showLoading(true)
      viewModal.setUsers(username)
    }

    viewModal.getUsers().observe(this, {userItem ->
      if (userItem != null) {
        adapter.setData(userItem)
        showLoading(false)
      }
    })

    showRecyclerView()

  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.action_change_settings) {
      val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
      startActivity(mIntent)
    }
    return super.onOptionsItemSelected(item)
  }

  private fun showRecyclerView() {
    adapter = ListUserAdapter()
    adapter.notifyDataSetChanged()

    binding.recyclerView.layoutManager = LinearLayoutManager(this)
    binding.recyclerView.adapter = adapter

    adapter.setOnItemClickCallback( object : ListUserAdapter.OnItemClickCallback {
      override fun onItemClicked(data: UserItem) {
        showSelectUser(data)
      }

    })
  }

  private fun showSelectUser(data: UserItem) {
    val intent = Intent(this, DetailActivity::class.java)
    intent.putExtra(DetailActivity.EXTRA_USER, data)
    startActivity(intent)

    Toast.makeText(this, data.username, Toast.LENGTH_SHORT).show()
  }

  private fun showLoading(state: Boolean) {
    if (state) {
      binding.progressBar.visibility = View.VISIBLE
    } else {
      binding.progressBar.visibility = View.GONE
    }
  }

}