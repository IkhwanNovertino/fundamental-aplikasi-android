package com.a1631770.ikhwanov.myactionbar

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
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
	  
	  val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
    val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
    
    searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
    searchView.queryHint = resources.getString(R.string.search_hint)
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
      
      //menggunakan method ini ketika search selesai (OK)
      override fun onQueryTextSubmit(query: String?): Boolean {
        Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
        return true
      }
      
      //Gunakan method ini ketika inginmerespon setiap perubahan huruf pada searchView
      override fun onQueryTextChange(newText: String?): Boolean {
        return false
      }
    })

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