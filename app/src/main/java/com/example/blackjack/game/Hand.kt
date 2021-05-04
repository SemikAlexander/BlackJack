package com.example.blackjack.game

enum class TypeHand { PLAYER, DEALER }

class Hand (
        val typeHand: TypeHand,
        val cards: ArrayList<Cards>
) {
    fun addCard(card: Cards) {
        cards.add(card)
    }

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