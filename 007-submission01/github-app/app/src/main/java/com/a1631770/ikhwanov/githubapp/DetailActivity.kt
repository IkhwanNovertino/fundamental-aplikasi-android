package com.a1631770.ikhwanov.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView


class DetailActivity : AppCompatActivity() {

  companion object {
    const val EXTRA_USER = "extra_user"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    val imgAvatar = findViewById<CircleImageView>(R.id.img_avatar)
    val tvUsername = findViewById<TextView>(R.id.tv_username)
    val tvRepo = findViewById<TextView>(R.id.tv_repo)
    val tvFollower = findViewById<TextView>(R.id.tv_follower)
    val tvFollowing = findViewById<TextView>(R.id.tv_following)
    val tvName = findViewById<TextView>(R.id.tv_names)
    val tvCompany = findViewById<TextView>(R.id.tv_company)
    val tvLocation = findViewById<TextView>(R.id.tv_location)

    val user = intent.getParcelableExtra<Users>(EXTRA_USER) as Users
    //val user = intent.getParcelableExtra<Users>(MainActivity.INTENT_DATA)

    //val testName: String? = user?.username

    tvUsername.text = user.username
    tvName.text = user.name
    tvCompany.text = user.company
    tvLocation.text = user.location
    tvRepo.text = user.repo
    tvFollower.text = user.follower
    tvFollowing.text = user.following
    imgAvatar.setImageResource(user.avatar!!)

  }
}