package com.example.week6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.week6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val handler = Handler(Looper.getMainLooper())

        val animalList = ArrayList<Int>()

        animalList.add(R.drawable.cat)
        animalList.add(R.drawable.danzi)
        animalList.add(R.drawable.chicken)
        animalList.add(R.drawable.inzermi)

        Thread {
            for(i in animalList) {
                Thread.sleep(1000)

                handler.post {
                    binding.picture.setImageResource(i)
                }
            }

        }.start()
    }
}