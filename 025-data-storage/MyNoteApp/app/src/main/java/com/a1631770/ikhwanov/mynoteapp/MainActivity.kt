package com.a1631770.ikhwanov.mynoteapp

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.a1631770.ikhwanov.mynoteapp.adapter.NoteAdapter
import com.a1631770.ikhwanov.mynoteapp.databinding.ActivityMainBinding
import com.a1631770.ikhwanov.mynoteapp.db.NoteHelper
import com.a1631770.ikhwanov.mynoteapp.helper.MappingHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  private lateinit var adapter: NoteAdapter
  private lateinit var binding: ActivityMainBinding

  companion object {
    private const val EXTRA_STATE = "EXTRA_STATE"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    supportActionBar?.title = "Notes"

    binding.rvNotes.layoutManager = LinearLayoutManager(this)
    binding.rvNotes.setHasFixedSize(true)
    adapter = NoteAdapter(this)
    binding.rvNotes.adapter = adapter

    binding.fabAdd.setOnClickListener {
      val intent = Intent(this, NoteAddUpdateActivity::class.java)
      startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD)
    }

    loadNotesAsync()

    if (savedInstanceState == null) {
      // proses ambil data
      loadNotesAsync()
    } else {
      val list = savedInstanceState.getParcelableArrayList<Note>(EXTRA_STATE)
      if (list != null) {
        adapter.listNotes = list
      }
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putParcelableArrayList(EXTRA_STATE, adapter.listNotes)
  }

  private fun loadNotesAsync() {
    GlobalScope.launch(Dispatchers.Main) {
      binding.progressbar.visibility = View.VISIBLE
      val noteHelper = NoteHelper.getInstance(applicationContext)
      noteHelper.open()
      val deferredNotes = async(Dispatchers.IO) {
        val cursor = noteHelper.queryAll()
        MappingHelper.mapCursorToArrayList(cursor)
      }
      noteHelper.close()
      binding.progressbar.visibility = View.INVISIBLE
      val notes = deferredNotes.await()
      if (notes.size > 0) {
        adapter.listNotes = notes
      } else {
        adapter.listNotes = ArrayList()
        showSnakeBarMassage("Tidak ada data saat ini")
      }
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (data != null) {
      when(requestCode) {
        NoteAddUpdateActivity.REQUEST_ADD -> if(resultCode == NoteAddUpdateActivity.RESULT_ADD) {
          val note = data.getParcelableExtra<Note>(NoteAddUpdateActivity.EXTRA_NOTE)

          adapter.addItem(note)
          binding.rvNotes.smoothScrollToPosition(adapter.itemCount - 1)

          showSnakeBarMassage("Satu item berhasil ditambahkan")
        }
        NoteAddUpdateActivity.REQUEST_UPDATE -> {
          when(resultCode){
            NoteAddUpdateActivity.RESULT_UPDATE -> {
              val note = data.getParcelableExtra<Note>(NoteAddUpdateActivity.EXTRA_NOTE)
              val position = data.getIntExtra(NoteAddUpdateActivity.EXTRA_POSITION, 0)

              adapter.updateItem(position, note)
              binding.rvNotes.smoothScrollToPosition(position)

              showSnakeBarMassage("satu item berhasil diubah")
            }
            NoteAddUpdateActivity.RESULT_DELETE -> {
              val position = data.getIntExtra(NoteAddUpdateActivity.EXTRA_POSITION, 0)
              adapter.removeItem(position)
              showSnakeBarMassage("Satu item berhasil dihapus")
            }
          }
        }
      }
    }
  }

  private fun showSnakeBarMassage(message: String) {
    Snackbar.make(binding.rvNotes, message, Snackbar.LENGTH_SHORT).show()
  }
}