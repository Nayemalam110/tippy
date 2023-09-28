package com.example.tinppy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.util.Objects
import kotlin.math.log

private const val TAG = "MainActivity"
private const val Intial_Tip = 15
class MainActivity : AppCompatActivity() {
    private lateinit var base: EditText
    private lateinit var seekBarData: SeekBar
    private lateinit var tipp: TextView
    private lateinit var tipAmount: TextView
    private lateinit var total: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        base = findViewById(R.id.baseId)
        tipp = findViewById(R.id.tipp)
        tipAmount = findViewById(R.id.textView4)
        total = findViewById(R.id.textView5)
        seekBarData = findViewById(R.id.seekBarId)

        seekBarData.progress = Intial_Tip
        tipp.text = "$Intial_Tip%"

        seekBarData.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
//Log.i(TAG, "progress Change $p1")
                tipp.text = p1.toString() +"%"

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
        base.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
             computeTipTotal()
            }

        })

    }

    private fun computeTipTotal() {
        if(base.text.isEmpty()){
            tipAmount.text =""
            total.text = ""
            return
        }
        val baseAmount = base.text.toString().toDouble()
        val tipPercent = seekBarData.progress
        val tipamount = baseAmount*tipPercent  / 100
        val totalamount = baseAmount+tipamount
        tipAmount.text = "%.2f".format(tipamount)
        total.text = "%.2f".format(totalamount)

    }
}