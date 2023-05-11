package com.example.groupproject

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity(), OnClickListener {
    private val blackjack: Blackjack = Blackjack()
    private lateinit var dealerHand : TextView
    private lateinit var userHand : TextView
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        //Initial start w/ 1 card each
        blackjack.playerHit()
        blackjack.dealerHit()
        dealerHand = findViewById(R.id.dealerHand)
        userHand = findViewById(R.id.userHand)
        updateDealerHand()
        updatePlayerHand()
        btn1= findViewById(R.id.hit)
        btn2= findViewById(R.id.stay)
        btn3= findViewById(R.id.mainMenu)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.hit -> {
                blackjack.playerHit()
                updatePlayerHand()
                if (blackjack.playerSum > 21) {
                    Toast.makeText(this, "YOU LOSE!", Toast.LENGTH_LONG).show()
                    blackjack.reset()
                    this.finish()
                    overridePendingTransition(R.anim.slide_from_left, 0)
                }
            }
            R.id.stay -> {
                while (blackjack.dealerChoice()) {
                    blackjack.dealerHit()
                    updateDealerHand()
                }
                when (blackjack.didPlayerWin()) {
                    "win" -> Toast.makeText(this, "YOU WIN!", Toast.LENGTH_LONG).show()
                    "draw" -> Toast.makeText(this, "DRAW!!", Toast.LENGTH_LONG).show()
                    "loss" -> Toast.makeText(this, "YOU LOSE!", Toast.LENGTH_LONG).show()
                    else -> {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    blackjack.reset()
                    this.finish()
                    overridePendingTransition(R.anim.slide_from_left, 0)
                }, 3000)
            }
            R.id.mainMenu-> {
                this.finish()
                overridePendingTransition(R.anim.slide_from_left, 0)
            }
        }
    }
    private fun updateDealerHand() {
        var printDealerHand = "| "
        for (card in blackjack.dealerHand) {
            printDealerHand = printDealerHand + card.getValue() + " | "
        }
        dealerHand.text = printDealerHand + "     " + blackjack.dealerSum.toString()
    }
    private fun updatePlayerHand() {
        var printUserHand = "| "
        for (card in blackjack.playerHand) {
            printUserHand = printUserHand + card.getValue() + " | "
        }
        userHand.text = printUserHand + "     " + blackjack.playerSum.toString()
    }
}
