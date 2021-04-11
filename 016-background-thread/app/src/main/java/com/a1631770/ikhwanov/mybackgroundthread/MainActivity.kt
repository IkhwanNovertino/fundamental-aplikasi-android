package com.a1631770.ikhwanov.mybackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a1631770.ikhwanov.mybackgroundthread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding : ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnStart.setOnClickListener {
      try {
          //simulate process compressing
        for ( i in 0..10) {
          Thread.sleep(500)
          val percentage = i * 10
          if (percentage == 100) {
            binding.tvStatus.setText(R.string.task_completed)
          } else {
            binding.tvStatus.text = String.format(getString(R.string.compressing), percentage)
          }
        }
      } catch (e : InterruptedException) {
        e.printStackTrace()
      }
    }
  }
}