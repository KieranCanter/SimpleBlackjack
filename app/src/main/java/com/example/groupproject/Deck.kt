package com.example.groupproject

class Deck {

    private var deck : ArrayList<Card>
    private var suits : Array<String> = arrayOf("Clubs", "Spades", "Hearts", "Diamonds")
    private var values : Array<String> = arrayOf("Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King")

    constructor() {
        deck = ArrayList<Card>()
        for (suit in suits) {
            for (value in values) {
                deck.add(Card(suit, value))
            }
        }
    }

    fun shuffleDeck() {
        this.deck.shuffle()
    }

    fun dealOneCard() : Card {
        var dealtCard : Card = this.deck[0]
        this.deck.removeAt(0)
        return dealtCard
    }
}