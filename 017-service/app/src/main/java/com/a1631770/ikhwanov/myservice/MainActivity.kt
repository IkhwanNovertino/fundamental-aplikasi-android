package com.a1631770.ikhwanov.myservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a1631770.ikhwanov.myservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding : ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnStartService.setOnClickListener {
      val mStartServiceIntent = Intent(this@MainActivity, MyService::class.java)
      startService(mStartServiceIntent)
    }

    binding.btnStartJobIntentService.setOnClickListener {  }

    binding.btnStartBoundService.setOnClickListener {  }

    binding.btnStopBoundService.setOnClickListener {  }

  }
}