package com.a1631770.ikhwanov.myunittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
  private  lateinit var mainViewModel: MainViewModel

  private lateinit var edtLength: EditText
  private lateinit var edtWidth: EditText
  private lateinit var edtHeigth: EditText
  private lateinit var tvResult: TextView
  private lateinit var btnCalculateVolume: Button
  private lateinit var btnCalculateSurfaceArea: Button
  private lateinit var btnCalculateCircumference: Button
  private lateinit var btnSave: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mainViewModel = MainViewModel(CuboidModel())

    edtLength = findViewById(R.id.edt_length)
    edtWidth = findViewById(R.id.edt_width)
    edtHeigth = findViewById(R.id.edt_heigth)
    tvResult = findViewById(R.id.tv_result)
    btnCalculateVolume = findViewById(R.id.btn_volume)
    btnCalculateSurfaceArea = findViewById(R.id.btn_surface_area)
    btnCalculateCircumference = findViewById(R.id.btn_circumfarence)
    btnSave = findViewById(R.id.btn_save)

    btnSave.setOnClickListener(this)
    btnCalculateVolume.setOnClickListener(this)
    btnCalculateSurfaceArea.setOnClickListener(this)
    btnCalculateCircumference.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    val length = edtLength.text.toString().trim()
    val width = edtWidth.text.toString().trim()
    val heigth = edtHeigth.text.toString().trim()

    when {
      length.isEmpty() -> edtLength.error = "Field ini tidak boleh kosong"
      heigth.isEmpty() -> edtHeigth.error = "Field ini tidak boleh kosong"
      width.isEmpty() -> edtWidth.error = "Field ini tidak boleh kosong"

      else -> {
        val l = length.toDouble()
        val w = width.toDouble()
        val h = heigth.toDouble()

        when {
          v?.id == R.id.btn_save -> {
            mainViewModel.save(l, w, h)
            visible()
          }

          v?.id == R.id.btn_circumfarence ->{
            tvResult.text = mainViewModel.getCircumference().toString()
            gone()
          }

          v?.id == R.id.btn_surface_area -> {
            tvResult.text = mainViewModel.getSurfaceArea().toString()
            gone()
          }

          v?.id == R.id.btn_volume -> {
            tvResult.text = mainViewModel.getVolume().toString()
            gone()
          }
        }
      }
    }
  }

  private fun gone() {
    btnCalculateVolume.visibility = View.GONE
    btnCalculateCircumference.visibility = View.GONE
    btnCalculateSurfaceArea.visibility = View.GONE
    btnSave.visibility = View.VISIBLE
  }

  private fun visible() {
    btnCalculateVolume.visibility = View.VISIBLE
    btnCalculateCircumference.visibility = View.VISIBLE
    btnCalculateSurfaceArea.visibility = View.VISIBLE
    btnSave.visibility = View.GONE
  }
}