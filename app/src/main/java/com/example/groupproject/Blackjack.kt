package com.example.groupproject

class Blackjack {

    private lateinit var deck : Deck
    private lateinit var playerHand : ArrayList<Card>
    private lateinit var dealerHand : ArrayList<Card>

    constructor() {
        this.deck = Deck()
        this.deck.shuffleDeck()
        this.playerHand = ArrayList<Card>()
        this.dealerHand = ArrayList<Card>()
        dealDealerOneCard()
        dealDealerOneCard()
        dealPlayerOneCard()
        dealPlayerOneCard()
    }
    
    fun dealerTurn() {
        while (dealerShouldTakeCard()) {
            dealDealerOneCard()
        }
    }

    fun dealerShouldTakeCard() : Boolean {
        var possibleScores : ArrayList<Int> = getDealerHandScore()

        if (possibleScores.size == 1) {
            if (possibleScores[0] <= 16) {
                return true
            }
        }

        if (possibleScores[1] <= 17) {
            return true
        }

        return false
    }
    
    fun dealDealerOneCard() {
        this.dealerHand.add(this.deck.dealOneCard())
    }
    
    fun dealPlayerOneCard() {
        this.playerHand.add(this.deck.dealOneCard())
    }
    
    fun getDealerHand() : ArrayList<Card> {
        return this.dealerHand
    }

    fun getPlayerHand() : ArrayList<Card> {
        return this.playerHand
    }
    
    fun getDealerHandScore() : ArrayList<Int> {
        var acePresent : Boolean = false
        var handValue : Int = 0
        val toReturn : ArrayList<Int> = ArrayList<Int>()
        for (card in this.dealerHand) {
            if (card.getValue() == 1) {
                acePresent = true
            }
        }
        for (card in this.dealerHand) {
            handValue += card.getValue()
        }
        toReturn.add(handValue)
        if (acePresent == false) {
            return toReturn
        }
        if (handValue + 10 <= 21) {
            toReturn.add(handValue + 10)
        }
        return toReturn
    }
    
    fun getPlayerHandScore() : ArrayList<Int> {
        var acePresent : Boolean = false
        var handValue : Int = 0
        val toReturn : ArrayList<Int> = ArrayList<Int>()
        for (card in this.playerHand) {
            if (card.getValue() == 1) {
                acePresent = true
            }
        }
        for (card in this.playerHand) {
            handValue += card.getValue()
        }
        toReturn.add(handValue)
        if (acePresent == false) {
            return toReturn
        }
        if (handValue + 10 <= 21) {
            toReturn.add(handValue + 10)
        }
        return toReturn
    }
    
    fun bestDealerScore() : Int {
        var possibleScores : ArrayList<Int> = getDealerHandScore()

        if (possibleScores.size == 1) {
            if (possibleScores[0] > 21) {
                return -1
            }

            return possibleScores[0]
        }

        var score1 = possibleScores[0]
        var score2 = possibleScores[1]

        if (score1 < 22 && score2 < 22) {
            return score2
        } else if (score1 < 22) {
            return score1
        } else {
            return -1
        }
    }

    fun bestPlayerScore() : Int {
        var possibleScores : ArrayList<Int> = getPlayerHandScore()

        if (possibleScores.size == 1) {
            if (possibleScores[0] > 21) {
                return -1
            }

            return possibleScores[0]
        }

        var score1 = possibleScores[0]
        var score2 = possibleScores[1]

        if (score1 < 22 && score2 < 22) {
            return score2
        } else if (score1 < 22) {
            return score1
        } else {
            return -1
        }
    }
    
}
