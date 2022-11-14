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
    private var x: Double = 0.0
    private var operand = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        calcDisplay = findViewById(R.id.editText)

        val zero: Button = findViewById<Button>(R.id.button0)

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
    
        clear.setOnClickListener {
            calcDisplay.text.clear()
            calcDisplay.text.append("0")
//            calcDisplay.hint = "0"
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

        minus.setOnClickListener {
            if (calcDisplay.text.toString() == "0" || calcDisplay.text.toString() == "0.0") {
                calcDisplay.text.clear()
                calcDisplay.text.append("-")
            } else {
                performOperation(minus, equal)
            }
        }

        performOperation(plus, equal)
        performOperation(multiply, equal)
        performOperation(divide,equal)
    }

    private fun equalize(equal: Button): Double {
        var result: Double = 0.0

        equal.setOnClickListener {
            val y = calcDisplay.text.toString().toDouble()
            Log.i(x.toString(), "iz equal x: $x")

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
                Log.i(result.toString(), "result from equal: $result")
                val roundedResult = (result * 100.0).roundToInt() / 100.0
                calcDisplay.text.append(roundedResult.toString())

            }
        }
        return result
    }
    
    private fun performOperation(button: Button, equal: Button) {

        button.setOnClickListener {
//
//            if (button.text.toString() == "-" && calcDisplay.text.isEmpty()) {
//                calcDisplay.text.append("-")
//            }
            if (calcDisplay.text.isEmpty()) {
                x = 0.0

            } else {
                x = calcDisplay.text.toString().toDouble()
//                if (operand == "-") x = -x

                Log.i(x.toString(), "iz perform operation x: $x")
                operand = button.text.toString()
                calcDisplay.text.clear()

                calcDisplay.hint = String.format("%1s %2s", x.toString(), operand)

                equalize(equal)
            }
        }
    }
    
    private fun Button.appendToDisplayOnClick() {

        if (calcDisplay.text.toString() == "0") calcDisplay.text.clear()
        calcDisplay.text.append(text)
    }
    
    private fun setupButton(@IdRes resId: Int): Button =
        findViewById<Button>(resId).apply {
            this.setOnClickListener {
                appendToDisplayOnClick()
            }
        }
}