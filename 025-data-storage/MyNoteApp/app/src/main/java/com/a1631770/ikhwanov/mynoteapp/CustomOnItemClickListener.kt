package com.a1631770.ikhwanov.mynoteapp

import android.view.View
import java.text.FieldPosition

class CustomOnItemClickListener(
    private val position: Int,
    private val onItemClickCallback: OnItemClickCallback
) : View.OnClickListener{

    interface OnItemClickCallback {
        fun onItemClicked(v: View?, position: Int)
    }

    override fun onClick(v: View?) {
        onItemClickCallback.onItemClicked(v, position)
    }

}