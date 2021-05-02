package com.example.blackjack.game

import android.content.Context
import com.example.blackjack.R

import com.google.auto.value.AutoValue
import java.util.*


@AutoValue
abstract class Card (val context: Context) {
    override fun toString(): String {
        val rank = rank().toString().toLowerCase()
        val suit = suit().toString().toLowerCase()
        return java.lang.String.format(Locale.US, "%s of %s", rank, suit)
    }

    abstract fun rank(): Rank
    abstract fun suit(): Suit

    fun value(): Int {
        return when (rank()) {
            Rank.ACE -> 1
            Rank.TWO -> 2
            Rank.THREE -> 3
            Rank.FOUR -> 4
            Rank.FIVE -> 5
            Rank.SIX -> 6
            Rank.SEVEN -> 7
            Rank.EIGHT -> 8
            Rank.NINE -> 9
            Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING -> 10
            else -> 0
        }
    }

    val imageID: Int
        get() {
            val array: IntArray = when (suit()) {
                Suit.CLUBS -> CLUBS_ID
                Suit.DIAMONDS -> DIAMONDS_ID
                Suit.HEARTS -> HEARTS_ID
                Suit.SPADES -> SPADES_ID
                Suit.PLAYER -> return R.drawable.red_back
                Suit.DEALER -> return R.drawable.blue_back
            }
            return array[rank().ordinal]
        }

    enum class Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, BLANK
    }

    enum class Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES, PLAYER, DEALER
    }

    companion object {
        val dealerBlank = create(Rank.BLANK, Suit.DEALER)
        val playerBlank = create(Rank.BLANK, Suit.PLAYER)

        private val CLUBS_ID = intArrayOf(R.drawable.ace_of_clubs, R.drawable.two_of_clubs, R.drawable.three_of_clubs, R.drawable.four_of_clubs, R.drawable.five_of_clubs, R.drawable.six_of_clubs, R.drawable.seven_of_clubs, R.drawable.eight_of_clubs, R.drawable.nine_of_clubs, R.drawable.ten_of_clubs, R.drawable.jack_of_clubs, R.drawable.queen_of_clubs, R.drawable.king_of_clubs)
        private val DIAMONDS_ID = intArrayOf(R.drawable.ace_of_diamonds, R.drawable.two_of_diamonds, R.drawable.three_of_diamonds, R.drawable.four_of_diamonds, R.drawable.five_of_diamonds, R.drawable.six_of_diamonds, R.drawable.seven_of_diamonds, R.drawable.eight_of_diamonds, R.drawable.nine_of_diamonds, R.drawable.ten_of_diamonds, R.drawable.jack_of_diamonds, R.drawable.queen_of_diamonds, R.drawable.king_of_diamonds)
        private val HEARTS_ID = intArrayOf(R.drawable.ace_of_hearts, R.drawable.two_of_hearts, R.drawable.three_of_hearts, R.drawable.four_of_hearts, R.drawable.five_of_hearts, R.drawable.six_of_hearts, R.drawable.seven_of_hearts, R.drawable.eight_of_hearts, R.drawable.nine_of_hearts, R.drawable.ten_of_hearts, R.drawable.jack_of_hearts, R.drawable.queen_of_hearts, R.drawable.king_of_hearts)
        private val SPADES_ID = intArrayOf(R.drawable.ace_of_spades, R.drawable.two_of_spades, R.drawable.three_of_spades, R.drawable.four_of_spades, R.drawable.five_of_spades, R.drawable.six_of_spades, R.drawable.seven_of_spades, R.drawable.eight_of_spades, R.drawable.nine_of_spades, R.drawable.ten_of_spades, R.drawable.jack_of_spades, R.drawable.queen_of_spades, R.drawable.king_of_spades)

        private fun create(rank: Rank?, suit: Suit?): Card {
            return AutoValue_Card(rank, suit)
        }
    }
}
