package com.example.groupproject

class Card {

    private var suit : String
    private var value : String

    constructor(suit : String, value : String) {
        this.suit = suit
        this.value = value
    }

    fun getSuit() : String {
        return this.suit
    }

    fun getValue() : String {
        return this.value
    }
}