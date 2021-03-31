package com.a1631770.ikhwanov.gituserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

  companion object {
    const val EXTRA_USER = "extra_user"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    val user = intent.getParcelableExtra(EXTRA_USER) as User

    val imgAvatar = findViewById<ImageView>(R.id.img_avatar)
    val tvUsername = findViewById<TextView>(R.id.tv_username)
    val tvRepo = findViewById<TextView>(R.id.tv_repo)
    val tvFollower = findViewById<TextView>(R.id.tv_follower)
    val tvFollowing = findViewById<TextView>(R.id.tv_following)
    val tvName = findViewById<TextView>(R.id.tv_names)
    val tvCompany = findViewById<TextView>(R.id.tv_company)
    val tvLocation = findViewById<TextView>(R.id.tv_location)

      imgAvatar.setImageResource(user.avatar!!)
      tvUsername.text = user.username
      tvName.text = user.name
      tvCompany.text = user.company
      tvLocation.text = user.location
      tvRepo.text = user.repository.toString()
      tvFollower.text = user.follower.toString()
      tvFollowing.text = user.following.toString()

  }
}