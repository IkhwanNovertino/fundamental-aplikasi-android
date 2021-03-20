package com.a1631770.ikhwanov.mylistview

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
  private lateinit var adapter: HeroAdapter
  private lateinit var dataName: Array<String>
  private lateinit var dataDesc: Array<String>
  private lateinit var dataPhoto: TypedArray
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
    dataName = resources.getStringArray(R.array.data_name)
    dataDesc = resources.getStringArray(R.array.data_desc)
    dataPhoto = resources.obtainTypedArray(R.array.data_photo)
  }

  private fun addItem() {
    for (position in dataName.indices) {
      val hero = Hero(
          dataPhoto.getResourceId(position, -1),
          dataName[position],
          dataDesc[position]
      )

      heroes.add(hero)
    }
    adapter.heroes = heroes
  }
}