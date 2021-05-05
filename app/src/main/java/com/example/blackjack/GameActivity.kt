package com.example.blackjack

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import androidx.appcompat.app.AppCompatActivity
import com.example.blackjack.databinding.ActivityGameBinding
import com.example.blackjack.game.Cards
import com.example.blackjack.game.GameProcess
import com.example.blackjack.game.Hand
import com.example.blackjack.game.TypeHand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    private var bidGame = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            playerPoints.text = "0"
            dealerPoints.text = "0"

            playerCardsField.removeAllViews()
            dealersCardsField.removeAllViews()
            chipsField.removeAllViews()

            splitButton.visibility = View.GONE

            bidGame = intent.getIntExtra(PrefsKeys.BID, 0)
            when (bidGame) {
                1 -> chosenPlayerChip.setImageResource(R.drawable.chip_one)
                5 -> chosenPlayerChip.setImageResource(R.drawable.chip_five)
                25 -> chosenPlayerChip.setImageResource(R.drawable.chip_twenty_five)
                50 -> chosenPlayerChip.setImageResource(R.drawable.chip_fifty)
                100 -> chosenPlayerChip.setImageResource(R.drawable.chip_hundred)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val gameProcess = GameProcess()

        val packOfCards: ArrayList<Cards> = gameProcess.createPackOfCard()

        val userHand = Hand(TypeHand.PLAYER)
        val dealerHand = Hand(TypeHand.DEALER)

        binding.apply {

            userHand.takeCardInHand(packOfCards)
            userHand.takeCardInHand(packOfCards)

            dealerHand.takeCardInHand(packOfCards)
            dealerHand.takeCardInHand(packOfCards)

            putPlayerCardsOnTable(userHand)
            putDealersCardsOnTable(dealerHand, true)

            if (userHand.isBlackJack()) {
                putDealersCardsOnTable(dealerHand, false)
            }

            hitButton.setOnClickListener {
                playerCardsField.removeAllViews()

                userHand.takeCardInHand(packOfCards)
                putPlayerCardsOnTable(userHand)

                playerPoints.text = userHand.getPoints().toString()
            }

            doubleButton.setOnClickListener {
                //Doubles the bid
                when (bidGame) {
                    1 -> doubleChip.setImageResource(R.drawable.chip_one)
                    5 -> doubleChip.setImageResource(R.drawable.chip_five)
                    25 -> doubleChip.setImageResource(R.drawable.chip_twenty_five)
                    50 -> doubleChip.setImageResource(R.drawable.chip_fifty)
                    100 -> doubleChip.setImageResource(R.drawable.chip_hundred)
                }

                playerCardsField.removeAllViews()

                userHand.takeCardInHand(packOfCards)
                putPlayerCardsOnTable(userHand)

                playerPoints.text = userHand.getPoints().toString()

                dealersCardsField.removeAllViews()

                if (gameProcess.isGameWon(userHand, dealerHand, packOfCards))
                    toast("Game won!")
                else
                    toast("Game lost!")

                putDealersCardsOnTable(dealerHand, false)

                GlobalScope.launch {
                    delay(1000)
                    launch(Dispatchers.Main) {
                        startActivity<MainActivity>()
                        finish()
                    }
                }

                return@setOnClickListener
            }

            stayButton.setOnClickListener {
                dealersCardsField.removeAllViews()

                if (gameProcess.isGameWon(userHand, dealerHand, packOfCards))
                    toast("Game won!")
                else
                    toast("Game lost!")

                putDealersCardsOnTable(dealerHand, false)

                GlobalScope.launch {
                    delay(1000)
                    launch(Dispatchers.Main) {
                        startActivity<MainActivity>()
                        finish()
                    }
                }

                return@setOnClickListener
            }
        }
    }

    private fun putPlayerCardsOnTable(hand: Hand) {
        binding.apply {
            val lp = LayoutParams(250, MATCH_PARENT)

            for (i in 0 until hand.cards.size) {
                val iv = ImageView(this@GameActivity)
                iv.setBackgroundResource(hand.cards[i].cardsID)

                if (i != 0) lp.setMargins(-195, 0, 0, 0)

                iv.layoutParams = lp
                playerCardsField.addView(iv)
            }

            playerPoints.text = hand.getPoints().toString()
        }
    }

    private fun putDealersCardsOnTable(hand: Hand, startRound: Boolean) {
        binding.apply {
            val lp = LayoutParams(250, MATCH_PARENT)

            for (i in 0 until hand.cards.size) {
                val iv = ImageView(this@GameActivity)

                if (i != 0) {
                    lp.setMargins(-195, 0, 0, 0)
                    if (startRound)
                        iv.setBackgroundResource(R.drawable.red_back)
                    else
                        iv.setBackgroundResource(hand.cards[i].cardsID)
                }
                else iv.setBackgroundResource(hand.cards[i].cardsID)

                iv.layoutParams = lp
                dealersCardsField.addView(iv)
            }
            if (!startRound)
                dealerPoints.text = hand.getPoints().toString()
        }
    }

    override fun onBackPressed() {
        startActivity<MainActivity>()
        finish()
    }
}