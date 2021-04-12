package com.a1631770.ikhwanov.myservice

import android.app.PendingIntent.getService
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.a1631770.ikhwanov.myservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding : ActivityMainBinding

  //BoundService
  private var mServiceBound = false
  private lateinit var mBoundService: MyBoundService

  private val mServiceConnection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
      val myBinder = service as MyBoundService.MyBinder
      mBoundService = myBinder.getService
      mServiceBound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      mServiceBound = false
    }

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnStartService.setOnClickListener {
      val mStartServiceIntent = Intent(this@MainActivity, MyService::class.java)
      startService(mStartServiceIntent)
    }

    binding.btnStartJobIntentService.setOnClickListener {
      val mStartJobIntentService = Intent(this, MyJobIntentService::class.java)
      mStartJobIntentService.putExtra(MyJobIntentService.EXTRA_DURATION, 5000L)
      MyJobIntentService.enqueueWork(this, mStartJobIntentService)
    }

    binding.btnStartBoundService.setOnClickListener {
      val mBoundServiceIntent = Intent(this, MyBoundService::class.java)
      bindService(mBoundServiceIntent, mServiceConnection, BIND_AUTO_CREATE)
    }

    binding.btnStopBoundService.setOnClickListener {
      unbindService(mServiceConnection)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    if (mServiceBound) {
      unbindService(mServiceConnection)
    }
  }
}