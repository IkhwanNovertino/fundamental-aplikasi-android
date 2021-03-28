package com.a1631770.ikhwanov.mylistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class HeroAdapter internal constructor(private val context: Context) : BaseAdapter() {
  internal var heroes = arrayListOf<Hero>()
  override fun getCount(): Int = heroes.size

  override fun getItem(position: Int): Any = heroes[position]

  override fun getItemId(position: Int): Long = position.toLong()

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    var itemView = convertView
    if (itemView == null) {
      itemView = LayoutInflater.from(context).inflate(R.layout.item_hero, parent, false)
    }

    val viewHolder = ViewHolder(itemView as View)

    val hero = getItem(position) as Hero
    viewHolder.bind(hero)
    return itemView

  }

  private inner class ViewHolder internal constructor(view: View) {
    private val txtName = view.findViewById<TextView>(R.id.txt_name)
    private val txtDesc = view.findViewById<TextView>(R.id.txt_desc)
    private val imgPhoto = view.findViewById<CircleImageView>(R.id.img_photo)

    internal fun bind(hero: Hero) {
      txtName.text = hero.name
      txtDesc.text = hero.desc
      imgPhoto.setImageResource(hero.photo)
    }
  }
}