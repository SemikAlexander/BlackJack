package com.example.blackjack

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.blackjack.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        super.onStart()

        binding.apply {
            adView.loadAd(AdRequest.Builder().build())

            playGame.setOnClickListener {
                animation()
                GlobalScope.launch {
                    delay(850)
                    launch(Dispatchers.Main) {
                        startActivity<GameActivity>()
                        finish()
                    }
                }
            }

            settings.setOnClickListener {
                startActivity<SettingsActivity>()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun animation() {
        binding.apply {
            gameMenu.visibility = View.GONE
            settings.visibility = View.GONE
            adView.visibility = View.GONE


            val cx: Int = (mainActivity.left + mainActivity.right) / 2
            val cy = 0
            val finalRadius: Int = max(mainActivity.width, mainActivity.height)
            val anim = ViewAnimationUtils
                .createCircularReveal(mainActivity, cx, cy, 0f, finalRadius.toFloat())
            mainActivity.visibility = View.VISIBLE
            mainActivity.setBackgroundColor(Color.RED)
            anim.duration = 1000
            anim.start()
        }
    }
}