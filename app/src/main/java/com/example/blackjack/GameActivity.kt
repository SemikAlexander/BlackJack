package com.example.blackjack

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import androidx.appcompat.app.AppCompatActivity
import com.example.blackjack.databinding.ActivityGameBinding
import com.example.blackjack.game.Cards
import com.example.blackjack.game.GameProcess
import com.example.blackjack.game.Hand
import com.example.blackjack.game.TypeHand


class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            playerPoints.text = "0"
            dealerPoints.text = "0"

            playerCardsField.removeAllViews()
            dealersCardsField.removeAllViews()
        }
    }

    override fun onStart() {
        super.onStart()

        val gameProcess = GameProcess()

        val packOfCards: ArrayList<Cards> = gameProcess.createPackOfCard()

        val userCards: ArrayList<Cards> = ArrayList()
        val userHand = Hand(TypeHand.PLAYER, userCards)

        val dealerCards: ArrayList<Cards> = ArrayList()
        val dealerHand = Hand(TypeHand.DEALER, dealerCards)

        binding.apply {

            gameProcess.takeCardInHand(packOfCards, userHand)
            gameProcess.takeCardInHand(packOfCards, userHand)

            putPlayerCardsOnTable(userCards, userHand)

            gameProcess.takeCardInHand(packOfCards, dealerHand)
            gameProcess.takeCardInHand(packOfCards, dealerHand)

            /*Dealer's put his cards on table (start round) */
            val lp = LayoutParams(250, MATCH_PARENT)

            for (i in 0 until dealerCards.size) {
                val iv = ImageView(this@GameActivity)

                if (i != 0) {
                    lp.setMargins(-195, 0, 0, 0)
                    iv.setBackgroundResource(R.drawable.red_back)
                }
                else iv.setBackgroundResource(dealerCards[i].cardsID)

                iv.layoutParams = lp
                dealersCardsField.addView(iv)
            }

            hitButton.setOnClickListener {
                playerCardsField.removeAllViews()

                gameProcess.takeCardInHand(packOfCards, userHand)
                putPlayerCardsOnTable(userCards, userHand)

                playerPoints.text = userHand.getPoints().toString()
            }

            doubleButton.setOnClickListener {
                //Doubles the bid

                playerCardsField.removeAllViews()

                gameProcess.takeCardInHand(packOfCards, userHand)
                putPlayerCardsOnTable(userCards, userHand)

                playerPoints.text = userHand.getPoints().toString()
            }

            stayButton.setOnClickListener {
                //After clicking on the stop, the dealer opens a second hidden card and can still take two additional cards
            }
        }
    }

    private fun putPlayerCardsOnTable(cards: ArrayList<Cards>, hand: Hand) {
        binding.apply {
            val lp = LayoutParams(250, MATCH_PARENT)

            for (i in 0 until cards.size) {
                val iv = ImageView(this@GameActivity)
                iv.setBackgroundResource(cards[i].cardsID)

                if (i != 0) lp.setMargins(-195, 0, 0, 0)

                iv.layoutParams = lp
                playerCardsField.addView(iv)
            }

            playerPoints.text = hand.getPoints().toString()
        }
    }

    override fun onBackPressed() {
        startActivity<MainActivity>()
        finish()
    }
}