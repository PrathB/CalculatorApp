package com.test.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
//  Op is created to change the output screen
private var Op : TextView? = null
    private var lastAlph = true
    private var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Op = findViewById(R.id.opScreen)
    }
    fun digitClick(view: View) {
//      Op is appended by the text in the button (view) which is pressed
        Op?.append((view as Button).text)
        if((view as Button).text in "12345678900"){
            lastAlph = true
            lastDot = false
        }
        else{
            lastAlph = false

        }
    }

    fun clr(view: View) {
        Op?.text = ""
    }

    fun dotClick(view: View) {
        if(lastAlph && !lastDot){
            Op?.append(".")
            lastAlph = false
            lastDot = true
        }
    }

}