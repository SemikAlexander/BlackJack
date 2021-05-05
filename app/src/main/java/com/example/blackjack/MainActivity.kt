package com.example.blackjack

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
    private var appModeTheme = ""
    private var bid = 0
    private var cashUser = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = getSharedPreferences(PrefsKeys.SETTING, Context.MODE_PRIVATE)
        appModeTheme = if (pref.getString(PrefsKeys.MODE, null).toString() == PrefsKeys.NIGHT_MODE) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            PrefsKeys.NIGHT_MODE
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            PrefsKeys.DAY_MODE
        }

        cashUser = pref.getInt(PrefsKeys.CASH, 2000)

        if (cashUser == 0)
            cashUser = 200

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        super.onStart()

        val pref = getSharedPreferences(PrefsKeys.SETTING, Context.MODE_PRIVATE)
        val editor = pref.edit()

        binding.apply {
            adView.loadAd(AdRequest.Builder().build())
            cash.text = "$cashUser$"

            playGame.setOnClickListener {
                animation()
                GlobalScope.launch {
                    delay(800)
                    launch(Dispatchers.Main) {
                        editor.putInt(PrefsKeys.CASH, cashUser - bid)
                        editor.apply()

                        startActivity<GameActivity> {
                            putExtra(PrefsKeys.BID, bid)
                        }
                        overridePendingTransition(R.anim.slidein, R.anim.slideout)
                        finish()
                    }
                }
            }

            appMode.setOnClickListener {
                animation2()
                GlobalScope.launch {
                    delay(700)
                    launch(Dispatchers.Main) {
                        if (appModeTheme == PrefsKeys.DAY_MODE) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                            appModeTheme = PrefsKeys.NIGHT_MODE

                            editor.putString(PrefsKeys.MODE, PrefsKeys.NIGHT_MODE)
                        }
                        else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            appModeTheme = PrefsKeys.DAY_MODE

                            editor.putString(PrefsKeys.MODE, PrefsKeys.DAY_MODE)
                        }
                        editor.apply()

                        overridePendingTransition(R.anim.slidein, R.anim.slideout)
                        recreate()
                    }
                }
            }

            clearButton.setOnClickListener {
                chosenChip.setBackgroundResource(0)
                dealLayout.visibility = View.INVISIBLE

                bid = 100
            }

            chip1.setOnClickListener {
                chosenChip.setBackgroundResource(R.drawable.chip_one)
                dealLayout.visibility = View.VISIBLE

                bid = 1
            }

            chip5.setOnClickListener {
                chosenChip.setBackgroundResource(R.drawable.chip_five)
                dealLayout.visibility = View.VISIBLE

                bid = 5
            }

            chip25.setOnClickListener {
                chosenChip.setBackgroundResource(R.drawable.chip_twenty_five)
                dealLayout.visibility = View.VISIBLE

                bid = 25
            }

            chip50.setOnClickListener {
                chosenChip.setBackgroundResource(R.drawable.chip_fifty)
                dealLayout.visibility = View.VISIBLE

                bid = 50
            }

            chip100.setOnClickListener {
                chosenChip.setBackgroundResource(R.drawable.chip_hundred)
                dealLayout.visibility = View.VISIBLE

                bid = 100
            }
        }
    }

    @SuppressLint("ResourceAsColor", "Range")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun animation() {
        binding.apply {
            chosenChip.visibility = View.INVISIBLE
            startGameRelative.visibility = View.INVISIBLE
            appMode.visibility = View.GONE
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

            val colorValue = if (appModeTheme == PrefsKeys.DAY_MODE)
                ContextCompat.getColor(this@MainActivity, R.color.background_color)
            else
                ContextCompat.getColor(this@MainActivity, R.color.black_background_color)

            mainActivity.setBackgroundColor(colorValue)
            anim.duration = 800
            anim.start()
        }
    }

    @SuppressLint("ResourceAsColor", "Range")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun animation2() {
        binding.apply {
            chosenChip.visibility = View.INVISIBLE
            startGameRelative.visibility = View.INVISIBLE
            //appMode.visibility = View.GONE
            adView.visibility = View.GONE
            name.visibility = View.GONE

            val location = IntArray(2)
            appMode.getLocationOnScreen(location)

            val cx = location[0]
            val cy = location[1]
            val finalRadius: Int = max(mainActivity.width, mainActivity.height)
            val anim = ViewAnimationUtils
                .createCircularReveal(mainActivity, cx, cy, 0f, finalRadius.toFloat())
            mainActivity.visibility = View.VISIBLE

            val colorValue = if (appModeTheme == PrefsKeys.DAY_MODE)
                ContextCompat.getColor(this@MainActivity, R.color.black_background_color)
            else
                ContextCompat.getColor(this@MainActivity, R.color.background_color)

            mainActivity.setBackgroundColor(colorValue)

            anim.duration = 600
            anim.start()
        }
    }
}