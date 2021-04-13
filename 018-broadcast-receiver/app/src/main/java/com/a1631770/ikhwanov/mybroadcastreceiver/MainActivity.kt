package com.a1631770.ikhwanov.mybroadcastreceiver

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.a1631770.ikhwanov.mybroadcastreceiver.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), View.OnClickListener {

  private var binding: ActivityMainBinding? = null

  companion object {
    private const val SMS_REQUEST_CODE = 101
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding?.root)

    binding?.btnPermission?.setOnClickListener(this)

  }

  override fun onClick(v: View?) {
    when {
      v?.id  == R.id.btn_permission -> PermissionManager.check(this, android.Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE)

    }
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String>,
    grantResults: IntArray
  ) {
    if (requestCode == SMS_REQUEST_CODE) {
      when {
        grantResults[0] == PackageManager.PERMISSION_GRANTED -> Toast.makeText(this, "Sms rceiver permission diterima", Toast.LENGTH_SHORT).show()
        else -> Toast.makeText(this, "Sms receiver permission ditolak", Toast.LENGTH_SHORT).show()
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    binding = null
  }
}