package org.hyperskill.calculator

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.sign

class MainActivity : AppCompatActivity() {
    
    private lateinit var calcDisplay: EditText
    private var x = 0.0
    private var operand = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        calcDisplay = findViewById(R.id.editText)
        val zero: Button = findViewById(R.id.button0)

        zero.setOnClickListener {
            if (calcDisplay.text.toString() != "0") zero.appendToDisplayOnClick()
        }
        setupButton(R.id.button1)
        setupButton(R.id.button2)
        setupButton(R.id.button3)
        setupButton(R.id.button4)
        setupButton(R.id.button5)
        setupButton(R.id.button6)
        setupButton(R.id.button7)
        setupButton(R.id.button8)
        setupButton(R.id.button9)
        
        val clear: Button = findViewById(R.id.clearButton)
        val dot: Button = findViewById(R.id.dotButton)
        val equal: Button = findViewById(R.id.equalButton)
        
        val minus: Button = findViewById(R.id.subtractButton)
        val plus: Button = findViewById(R.id.addButton)
        val multiply: Button = findViewById(R.id.multiplyButton)
        val divide: Button = findViewById(R.id.divideButton)
    
        calcDisplay.text.clear()
        calcDisplay.text.append("0")
    
        clear.setOnClickListener {
            calcDisplay.text.clear()
            calcDisplay.hint = "0"
            calcDisplay.text.append("0")
        }
    
        dot.setOnClickListener {
            if (!calcDisplay.text.toString().contains(".")) {
                if (calcDisplay.text.toString() == "-") {
                    calcDisplay.text.append("0.")
                } else {
                    calcDisplay.text.append(".")
                }
            }
        }

        equalize(equal)
        performOperation(minus, equal)
        performOperation(plus, equal)
        performOperation(multiply, equal)
        performOperation(divide,equal)
    }

    private fun equalize(equal: Button) {
        var result: Double

        equal.setOnClickListener {
            val y = calcDisplay.text.toString().toDouble()

            if (y.equals(0.0)) {
                calcDisplay.text.clear()
                calcDisplay.text.append("Div by 0!")
            } else {
                calcDisplay.text.clear()
                result = when (operand) {
                    "-" -> x - y
                    "+" -> x + y
                    "*" -> x * y
                    "/" -> x / y
                    else -> 0.123456789
                }
                
                val resultString = if ((result * 100.0).toInt() == ((result).roundToInt() * 100)) {
                                        result.toInt().toString()
                                    } else {
                                        ((result * 100.0).roundToInt() / 100.0).toString()
                                    }
                
                calcDisplay.text.append(resultString)
            }
        }
    }
    
    private fun performOperation(button: Button, equal: Button) {
        button.setOnClickListener {
            val displayString = calcDisplay.text.toString()

            if (displayString == "0" || displayString == "0.0" && button.text.toString() == "-") {
                calcDisplay.text.clear()
                calcDisplay.text.append("-")
            } else {
                x = calcDisplay.text.toString().toDouble()
                operand = button.text.toString()
                calcDisplay.text.clear()
                
                val xToString = if ((x * 100.0).toInt() == ((x).roundToInt() * 100)) {
                                    x.toInt().toString()
                                } else {
                                    ((x * 100.0).roundToInt() / 100.0).toString()
                                }
//                calcDisplay.hint = xToString
                calcDisplay.hint = String.format("%1s %2s", xToString, operand)

                equalize(equal)
            }
        }
    }
    
    private fun Button.appendToDisplayOnClick() {
        val displayString = calcDisplay.text.toString()
        if (displayString == "-") {
            if (text.toString() == "0") {
                calcDisplay.text.clear()
                calcDisplay.append("-")
            } else {
                calcDisplay.text.append(text)
            }
        } else if (displayString == "0") {
            calcDisplay.text.clear()
            calcDisplay.append(text)
        } else {
            calcDisplay.append(text)
        }
    }
    
    private fun setupButton(@IdRes resId: Int): Button =
        findViewById<Button>(resId).apply {
            this.setOnClickListener {
                appendToDisplayOnClick()
            }
        }
}