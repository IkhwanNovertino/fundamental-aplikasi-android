package com.a1631770.ikhwanov.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
  private lateinit var edtLength: EditText
  private lateinit var edtWidth: EditText
  private lateinit var edtHeigth: EditText
  private lateinit var btnCalculate: EditText
  private lateinit var tvResult: TextView

  companion object {
    private const val STATE_RESULT = "state_result"
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    edtLength = findViewById(R.id.edt_length)
    edtWidth = findViewById(R.id.edt_width)
    edtHeigth = findViewById(R.id.edt_heigth)
    btnCalculate = findViewById(R.id.btn_calculate)
    tvResult = findViewById(R.id.tv_result)

    btnCalculate.setOnClickListener(this)

    if (savedInstanceState != null) {
      val result = savedInstanceState.getString(STATE_RESULT)
      tvResult.text = result
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putString(STATE_RESULT, tvResult.text.toString())
  }

  override fun onClick(v: View?) {
    if(v?.id == R.id.btn_calculate) {
      val inputLength = edtLength.text.toString().trim()
      val inputWidth = edtWidth.text.toString().trim()
      val inputHeigth = edtHeigth.text.toString().trim()

      var isEmptyFields = false

      if (inputLength.isEmpty()) {
        isEmptyFields = true
        edtLength.error = "Field ini tidak boleh kosong!"
      }

      if (inputWidth.isEmpty()) {
        isEmptyFields =true
        edtWidth.error = "Field ini tidak boleh kosong!"
      }

      if (inputHeigth.isEmpty()){
        isEmptyFields = true
        edtHeigth.error = "Fieldini tidak boleh kosong!"
      }

      if (!isEmptyFields){
      val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeigth.toDouble()
      tvResult.text = volume.toString()
      }
    }
  }
}