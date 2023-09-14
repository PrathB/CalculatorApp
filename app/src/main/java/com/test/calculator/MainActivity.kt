package com.test.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
//  Op is created to change the output screen
    private var Op : TextView? = null
    private var lastnum = false
    private var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Op = findViewById(R.id.opScreen)
    }
    fun digitClick(view: View) {
//      Op is appended by the text in the button (view) which is pressed
        Op?.append((view as Button).text)
        lastnum = true
        lastDot = false
    }

//    output screen is cleared using clr fxn
    fun clr(view: View) {
        Op?.text = ""
        lastnum = false
        lastDot = false
    }

//   point(.) is added if lastchar is not a point
    fun dotClick(view: View) {
        if(lastnum && !lastDot){
            Op?.append(".")
            lastnum = false
            lastDot = true
        }
    }

//  isOperatorAdded fxn will return true if input problem contains an operator
private fun isOperatorAdded(opScreen : String) : Boolean{
        return if(opScreen.startsWith("-")){
            false
        }
        else{
            (opScreen.contains("+") || opScreen.contains("-")
                || opScreen.contains("*") || opScreen.contains("/"))
        }
    }

//  onOperator is called when an operator is clicked
//  if last char is not a number or if an operator already exists ,the operator pressed does nothing
//  else it adds the operator to statement
    fun operatorClick(view: View){
        Op?.text?.let{
            if(lastnum && !isOperatorAdded(it.toString())) {
                Op?.append((view as Button ).text)
                lastnum = false
                lastDot = false
            }
        }
    }

    private fun remove0(ans:String) : String {
        var fans = ans
        if(ans.endsWith(".0")){
            fans = ans.substring(0,ans.length-2)
        }
        return fans
    }


    fun equalClick(view: View){
        if(lastnum){
            var str: String = Op?.text.toString()
            var prefix = ""
            try{
                if(str.startsWith("-")){
                    prefix = "-"
                    str = str.substring(1)
                }
                when {
                    str.contains("-") -> {
                        val operands = str.split("-")
                        if (prefix.isEmpty()) {
                            Op?.text =
                                remove0((operands[0].toDouble() - operands[1].toDouble()).toString())
                        } else {
                            Op?.text =
                                remove0(((-(operands[0].toDouble())) + operands[1].toDouble()).toString())
                        }
                    }

                    str.contains("+") -> {
                        val operands = str.split("+")
                        if (prefix.isEmpty()) {
                            Op?.text =
                                remove0((operands[0].toDouble() + operands[1].toDouble()).toString())
                        } else {
                            Op?.text =
                                remove0(((-(operands[0].toDouble())) + operands[1].toDouble()).toString())
                        }
                    }

                    str.contains("*") -> {
                        val operands = str.split("*")
                        if (prefix.isEmpty()) {
                            Op?.text =
                                remove0((operands[0].toDouble() * operands[1].toDouble()).toString())
                        } else {
                            Op?.text =
                                remove0(((-(operands[0].toDouble())) * operands[1].toDouble()).toString())
                        }
                    }

                    else -> {
                        val operands = str.split("/")
                        if (prefix.isEmpty()) {
                            Op?.text =
                                remove0((operands[0].toDouble() / operands[1].toDouble()).toString())
                        } else {
                            Op?.text =
                                remove0(((-(operands[0].toDouble())) / operands[1].toDouble()).toString())
                        }
                    }
                }
            }catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}