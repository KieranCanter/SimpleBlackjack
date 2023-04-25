package com.example.groupproject

class Card {

    private var suit : String
    private var value : Int

    constructor(suit : String, value : Int) {
        this.suit = suit
        this.value = value
    }

    fun getSuit() : String {
        return this.suit
    }

    fun getValue() : Int {
        return this.value
    }
}