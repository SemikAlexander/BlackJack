package com.example.blackjack.game

import com.example.blackjack.R.drawable.*
import com.example.blackjack.game.Results.*

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
    fun isGameWon(userHand: Hand, dealerHand: Hand, packOfCards: ArrayList<Cards>): Results {
        when {
            dealerHand.getPoints() > userHand.getPoints() -> return LOST
            userHand.getPoints() > 21 -> return LOST
            userHand.cards.size == 2 -> {
                dealerHand.takeCardInHand(packOfCards)
                return if (dealerHand.getPoints() > userHand.getPoints())
                    LOST
                else
                    WON
            }
            else -> {
                for (i in 0..2) {
                    dealerHand.takeCardInHand(packOfCards)
                    if (dealerHand.getPoints() < 21) {
                        if (dealerHand.getPoints() > userHand.getPoints())
                            return LOST
                    }
                    if (dealerHand.getPoints() > 21)
                        return WON
                }
                if (dealerHand.getPoints() == userHand.getPoints())
                    return DRAW
                return WON
            }
        }
    }
}