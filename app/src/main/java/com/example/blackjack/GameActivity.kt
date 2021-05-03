package com.example.blackjack

import android.R.attr.*
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.blackjack.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.apply {
            playerPoints.text = "0"
            dealerPoints.text = "0"

            playerCardsField.removeAllViews()

            hitButton.setOnClickListener {

                val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )

                val iv = ImageView(this@GameActivity)
                iv.setBackgroundResource(R.drawable.queen_of_hearts)

                if (count != 0) {
                    lp.setMargins(-240, 0, 0, 0)
                    iv.layoutParams = lp
                }

                playerCardsField.addView(iv)
                count++
            }
        }
    }

    override fun onBackPressed() {
        startActivity<MainActivity>()
        finish()
    }
}