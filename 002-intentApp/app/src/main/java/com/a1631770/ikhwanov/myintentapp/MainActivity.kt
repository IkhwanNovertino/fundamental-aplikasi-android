package com.a1631770.ikhwanov.myintentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val btnMoveActivity = findViewById<Button>(R.id.btn_move_activity)
    btnMoveActivity.setOnClickListener(this)
    
    val btnMoveWithData: Button = findViewById(R.id.btn_move_activity_data)
    btnMoveWithData.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    when(v?.id) {
      R.id.btn_move_activity -> {
        val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
        startActivity(moveIntent)
      }
      R.id.btn_move_activity_data -> {
      
      }
      
    }
  }
}