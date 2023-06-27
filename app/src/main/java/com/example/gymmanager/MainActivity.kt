package com.example.gymmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.example.gymmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addT.setOnClickListener {
            startActivity(Intent(this,AddTrainerActivity::class.java))
        }

        binding.onlineT.setOnClickListener {
            startActivity(Intent(this,OnlineTrainerActivity::class.java))
        }
    }

}