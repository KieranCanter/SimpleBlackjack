package com.example.groupproject

import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.math.roundToInt

class FinancialActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var email : String
    private var depositSelected : Boolean = false
    private var withdrawSelected : Boolean = true

    private lateinit var options : Spinner
    private lateinit var transactionAmountET : EditText
    private lateinit var currentBalanceTV : TextView

    private var currentBalance : Double = 0.0
    private var transactionAmount : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financial)

        this.email = intent.extras?.getString("Email")!!

        this.options = findViewById<Spinner>(R.id.options)
        var categories : ArrayList<String> = ArrayList<String>()
        categories.add("WITHDRAW")
        categories.add("DEPOSIT")

        var dataAdapter : ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.spinner_list, categories)
        dataAdapter.setDropDownViewResource(R.layout.spinner_list_dropdown)
        options.adapter = dataAdapter
        options.onItemSelectedListener = this

        transactionAmountET = findViewById<EditText>(R.id.enter_amount)
        currentBalanceTV = findViewById<TextView>(R.id.current_balance)
    }

    fun sendReceipt() {
        currentBalanceTV.text = "Current Balance: \n$" + ((currentBalance * 100.0).roundToInt() / 100.0).toString()
        Log.w("MainActivity", "Current Balance is " + currentBalance)
        Log.w("MainActivity", "Last Transaction Amount Was " + transactionAmount)
        var emailIntent : Intent = Intent(Intent.ACTION_SEND)
        emailIntent.setType("text/plain")
        if (withdrawSelected) {
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RECEIPT FOR WITHDRAWAL")
        } else {
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "RECEIPT FOR DEPOSIT")
        }
        var recipient : Array<String> = arrayOf(email)
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipient)
        if (withdrawSelected) {
            emailIntent.putExtra(Intent.EXTRA_TEXT, "User withdrew $" + transactionAmount)
        } else {
            emailIntent.putExtra(Intent.EXTRA_TEXT, "User deposited $" + transactionAmount)
        }
        startActivity(Intent.createChooser(emailIntent, "Send receipt"))
    }
    
    fun performTransaction(v : View) {
        if (transactionAmount <= 0.0) {
            var toast : Toast = Toast.makeText(this, "INPUT AMOUNT", Toast.LENGTH_LONG)
            toast.show()
            return
        }

        if (withdrawSelected) {
            if (currentBalance >= transactionAmount) {
                currentBalance -= transactionAmount
                sendReceipt()
                Toast.makeText(this, "$" + NumberFormat.getInstance().format(transactionAmount) + " WITHDRAWN", Toast.LENGTH_LONG).show()
            } else {
                var toast : Toast = Toast.makeText(this, "INSUFFICIENT FUNDS", Toast.LENGTH_LONG)
                toast.show()
            }
        } else if (depositSelected) {
            currentBalance += transactionAmount
            sendReceipt()
            Toast.makeText(this, "$" + NumberFormat.getInstance().format(transactionAmount) + " DEPOSITED", Toast.LENGTH_LONG).show()
        } else {
            Log.w("MainAcitivity", "UH OH")
        }
    }

    fun confirmAmount(v : View) {
        var desiredAmount : String = transactionAmountET.text.toString()
        if (desiredAmount == "") {
            var toast : Toast = Toast.makeText(this, "INPUT AMOUNT", Toast.LENGTH_LONG)
            toast.show()
            return
        } else {
            transactionAmount = desiredAmount.toDouble()
            Toast.makeText(this, "AMOUNT CONFIRMED", Toast.LENGTH_LONG).show()
        }
    }

    fun returnToMainMenu(v : View) {
        this.finish()
        overridePendingTransition(R.anim.slide_from_left, 0)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var itemSelected = parent?.getItemAtPosition(position).toString()

        Log.w("MainActivity", itemSelected)

        if (itemSelected == "WITHDRAW") {
            withdrawSelected = true
            depositSelected = false
        } else {
            withdrawSelected = false
            depositSelected = true
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.w("MainActivity", "Nothing selected")
    }
}
