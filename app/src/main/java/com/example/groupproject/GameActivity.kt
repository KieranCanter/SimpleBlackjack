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
    private lateinit var btnHit: Button
    private lateinit var btnStay: Button
    private lateinit var btnMenu: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        //Initial start w/ 1 card each
        blackjack.playerHit()
        blackjack.dealerHit()
        btnHit= findViewById(R.id.hit)
        btnStay= findViewById(R.id.stay)
        btnMenu= findViewById(R.id.mainMenu)
        btnHit.setOnClickListener(this)
        btnStay.setOnClickListener(this)
        btnMenu.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.hit -> {
                Log.w("PLAYER SUM", blackjack.playerSum.toString())
                if (blackjack.playerSum > 21) {
                    Toast.makeText(this, "YOU LOSE!", Toast.LENGTH_LONG).show()
                    this.finish()
                    overridePendingTransition(R.anim.slide_from_left, 0)
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
                    overridePendingTransition(R.anim.slide_from_left, 0)
                }
                else if ( blackjack.dealerSum > 16 && blackjack.dealerSum == blackjack.playerSum) {
                    Toast.makeText(this, "DRAW!!", Toast.LENGTH_LONG).show()
                    this.finish()
                    overridePendingTransition(R.anim.slide_from_left, 0)
                    }
                else if (blackjack.dealerSum > blackjack.playerSum) {
                    Toast.makeText(this, "YOU LOSE!", Toast.LENGTH_LONG).show()
                    this.finish()
                    overridePendingTransition(R.anim.slide_from_left, 0)
                }
            }
            R.id.mainMenu-> {
                this.finish()
                overridePendingTransition(R.anim.slide_from_left, 0)
            }
        }
    }
}
