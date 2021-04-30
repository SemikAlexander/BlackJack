package com.example.blackjack

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
                    delay(800)
                    launch(Dispatchers.Main) {
                        startActivity<GameActivity>()
                        overridePendingTransition(R.anim.slidein, R.anim.slideout)
                        finish()
                    }
                }
            }

            settings.setOnClickListener {
                animation2()
                GlobalScope.launch {
                    delay(600)
                    launch(Dispatchers.Main) {
                        startActivity<SettingsActivity>()
                        overridePendingTransition(R.anim.slidein, R.anim.slideout)
                        finish()
                    }
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor", "Range")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun animation() {
        binding.apply {
            gameMenu.visibility = View.GONE
            settings.visibility = View.GONE
            adView.visibility = View.GONE
            name.visibility = View.GONE

            val cx: Int = (mainActivity.left + mainActivity.right) / 2

            val location = IntArray(2)
            playGame.getLocationOnScreen(location)

            val cy = location[1]
            val finalRadius: Int = max(mainActivity.width, mainActivity.height)
            val anim = ViewAnimationUtils
                .createCircularReveal(mainActivity, cx, cy, 0f, finalRadius.toFloat())
            mainActivity.visibility = View.VISIBLE

            val colorValue = ContextCompat.getColor(this@MainActivity, R.color.background_color)

            mainActivity.setBackgroundColor(colorValue)
            anim.duration = 800
            anim.start()
        }
    }

    @SuppressLint("ResourceAsColor", "Range")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun animation2() {
        binding.apply {
            gameMenu.visibility = View.GONE
            settings.visibility = View.GONE
            adView.visibility = View.GONE
            name.visibility = View.GONE

            val location = IntArray(2)
            settings.getLocationOnScreen(location)

            val cx = location[0]
            val cy = location[1]
            val finalRadius: Int = max(mainActivity.width, mainActivity.height)
            val anim = ViewAnimationUtils
                .createCircularReveal(mainActivity, cx, cy, 0f, finalRadius.toFloat())
            mainActivity.visibility = View.VISIBLE

            val colorValue = ContextCompat.getColor(this@MainActivity, R.color.background_color)

            mainActivity.setBackgroundColor(colorValue)
            anim.duration = 600
            anim.start()
        }
    }
}