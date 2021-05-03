package com.example.blackjack.game

import com.example.blackjack.R.drawable.*
import com.example.blackjack.game.Rank.*
import com.example.blackjack.game.Suit.*

enum class Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, BLANK
}

enum class Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES, PLAYER, DEALER
}

abstract class Card {

    abstract fun rank(): Rank
    abstract fun suit(): Suit

    fun cardsValue(): Int {
        return when (rank()) {
            ACE -> 1
            TWO -> 2
            THREE -> 3
            FOUR -> 4
            FIVE -> 5
            SIX -> 6
            SEVEN -> 7
            EIGHT -> 8
            NINE -> 9
            TEN, JACK, QUEEN, KING -> 10
            else -> 0
        }
    }

    val cardsID: Int
        get() {
            val array: IntArray = when (suit()) {
                CLUBS -> CLUBS_ID
                DIAMONDS -> DIAMONDS_ID
                HEARTS -> HEARTS_ID
                SPADES -> SPADES_ID

                PLAYER -> return red_back
                DEALER -> return blue_back
            }
            return array[rank().ordinal]
        }

    companion object {
        private val CLUBS_ID = intArrayOf (
                ace_of_clubs, two_of_clubs, three_of_clubs, four_of_clubs, five_of_clubs,
                six_of_clubs, seven_of_clubs, eight_of_clubs, nine_of_clubs, ten_of_clubs,
                jack_of_clubs, queen_of_clubs, king_of_clubs
        )
        private val DIAMONDS_ID = intArrayOf (
                ace_of_diamonds, two_of_diamonds, three_of_diamonds, four_of_diamonds,
                five_of_diamonds, six_of_diamonds, seven_of_diamonds, eight_of_diamonds,
                nine_of_diamonds, ten_of_diamonds, jack_of_diamonds, queen_of_diamonds,
                king_of_diamonds
        )
        private val HEARTS_ID = intArrayOf (
                ace_of_hearts, two_of_hearts, three_of_hearts, four_of_hearts, five_of_hearts,
                six_of_hearts, seven_of_hearts, eight_of_hearts, nine_of_hearts, ten_of_hearts,
                jack_of_hearts, queen_of_hearts, king_of_hearts
        )
        private val SPADES_ID = intArrayOf (
                ace_of_spades, two_of_spades, three_of_spades, four_of_spades, five_of_spades,
                six_of_spades, seven_of_spades, eight_of_spades, nine_of_spades, ten_of_spades,
                jack_of_spades, queen_of_spades, king_of_spades
        )
    }
}