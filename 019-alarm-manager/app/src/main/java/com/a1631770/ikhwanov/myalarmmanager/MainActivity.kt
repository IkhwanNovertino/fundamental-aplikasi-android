package com.a1631770.ikhwanov.myalarmmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.a1631770.ikhwanov.myalarmmanager.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

  private lateinit var binding: ActivityMainBinding
  private lateinit var alarmReceiver: AlarmReceiver

  companion object {
    private const val DATE_PICKER_TAG = "DatePicker"
    private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
    private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    //Listener one time alarm
    binding.btnOnceDate.setOnClickListener(this)
    binding.btnOnceTime.setOnClickListener(this)
    binding.btnSetOnceAlarm.setOnClickListener(this)

    alarmReceiver = AlarmReceiver()
  }

  override fun onClick(v: View?) {
    when(v?.id) {
      R.id.btn_once_date -> {
        val datePickerFragment = DatePickerFragment()
        datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
      }

      R.id.btn_once_time -> {
        val timePickerFragment = TimePickerFragment()
        timePickerFragment.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
      }

      R.id.btn_set_once_alarm -> {
        val onceDate = binding.tvOnceDate.text.toString()
        val onceTime = binding.tvOnceTime.text.toString()
        val onceMessage = binding.edtOnceMessage.text.toString()

        alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, onceDate, onceTime, onceMessage)
      }
    }
  }

  override fun onDialogDataSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
    //siapkan data formatter-nya terlebih dahulu
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)
    val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    //setText dari textview once
    binding.tvOnceDate.text = dataFormat.format(calendar.time)
  }

  override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
    //siapkan time formatter-nya terlebih dahulu
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
    calendar.set(Calendar.MINUTE, minute)

    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    //set text dari textview berdasarkan tag
    when(tag) {
      TIME_PICKER_ONCE_TAG -> binding.tvOnceTime.text = dateFormat.format(calendar.time)
      TIME_PICKER_REPEAT_TAG -> {}

      else -> {}
    }
  }

  override fun onDestroy() {
    super.onDestroy()
  }




}