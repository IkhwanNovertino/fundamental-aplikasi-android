package com.a1631770.ikhwanov.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
  private lateinit var tvResult: TextView
  
  companion object{
    private const val REQUEST_CODE = 100
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val btnMoveActivity = findViewById<Button>(R.id.btn_move_activity)
    btnMoveActivity.setOnClickListener(this)
    
    val btnMoveWithData: Button = findViewById(R.id.btn_move_activity_data)
    btnMoveWithData.setOnClickListener(this)
    
    val btnMoveWithObject = findViewById<Button>(R.id.btn_move_activity_object)
    btnMoveWithObject.setOnClickListener(this)
    
    val btnDialPhone = findViewById<Button>(R.id.btn_dial_number)
    btnDialPhone.setOnClickListener(this)
    
    val btnMoveForResult = findViewById<Button>(R.id.btn_result_for_activity)
    btnMoveForResult.setOnClickListener(this)
    
    tvResult = findViewById(R.id.tv_result)
  }

  override fun onClick(v: View?) {
    when(v?.id) {
      R.id.btn_move_activity -> {
        val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
        startActivity(moveIntent)
      }
      
      R.id.btn_move_activity_data -> {
        val moveWithData = Intent(this@MainActivity, MoveWithDataActivity::class.java)
        moveWithData.putExtra(MoveWithDataActivity.EXTRA_NAME, "Muhammad Ikhwan")
        moveWithData.putExtra(MoveWithDataActivity.EXTRA_AGE, 20)
        startActivity(moveWithData)
      }
      
      R.id.btn_move_activity_object -> {
        val person = Person("Muhammad Ikhwan",
          20,
          "academy@bangkit.academy",
          "Banjarmasin"
        )
        
        val moveWithObject = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
        moveWithObject.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
        startActivity(moveWithObject)
      }
      
      R.id.btn_dial_number -> {
        val phoneNumber = "082250870821"
        val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(dialPhoneIntent)
      }
      
      R.id.btn_result_for_activity -> {
        val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
        startActivityForResult(moveForResultIntent, REQUEST_CODE)
      }
    }
  }
  
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    
    if (requestCode == REQUEST_CODE) {
      if (resultCode == MoveForResultActivity.RESULT_CODE) {
        val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
        tvResult.text = "Hasil: $selectedValue"
      }
    }
  }
}