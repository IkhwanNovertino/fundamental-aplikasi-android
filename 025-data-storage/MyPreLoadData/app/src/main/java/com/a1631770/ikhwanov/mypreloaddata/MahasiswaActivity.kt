package com.a1631770.ikhwanov.mypreloaddata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.a1631770.ikhwanov.mypreloaddata.adapter.MahasiswaAdapter
import com.a1631770.ikhwanov.mypreloaddata.database.MahasiswaHelper
import com.a1631770.ikhwanov.mypreloaddata.databinding.ActivityMahasiswaBinding

class MahasiswaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMahasiswaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val mahasiswaAdapter = MahasiswaAdapter()
        binding.recyclerview.adapter = mahasiswaAdapter

        val mahasiswaHelper = MahasiswaHelper(this)
        mahasiswaHelper.open()

        val mahasiswaModels = mahasiswaHelper.getAllData()
        mahasiswaHelper.close()

        mahasiswaAdapter.setData(mahasiswaModels)
    }
}