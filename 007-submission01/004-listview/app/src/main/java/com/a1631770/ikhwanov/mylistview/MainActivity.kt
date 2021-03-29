package com.a1631770.ikhwanov.mylistview

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
  private lateinit var adapter: HeroAdapter
  //private lateinit var dataName: Array<String>
  //private lateinit var dataDesc: Array<String>
  //private lateinit var dataPhoto: TypedArray

  private lateinit var dataName: Array<String>
  private lateinit var dataUsername: Array<String>
  private lateinit var dataRepo: Array<String>
  private lateinit var dataFollower: Array<String>
  private lateinit var dataFollowing: Array<String>
  private lateinit var dataCompany: Array<String>
  private lateinit var dataLocation: Array<String>
  private lateinit var dataAvatar: TypedArray
  private var heroes = arrayListOf<Hero>()

//  private val dataName = arrayOf(
//    "Cut Nyak Dien",
//    "Ki Hajar Dewantara",
//    "Moh Yamin",
//    "Pattimura",
//    "R. A. Kartini",
//    "Sukarno")

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    /**
     * menggunakan dataName diatas
    val listView: ListView = findViewById(R.id.lv_list)
    val adapter = ArrayAdapter<String>(
      this, android.R.layout.simple_list_item_1, android.R.id.text1, dataName)

    listView.adapter = adapter
    **/

    //Menggunakan Hero class
    val listView = findViewById<ListView>(R.id.lv_list)
    adapter = HeroAdapter(this)
    listView.adapter = adapter

    //untuk inisiasi array
    prepare()
    //membuat method addItem() untuk memasukkan data tersebut
    addItem()

    listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
      Toast.makeText(this@MainActivity, heroes[position].name, Toast.LENGTH_SHORT).show()
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
      val hero = Hero(
        dataAvatar.getResourceId(position, -1),
        dataUsername[position],
        dataName[position],
        dataCompany[position],
        dataLocation[position],
        dataRepo[position],
        dataFollower[position],
        dataFollowing[position]
      )

      heroes.add(hero)
    }
    adapter.heroes = heroes
  }
}