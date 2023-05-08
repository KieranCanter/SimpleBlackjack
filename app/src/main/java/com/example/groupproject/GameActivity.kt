package com.example.groupproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity(), OnClickListener {
    /*
        choose bet?
        initiate blackjack stuff
        let user play with GUI, update blackjack for each input
        let dealer play depending on result of user (while(dealerChoice())
        display win or lose
     */
    private val blackjack: Blackjack = Blackjack()
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        //Initial start w/ 1 card each
        blackjack.playerHit()
        blackjack.dealerHit()
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
                Log.w("PLAYER SUM", blackjack.playerSum.toString())
                if (blackjack.playerSum > 21) {
                    Toast.makeText(this, "YOU LOSE!", Toast.LENGTH_LONG).show()
                    this.finish()
                }
                else {
                    blackjack.playerHit()
                }
            }
            R.id.stay -> {
                Log.w("DEALER SUM", blackjack.dealerSum.toString())
                while(blackjack.dealerChoice());
                Log.w("DEALER SUM", blackjack.dealerSum.toString())
                if (blackjack.dealerSum > 21) {
                    Toast.makeText(this, "YOU WIN!", Toast.LENGTH_LONG).show()
                    this.finish()
                }
                else if ( blackjack.dealerSum > 16 && blackjack.dealerSum == blackjack.playerSum) {
                    Toast.makeText(this, "DRAW!!", Toast.LENGTH_LONG).show()
                    this.finish()
                    }
                else if (blackjack.dealerSum > blackjack.playerSum) {
                    Toast.makeText(this, "YOU LOSE!", Toast.LENGTH_LONG).show()
                    this.finish()
                }
            }
            R.id.mainMenu-> {
                this.finish()
            }
        }
    }
}