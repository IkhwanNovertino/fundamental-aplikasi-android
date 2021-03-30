package com.a1631770.ikhwanov.githubapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter internal constructor(private val context: Context): BaseAdapter() {
    internal var users = arrayListOf<Users>()

    override fun getCount(): Int = users.size

    override fun getItem(position: Int): Any = users[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        val user = getItem(position) as Users
        viewHolder.bind(user)
        return itemView
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val tvUsername = view.findViewById<TextView>(R.id.tv_username)
        private val tvRepo = view.findViewById<TextView>(R.id.tv_repo)
        private val tvFollower = view.findViewById<TextView>(R.id.tv_follower)
        private val imgAvatar = view.findViewById<CircleImageView>(R.id.img_avatar)

        internal fun bind(user: Users) {
            tvUsername.text = user.username
            tvRepo.text = user.repo
            tvFollower.text = user.follower
            imgAvatar.setImageResource(user.avatar!!)
        }
    }
}