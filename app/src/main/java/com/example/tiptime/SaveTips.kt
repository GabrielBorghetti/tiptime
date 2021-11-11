package com.example.tiptime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivitySaveTipsBinding

class SaveTips : AppCompatActivity() {
    lateinit var binding: ActivitySaveTipsBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
                binding = ActivitySaveTipsBinding.inflate(layoutInflater)
                    setContentView(binding.root)

            binding.btHome.setOnClickListener{
                val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
            }
            binding.btAbout.setOnClickListener{
                val intent = Intent (applicationContext, About::class.java)
                    startActivity(intent)
            }

        }
}