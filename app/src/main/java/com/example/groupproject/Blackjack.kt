package com.example.groupproject

import android.os.Handler
import android.os.Looper
import android.util.Log

class Blackjack {

    private var deck : Deck
    private var playerAces : Int = 0
    private var dealerAces : Int = 0
    var playerSum : Int = 0
    var dealerSum : Int = 0
    var playerHand : ArrayList<Card>
    var dealerHand : ArrayList<Card>

    init {
        this.deck = Deck()
        this.deck.shuffleDeck()
        this.playerHand = ArrayList()
        this.dealerHand = ArrayList()
    }

    fun playerHit(){
        playerHand.add(deck.dealOneCard())
        playerSum = valueCount(playerHand, true)
        //Over 21 check with aces
        for(i in 1..playerAces){
            if (playerSum > 21){
                playerSum -= 10
            }
        }
    }
    fun dealerHit(){
        dealerHand.add(deck.dealOneCard())
        dealerSum = valueCount(dealerHand, false)
        //Over 21 check with aces
        for(i in 1..dealerAces){
            if (dealerSum > 21){
                dealerSum -= 10
            }
        }
    }

    private fun valueCount(hand: ArrayList<Card>, player: Boolean): Int{
        var sum = 0
        playerAces = 0
        dealerAces = 0
        for (card in hand) {
            if(card.getValue() == "Ace"){
                if(player) {playerAces+=1} else {dealerAces += 1}
                sum += 11
            }
            else sum += toPoint(card.getValue())
        }
        return sum
    }

    private fun toPoint(value: String): Int{
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
        //Draw Case
        if( dealerSum > 16 && dealerSum == playerSum) {
            return false
        }
        return dealerSum < 21 && dealerSum < playerSum
    }

    fun didPlayerWin(): String {
        if(playerSum == dealerSum) return "draw"
        if(playerSum > 21) return "loss"
        if(dealerSum > 21) return "win"
        return if(playerSum > dealerSum) "win"
        else "loss"
    }

    fun reset() {
        deck = Deck()
        deck.shuffleDeck()
        playerAces = 0
        dealerAces = 0
        playerSum = 0
        dealerSum = 0
        playerHand = ArrayList()
        dealerHand = ArrayList()
    }
}
