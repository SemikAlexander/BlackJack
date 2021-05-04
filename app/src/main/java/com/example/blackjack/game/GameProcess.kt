package com.example.blackjack.game

import com.example.blackjack.R.drawable.*

class GameProcess {
    fun createPackOfCard(): ArrayList<Cards> {
        val packOfCards: ArrayList<Cards> = ArrayList()
        packOfCards.addAll(
                listOf(
                        Cards(cardsID = ace_of_clubs, cardsValue = 1),
                        Cards(cardsID = ace_of_diamonds, cardsValue = 1),
                        Cards(cardsID = ace_of_hearts, cardsValue = 1),
                        Cards(cardsID = ace_of_spades, cardsValue = 1),

                        Cards(cardsID = two_of_clubs, cardsValue = 2),
                        Cards(cardsID = two_of_diamonds, cardsValue = 2),
                        Cards(cardsID = two_of_hearts, cardsValue = 2),
                        Cards(cardsID = two_of_spades, cardsValue = 2),

                        Cards(cardsID = three_of_clubs, cardsValue = 3),
                        Cards(cardsID = three_of_diamonds, cardsValue = 3),
                        Cards(cardsID = three_of_hearts, cardsValue = 3),
                        Cards(cardsID = three_of_spades, cardsValue = 3),

                        Cards(cardsID = four_of_clubs, cardsValue = 4),
                        Cards(cardsID = four_of_diamonds, cardsValue = 4),
                        Cards(cardsID = four_of_hearts, cardsValue = 4),
                        Cards(cardsID = four_of_spades, cardsValue = 4),

                        Cards(cardsID = five_of_clubs, cardsValue = 5),
                        Cards(cardsID = five_of_diamonds, cardsValue = 5),
                        Cards(cardsID = five_of_hearts, cardsValue = 5),
                        Cards(cardsID = five_of_spades, cardsValue = 5),

                        Cards(cardsID = six_of_clubs, cardsValue = 6),
                        Cards(cardsID = six_of_diamonds, cardsValue = 6),
                        Cards(cardsID = six_of_hearts, cardsValue = 6),
                        Cards(cardsID = six_of_spades, cardsValue = 6),

                        Cards(cardsID = seven_of_clubs, cardsValue = 7),
                        Cards(cardsID = seven_of_diamonds, cardsValue = 7),
                        Cards(cardsID = seven_of_hearts, cardsValue = 7),
                        Cards(cardsID = seven_of_spades, cardsValue = 7),

                        Cards(cardsID = eight_of_clubs, cardsValue = 8),
                        Cards(cardsID = eight_of_diamonds, cardsValue = 8),
                        Cards(cardsID = eight_of_hearts, cardsValue = 8),
                        Cards(cardsID = eight_of_spades, cardsValue = 8),

                        Cards(cardsID = nine_of_clubs, cardsValue = 9),
                        Cards(cardsID = nine_of_diamonds, cardsValue = 9),
                        Cards(cardsID = nine_of_hearts, cardsValue = 9),
                        Cards(cardsID = nine_of_spades, cardsValue = 9),

                        Cards(cardsID = ten_of_clubs, cardsValue = 10),
                        Cards(cardsID = ten_of_diamonds, cardsValue = 10),
                        Cards(cardsID = ten_of_hearts, cardsValue = 10),
                        Cards(cardsID = ten_of_spades, cardsValue = 10),

                        Cards(cardsID = jack_of_clubs, cardsValue = 10),
                        Cards(cardsID = jack_of_diamonds, cardsValue = 10),
                        Cards(cardsID = jack_of_hearts, cardsValue = 10),
                        Cards(cardsID = jack_of_spades, cardsValue = 10),

                        Cards(cardsID = queen_of_clubs, cardsValue = 10),
                        Cards(cardsID = queen_of_hearts, cardsValue = 10),
                        Cards(cardsID = queen_of_diamonds, cardsValue = 10),
                        Cards(cardsID = queen_of_spades, cardsValue = 10),

                        Cards(cardsID = king_of_clubs, cardsValue = 10),
                        Cards(cardsID = king_of_hearts, cardsValue = 10),
                        Cards(cardsID = king_of_diamonds, cardsValue = 10),
                        Cards(cardsID = king_of_spades, cardsValue = 10)
                )
        )

        packOfCards.shuffle()
        return packOfCards
    }

    private fun isPackEmpty(packOfCards: ArrayList<Cards>): Boolean = packOfCards.size == 0

    fun takeCardInHand(packOfCards: ArrayList<Cards>, hand: Hand) {
        if (!isPackEmpty(packOfCards)) {
            hand.addCard(packOfCards[0])
            packOfCards.remove(packOfCards[0])
        }
    }
}