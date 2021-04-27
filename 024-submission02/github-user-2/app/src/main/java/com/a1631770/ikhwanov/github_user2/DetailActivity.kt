package com.a1631770.ikhwanov.github_user2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.a1631770.ikhwanov.github_user2.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModal: DetailViewModal
    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentDetail = intent.getParcelableExtra<UserItem>(EXTRA_USER)
        val username = intentDetail.username

        supportActionBar?.title = username

        showLoading(true)
        configDetailViewModal(username)

        setSectionAdapter()
    }

    private fun configDetailViewModal(username: String?) {
        viewModal = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModal::class.java)

        viewModal.setDataUserDetail(username!!)
        viewModal.getDataUserDetail().observe(this, Observer {
            if (it != null) {
                it.forEach { userItems ->
                    binding.Username.text = userItems.username
                    binding.Repo.text = userItems.repository.toString()
                    binding.Follower.text = userItems.follower.toString()
                    binding.Following.text = userItems.following.toString()
                    binding.Company.text = userItems.company
                    binding.Location.text = userItems.location

                    Glide.with(this)
                        .load(userItems.avatar)
                        .apply(RequestOptions().override(90, 90))
                        .into(binding.imageAvatar)
                }
                showLoading(false)
            }
        })
    }

    private fun setSectionAdapter() {
        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        val viewPager = binding.viewPager
        viewPager.adapter =sectionPagerAdapter
        binding.Tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBarDetail.visibility = View.VISIBLE
        } else {
            binding.progressBarDetail.visibility = View.GONE
        }
    }
}