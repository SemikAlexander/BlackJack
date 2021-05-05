package com.example.blackjack.game

enum class TypeHand { PLAYER, DEALER }
enum class Results { WON, LOST, DRAW }

class Hand (typeHand: TypeHand) {
    val cards: ArrayList<Cards> = ArrayList()

    fun takeCardInHand(packOfCards: ArrayList<Cards>) {
        if (packOfCards.size != 0) {
            cards.add(packOfCards[0])
            packOfCards.remove(packOfCards[0])
        }
    }

    fun isTwoCardsSameValue(): Boolean {
        if (cards.size != cards.distinct().count())
            return true
        return false
    }

    fun isBlackJack(): Boolean = getPoints() == 21

    fun getPoints(): Int {
        var points = 0
        for (i in 0 until cards.size)
            points += cards[i].cardsValue

        return points
    }

    fun removeAllCards() {
        cards.clear()
    }
}