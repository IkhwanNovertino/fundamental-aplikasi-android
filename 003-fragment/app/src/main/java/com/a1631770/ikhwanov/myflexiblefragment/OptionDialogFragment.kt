package com.a1631770.ikhwanov.myflexiblefragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class OptionDialogFragment : DialogFragment() {
  private lateinit var btnChoose: Button
  private lateinit var btnClose: Button
  private lateinit var rgOptions: RadioGroup
  private lateinit var rbPep: RadioButton
  private lateinit var rbCruyff: RadioButton
  private lateinit var rbEnrique: RadioButton
  private lateinit var rbRijkaard: RadioButton
  private var optionDialogListener: OnOptionDialogListener? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_option_dialog, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    btnChoose = view.findViewById(R.id.btn_choose)
    btnClose = view.findViewById(R.id.btn_close)
    rgOptions = view.findViewById(R.id.rg_options)
    rbPep = view.findViewById(R.id.rb_pep)
    rbCruyff = view.findViewById(R.id.rb_cruyff)
    rbEnrique = view.findViewById(R.id.rb_enrique)
    rbRijkaard = view.findViewById(R.id.rb_rijkaard)

    btnChoose.setOnClickListener {
      val checkedRadioButtonId = rgOptions.checkedRadioButtonId
      if (checkedRadioButtonId != -1) {
        var coach: String? = null
        when (checkedRadioButtonId) {
          R.id.rb_pep -> coach = rbPep.text.toString().trim()

          R.id.rb_cruyff -> coach = rbCruyff.text.toString().trim()

          R.id.rb_enrique -> coach = rbEnrique.text.toString().trim()

          R.id.rb_rijkaard -> coach = rbRijkaard.text.toString().trim()
        }
        optionDialogListener?.onOptionChosen(coach)
        dialog?.dismiss()
      }
    }

    btnClose.setOnClickListener {
      dialog?.cancel()
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    val fragment = parentFragment

    if (fragment is DetailCategoryFragment){
      val detailCategoryFragment = fragment
      this.optionDialogListener = detailCategoryFragment.optionDialogListener
    }
  }

  override fun onDetach() {
    super.onDetach()
    this.optionDialogListener = null
  }

  interface OnOptionDialogListener {
    fun onOptionChosen(text: String?)

  }
}

































