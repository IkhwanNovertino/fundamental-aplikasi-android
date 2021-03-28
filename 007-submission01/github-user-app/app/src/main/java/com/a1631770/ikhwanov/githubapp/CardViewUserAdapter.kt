package com.a1631770.ikhwanov.githubapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewUserAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<CardViewUserAdapter.CardViewViewHolder>() {
  class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imgPhoto: ImageView = itemView.findViewById(R.id.img_item)
    var tvName: TextView = itemView.findViewById(R.id.tv_name)
    var tvRepo: TextView = itemView.findViewById(R.id.tv_repo)
    var tvFollower: TextView = itemView.findViewById(R.id.tv_followers)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
    val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    return CardViewViewHolder(view)
  }

  override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
    val user = listUser[position]

    Glide.with(holder.itemView.context)
        .load(user.avatar)
        .apply(RequestOptions().override(90, 90))
        .into(holder.imgPhoto)

    holder.tvName.text = user.username
    holder.tvRepo.text = user.repo.toString()
    holder.tvFollower.text = user.follower.toString()
  }

  override fun getItemCount(): Int {
    return listUser.size
  }
}