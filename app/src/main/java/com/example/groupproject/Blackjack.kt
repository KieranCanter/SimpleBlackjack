package com.example.groupproject

class Blackjack {

    private var deck : Deck
    private var playerAces : Int = 0
    private var dealerAces : Int = 0
    private var playerSum : Int = 0
    private var dealerSum : Int = 0
    private lateinit var playerHand : ArrayList<Card>
    private lateinit var dealerHand : ArrayList<Card>

    constructor() {
        this.deck = Deck()
        this.deck.shuffleDeck()
        this.playerHand = ArrayList<Card>()
        this.dealerHand = ArrayList<Card>()
        dealerHit()
        playerHit()
    }

    fun playerHit(){
        playerHand.add(deck.dealOneCard())
        playerSum = valueCount(playerHand, true)
        //Over 21 check with aces
        for(i in 0..playerAces){
            if (playerSum > 21){
                playerSum -= 10
            }
        }
    }
    fun dealerHit(){
        dealerHand.add(deck.dealOneCard())
        dealerSum = valueCount(dealerHand, false)
        //Over 21 check with aces
        for(i in 0..dealerAces){
            if (dealerSum > 21){
                dealerSum -= 10
            }
        }
    }

    fun valueCount(hand: ArrayList<Card>, player: Boolean): Int{
        var sum = 0;
        for (card in hand) {
            if(card.getValue() == "Ace"){
                if(player) {playerAces+=1} else {dealerAces += 1}
                sum += 11
            }
            sum += toPoint(card.getValue())
        }
        return sum;
    }

    fun toPoint(value: String): Int{
        return when (value) {
            "Two" -> 2
            "Three" -> 3
            "Four" -> 4
            "Five" -> 5
            "Six" -> 6
            "Seven" -> 7
            "Eight" -> 8
            "Nine" -> 9
            else -> {
                10
            }
        }
    }

    //Return false = Dealer will stop playing
    //Return true = Dealer wants to keep playing
    fun dealerChoice(): Boolean {
        if(playerSum > 21) return false
        //Draw Case
        if( dealerSum > 16 && dealerSum == playerSum) {
            return false
        }
        //Dealer wants more cards
        if( dealerSum < 21 && dealerSum < playerSum ) {
            dealerHit()
            return true
        }
        return false
    }

    fun didPlayerWin(): String {
        if(playerSum == dealerSum) return "draw"
        if(playerSum > 21) return "loss"
        if(dealerSum > 21) return "win"
        if(playerSum > dealerSum) return "win"
        else return "loss"
    }
}
