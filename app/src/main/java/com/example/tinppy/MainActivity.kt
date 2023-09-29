package com.example.tinppy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator

private const val TAG = "MainActivity"
private const val Intial_Tip = 15

class MainActivity : AppCompatActivity() {
    private lateinit var base: EditText
    private lateinit var seekBarData: SeekBar
    private lateinit var tipp: TextView
    private lateinit var tipAmount: TextView
    private lateinit var total: TextView
    private lateinit var comment: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        base = findViewById(R.id.baseId)
        tipp = findViewById(R.id.tipp)
        tipAmount = findViewById(R.id.textView4)
        total = findViewById(R.id.textView5)
        seekBarData = findViewById(R.id.seekBarId)
        comment = findViewById(R.id.commentId)
        seekBarData.progress = Intial_Tip
        tipp.text = "$Intial_Tip%"
       updateTIp(Intial_Tip)
        seekBarData.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
//Log.i(TAG, "progress Change $p1")
                tipp.text = p1.toString() + "%"
                updateTIp(p1)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //  Log.i(TAG, "onStartTrackingTouch by Alam $p0")
                // seekBarData.setBackgroundColor(Color.red(2))
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                // Log.i(TAG, "onStopTrackingTouch by Nayem $p0")
                computeTipTotal()
            }

        })
        base.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                computeTipTotal()

            }

        })

    }

    @SuppressLint("RestrictedApi")
    private fun updateTIp(p1: Int) {
       var tipDes = when(p1){

           in 0..9 -> "poor"
           in 10..14 ->"Good"
           in 15..30 -> "Great"
           else -> "amazing"
       }
        comment.text = tipDes
        val ss = ArgbEvaluator().evaluate(
            p1.toFloat()/seekBarData.max,ContextCompat.getColor(this,R.color.red),
            ContextCompat.getColor(this,R.color.green)
        ) as Int
comment.setTextColor(ss)
    }

    private fun computeTipTotal() {
        if (base.text.isEmpty()) {
            tipAmount.text = ""
            total.text = ""
            return
        }
        val baseAmount = base.text.toString().toDouble()
        val tipPercent = seekBarData.progress
        val tipamount = baseAmount * tipPercent / 100
        val totalamount = baseAmount + tipamount
        tipAmount.text = "%.2f".format(tipamount)
        total.text = "%.2f".format(totalamount)

    }
}