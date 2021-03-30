package com.a1631770.ikhwanov.githubapp

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  private lateinit var adapter: UserAdapter

  private lateinit var dataName: Array<String>
  private lateinit var dataUsername: Array<String>
  private lateinit var dataRepo: Array<String>
  private lateinit var dataFollower: Array<String>
  private lateinit var dataFollowing: Array<String>
  private lateinit var dataCompany: Array<String>
  private lateinit var dataLocation: Array<String>
  private lateinit var dataAvatar: TypedArray
  private var users = arrayListOf<Users>()

//  companion object {
//    const val INTENT_DATA = "intent_data"
//  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val listView = findViewById<ListView>(R.id.lv_list)
    adapter = UserAdapter(this)
    listView.adapter =adapter

    prepare()

    addItem()

    listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
      //Toast.makeText(this@MainActivity, users[position].username, Toast.LENGTH_SHORT).show()
      val dataUsers = Users()
      dataUsers.avatar = dataAvatar.getResourceId(position, -1)
      dataUsers.username = users[position].username
      dataUsers.name = dataName[position]
      dataUsers.company = dataCompany[position]
      dataUsers.location = dataLocation[position]
      dataUsers.repo = dataRepo[position]
      dataUsers.follower = dataFollower[position]
      dataUsers.following = dataFollowing[position]


      val moveObject = Intent(this@MainActivity, DetailActivity::class.java)
      moveObject.putExtra(DetailActivity.EXTRA_USER, dataUsers)
      startActivity(moveObject)
    }
  }

  private fun prepare() {
    dataName = resources.getStringArray(R.array.name)
    dataUsername = resources.getStringArray(R.array.username)
    dataRepo = resources.getStringArray(R.array.repository)
    dataFollower = resources.getStringArray(R.array.followers)
    dataFollowing = resources.getStringArray(R.array.following)
    dataCompany = resources.getStringArray(R.array.company)
    dataLocation = resources.getStringArray(R.array.location)
    dataAvatar = resources.obtainTypedArray(R.array.avatar)
  }

  private fun addItem() {
    for (position in dataUsername.indices) {
      val user = Users(
        dataAvatar.getResourceId(position, -1),
        dataUsername[position],
        dataName[position],
        dataCompany[position],
        dataLocation[position],
        dataRepo[position],
        dataFollower[position],
        dataFollowing[position]
      )

      users.add(user)
    }
    adapter.users = users
  }


}

