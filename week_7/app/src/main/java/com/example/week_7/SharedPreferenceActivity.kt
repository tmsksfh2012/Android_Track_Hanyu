package com.example.week_7

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.week_7.databinding.ActivitySharedpreferencesBinding

class SharedPreferenceActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var binding : ActivitySharedpreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySharedpreferencesBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.spBtnSubmit.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when(p0.id) {
                R.id.sp_btn_submit -> {
                    val sharedPreferences = getSharedPreferences("memo", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("title", findViewById<EditText>(R.id.sp_edit_title).text.toString())
                    editor.putString("content", findViewById<EditText>(R.id.sp_edit_content).text.toString())
                    editor.apply()
                    finish()
                }
            }
        }
    }
}