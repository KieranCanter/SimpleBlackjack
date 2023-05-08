package com.example.groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toast.makeText
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

class MainActivity : AppCompatActivity() {

    private var email : String = ""
    private lateinit var emailInput : EditText
    private lateinit var adLayout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailInput = findViewById(R.id.enter_email)

        var initializer : AdInitializer = AdInitializer()
        MobileAds.initialize(this, initializer)
        var adView : AdView = AdView(this)
        var adSize : AdSize = AdSize(AdSize.FULL_WIDTH, AdSize.AUTO_HEIGHT)
        adView.setAdSize(adSize)
        var adUnitId : String = "ca-app-pub-3940256099942544/6300978111"
        adView.adUnitId = adUnitId
        var builder : AdRequest.Builder = AdRequest.Builder()
        builder.addKeyword("poker")
        builder.addKeyword("blackjack")
        var request : AdRequest = builder.build()
        adLayout = findViewById(R.id.ad_view)
        adLayout.addView(adView)
        try {
            adView.loadAd(request)
        } catch (e : Exception) {
            Log.w("MainActivity", "Ad failed to load")
        }
    }

    fun saveEmail(v : View) {
        email = emailInput.text.toString()
        Toast.makeText(this, "EMAIL SAVED", Toast.LENGTH_SHORT).show()
    }

    fun toFinancial(v : View) {
        if (email == "") {
            // Display toast asking for email input
            var toast : Toast = makeText(this, "PLEASE ENTER EMAIL", Toast.LENGTH_LONG)
            toast.show()
            return
        } else {
            var myIntent : Intent = Intent(this, FinancialActivity::class.java)
            myIntent.putExtra("Email", email)
            this.startActivity(myIntent)
        }
    }

    fun toGame(v : View) {
        if (email == "") {
            // Display toast asking for email input
            var toast : Toast = makeText(this, "PLEASE ENTER EMAIL", Toast.LENGTH_LONG)
            toast.show()
            return
        } else {
            var myIntent : Intent = Intent(this, GameActivity::class.java)
            myIntent.putExtra("Email", email)
            this.startActivity(myIntent)
        }
    }

    class AdInitializer : OnInitializationCompleteListener {
        override fun onInitializationComplete(status: InitializationStatus) {
            Log.w("MainActivity", "Ad Initialization Done")
        }
    }
}
