package com.example.week_7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.week_7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    private lateinit var listViewAdapter: ListViewAdapter
    var mArrayList = ArrayList<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainBtnCreate.setOnClickListener(this)
        binding.mainBtnSearch.setOnClickListener(this)

        mArrayList.add(Data("GURI", "HERE"))

        listViewAdapter = ListViewAdapter(mArrayList, this)

        binding.mainListview.adapter = listViewAdapter
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when(p0.id) {
                R.id.main_btn_create -> {
                    val intent = Intent(this@MainActivity, SharedPreferenceActivity::class.java)
                    startActivity(intent)
                }
                R.id.main_btn_search -> {
                    mArrayList.clear()
                    val sharedPreferences = getSharedPreferences("memo", MODE_PRIVATE)
                    mArrayList.add(Data(sharedPreferences.getString("title", "")!!, sharedPreferences.getString("content", "")!!))
                    listViewAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}