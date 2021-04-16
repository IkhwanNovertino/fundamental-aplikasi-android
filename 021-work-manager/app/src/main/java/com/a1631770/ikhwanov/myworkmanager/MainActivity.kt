package com.a1631770.ikhwanov.myworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.work.*
import com.a1631770.ikhwanov.myworkmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {

  private lateinit var workManager: WorkManager
  private lateinit var periodicWorkRequest: PeriodicWorkRequest
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    workManager = androidx.work.WorkManager.getInstance(this)
    binding.btnOneTimeTask.setOnClickListener(this)
    binding.btnPeriodicTask.setOnClickListener(this)
    binding.btnCancelTask.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    when(v?.id) {
      R.id.btn_one_time_task -> startOneTimexTask()
      R.id.btn_periodic_task -> startPeriodicTask()
      R.id.btn_cancel_task -> cancelPeriodicTask()
    }
  }

  private fun startPeriodicTask() {
    binding.tvStatus.text = getString(R.string.status)
    val data = Data.Builder().putString(MyWorker.EXTRA_CITY, binding.edtCity.text.toString()).build()

    val constrains = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    periodicWorkRequest = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES).setInputData(data).setConstraints(constrains).build()

    workManager.enqueue(periodicWorkRequest)
    workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id)
      .observe(this, { workInfo ->
        val status = workInfo.state.name
        binding.tvStatus.append("\n $status")
        binding.btnCancelTask.isEnabled = false
        if (workInfo.state ==WorkInfo.State.ENQUEUED) {
          binding.btnCancelTask.isEnabled = true
        }
      })
  }

  private fun cancelPeriodicTask() {
    workManager.cancelWorkById(periodicWorkRequest.id)
  }

  private fun startOneTimexTask() {
    binding.tvStatus.text = getString(R.string.status)
    val data = Data.Builder()
      .putString(MyWorker.EXTRA_CITY, binding.edtCity.text.toString())
      .build()

    //kode yang digunakan jika ingin mengatur kapan task ingin dieksekusi
    //val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
      .setInputData(data)
      .build()

    workManager.enqueue(oneTimeWorkRequest)
    workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
      .observe(this, {workInfo ->
        val status = workInfo.state.name
        binding.tvStatus.append("\n $status" )
      })
  }
}