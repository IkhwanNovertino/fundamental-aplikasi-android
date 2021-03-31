package com.a1631770.ikhwanov.gituserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
  private lateinit var rvUsers: RecyclerView
  private var list: ArrayList<User> = arrayListOf()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    rvUsers = findViewById(R.id.rv_users)
    rvUsers.setHasFixedSize(true)

    list.addAll(UsersData.listData)
    showRecyclerList()
  }

  private fun showRecyclerList() {
    rvUsers.layoutManager = LinearLayoutManager(this)
    val listUserAdapter = ListUserAdapter(list)
    rvUsers.adapter = listUserAdapter

    listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
      override fun onItemClicked(data: User) {
        showSelectedUser(data)
      }
    })

  }

  private fun showSelectedUser(user: User) {
    val intent = Intent(this@MainActivity, DetailActivity::class.java)
    intent.putExtra(DetailActivity.EXTRA_USER, user)
    this@MainActivity.startActivity(intent)

    Toast.makeText(this, "nama: ${user.name}, username: ${user.username} " +
            "\nRepo: ${user.repository}, follower: ${user.follower}, following: ${user.following}" +
            "\ncompany: ${user.company}, location: ${user.location}", Toast.LENGTH_SHORT).show()
  }
}