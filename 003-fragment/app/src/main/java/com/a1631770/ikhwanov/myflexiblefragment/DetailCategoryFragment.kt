package com.a1631770.ikhwanov.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class DetailCategoryFragment : Fragment() {
  lateinit var tvCateoryName: TextView
  lateinit var tvCategoryDesc: TextView
  lateinit var btnProfile: Button
  lateinit var btnShowDialog: Button

  var description: String? = null

  companion object {
    var EXTRA_NAME = "extra_name"
    var EXTRA_DESCRIPTION = "extra_description"
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_detail_category, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    tvCateoryName = view.findViewById(R.id.tv_category_name)
    tvCategoryDesc = view.findViewById(R.id.tv_category_desc)
    btnProfile = view.findViewById(R.id.btn_profile)
    btnShowDialog = view.findViewById(R.id.btn_show_dialog)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    if (savedInstanceState != null) {
      val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION)
      description = descFromBundle
    }

    if (arguments != null) {
      val categoryName = arguments?.getString(EXTRA_NAME)
      tvCateoryName.text = categoryName
      tvCategoryDesc.text = description
    }
  }




















}