package com.example.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blackjack.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.apply {

        }
    }

    override fun onBackPressed() {
        startActivity<MainActivity>()
        finish()
    }
}