package com.a1631770.ikhwanov.myjobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.a1631770.ikhwanov.myjobscheduler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  companion object {
    private const val JOB_IB = 10
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnStart.setOnClickListener { startJob() }
    binding.btnCancel.setOnClickListener { cancelJob() }
  }

  private fun startJob() {
    if(isJobRunning(this)) {
      Toast.makeText(this, "Job service is already scheduler", Toast.LENGTH_SHORT).show()
      return
    }

    val mServiceComponent = ComponentName(this, GetCurrentWeatherJobService::class.java)

    val builder = JobInfo.Builder(JOB_IB, mServiceComponent)
    builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
    builder.setRequiresDeviceIdle(false)
    builder.setRequiresCharging(false)
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      builder.setPeriodic(900000) //15menit
    } else {
      builder.setPeriodic(180000) //3 menit
    }

    val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
    scheduler.schedule(builder.build())
    Toast.makeText(this, "Job service started", Toast.LENGTH_SHORT).show()
  }

  private fun cancelJob() {
    val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
    scheduler.cancel(JOB_IB)
    Toast.makeText(this, "Job Service canceled", Toast.LENGTH_SHORT).show()
  }

  private fun isJobRunning(context: Context): Boolean {
    var isScheduled = false
    val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

    for (jobInfo in scheduler.allPendingJobs) {
      if(jobInfo.id == JOB_IB) {
        isScheduled = true
        break
      }
    }
    return isScheduled
  }

}