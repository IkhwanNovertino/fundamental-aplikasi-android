package com.a1631770.ikhwanov.gituserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
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

  }
}