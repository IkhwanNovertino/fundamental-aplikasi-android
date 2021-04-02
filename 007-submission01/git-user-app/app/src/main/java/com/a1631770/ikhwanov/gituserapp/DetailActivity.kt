package com.a1631770.ikhwanov.gituserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a1631770.ikhwanov.gituserapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

  companion object {
    const val EXTRA_USER = "extra_user"
  }

  private lateinit var binding: ActivityDetailBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val user = intent.getParcelableExtra(EXTRA_USER) as User

    binding.imgAvatar.setImageResource(user.avatar!!)
    binding.tvUsername.text = user.username
    binding.tvNames.text = user.name
    binding.tvCompany.text = user.company
    binding.tvLocation.text = user.location
    binding.tvRepo.text = user.repository.toString()
    binding.tvFollower.text = user.follower.toString()
    binding.tvFollowing.text = user.following.toString()

  }
}