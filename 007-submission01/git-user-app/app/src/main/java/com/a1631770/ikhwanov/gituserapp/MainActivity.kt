package com.a1631770.ikhwanov.gituserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a1631770.ikhwanov.gituserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//  private lateinit var rvUsers: RecyclerView
  private var list: ArrayList<User> = arrayListOf()

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    //setContentView(R.layout.activity_main)

    //rvUsers = findViewById(R.id.rv_users)
    //rvUsers.setHasFixedSize(true)
    binding.rvUsers.setHasFixedSize(true)

    list.addAll(UsersData.listData)
    showRecyclerList()
  }

  private fun showRecyclerList() {
    binding.rvUsers.layoutManager = LinearLayoutManager(this)
    val listUserAdapter = ListUserAdapter(list)
    binding.rvUsers.adapter = listUserAdapter

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

    Toast.makeText(this, user.username, Toast.LENGTH_SHORT).show()
  }
}