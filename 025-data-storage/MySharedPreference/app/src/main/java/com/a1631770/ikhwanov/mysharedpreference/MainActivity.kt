package com.a1631770.ikhwanov.mysharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.a1631770.ikhwanov.mysharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

  private lateinit var mUserPreference: UserPreference

  private var isPreferenceEmpty = false
  private lateinit var userModel: UserModel

  private lateinit var binding: ActivityMainBinding

  companion object {
    private const val REQUEST_CODE = 100
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    supportActionBar?.title = "My User Preference"

    mUserPreference = UserPreference(this)

    binding.btnSave.setOnClickListener(this)

    showExistingPreference()
  }

  private fun showExistingPreference() {
    userModel = mUserPreference.getUser()
    populateView(userModel)
    checkForm(userModel)
  }

  private fun checkForm(userModel: UserModel) {
    when {
      userModel.name.toString().isNotEmpty() -> {
        binding.btnSave.text = getString(R.string.change)
        isPreferenceEmpty = false
      }
      else -> {
        binding.btnSave.text = getString(R.string.save)
        isPreferenceEmpty = true
      }
    }
  }

  private fun populateView(userModel: UserModel) {
    binding.tvName.text = if (userModel.name.toString().isEmpty()) "Tidak Ada" else userModel.name
    binding.tvAge.text = if (userModel.age.toString().isEmpty()) "Tidak Ada" else userModel.age.toString()
    binding.tvIsLoveMu.text = if (userModel.isLove) "Ya" else "Tidak"
    binding.tvEmail.text = if (userModel.email.toString().isEmpty()) "Tidak Ada" else userModel.email
    binding.tvPhone.text = if (userModel.phoneNumber.toString().isEmpty()) "Tidak Ada" else userModel.phoneNumber
  }

  override fun onClick(v: View?) {
    if (v?.id == R.id.btn_save) {
      val intent = Intent(this, FormUserPreferenceActivity::class.java)
      when {
        isPreferenceEmpty -> {
          intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_ADD)
          intent.putExtra("USER", userModel)
        }
        else -> {
          intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_EDIT)
          intent.putExtra("USER", userModel)
        }
      }
      startActivityForResult(intent, REQUEST_CODE)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_CODE) {
      if (requestCode == FormUserPreferenceActivity.RESULT_CODE) {
        userModel = data?.getParcelableExtra(FormUserPreferenceActivity.EXTRA_RESULT) as UserModel
        populateView(userModel)
        checkForm(userModel)

      }
    }
  }
}