package com.a1631770.ikhwanov.myintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithObjectActivity : AppCompatActivity() {
  companion object{
    const val EXTRA_PERSON = "extra_person"
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_move_with_object)
    
    val tvObject = findViewById<TextView>(R.id.tv_object_received)
    
    val person = intent.getParcelableExtra<Person>(EXTRA_PERSON) as Person
    val text = "Nama: ${person.name.toString()}, \nEmail: ${person.email}, \nAge: ${person.age}, \nLocation: ${person.city}"
    tvObject.text = text
  }
}