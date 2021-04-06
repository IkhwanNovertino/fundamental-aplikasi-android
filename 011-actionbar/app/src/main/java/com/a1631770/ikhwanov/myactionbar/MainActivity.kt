package com.a1631770.ikhwanov.myactionbar

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.a1631770.ikhwanov.myactionbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding : ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.option_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId) {
      R.id.menu1 -> {
        supportFragmentManager.beginTransaction()
          .replace(R.id.fragment_container, MenuFragment())
          .addToBackStack(null)
          .commit()
        return true
      }
      R.id.menu2 -> {
        val intent = Intent(this@MainActivity, MenuActivity::class.java)
        startActivity(intent)
        return true
      }
      else -> return true
    }
  }
}