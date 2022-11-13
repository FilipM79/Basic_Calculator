package org.hyperskill.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var calcDisplay: EditText
    private var x: Double = 0.0
    private var operand = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        calcDisplay = findViewById(R.id.editText)
        
        if (!calcDisplay.text.equals("0")) setupButton(R.id.button0)
        setupButton(R.id.button1)
        setupButton(R.id.button2)
        setupButton(R.id.button3)
        setupButton(R.id.button4)
        setupButton(R.id.button5)
        setupButton(R.id.button6)
        setupButton(R.id.button7)
        setupButton(R.id.button8)
        setupButton(R.id.button9)
        
        val clear : Button= findViewById(R.id.clearButton)
        val dot: Button = findViewById(R.id.dotButton)
        val equal: Button = findViewById(R.id.equalButton)
        
        val minus: Button = findViewById(R.id.subtractButton)
        val plus: Button = findViewById(R.id.addButton)
        val multiply: Button = findViewById(R.id.multiplyButton)
        val divide: Button = findViewById(R.id.divideButton)
    
        clear.setOnClickListener {
            calcDisplay.text.clear()
            calcDisplay.hint = "0"
        }
    
        dot.setOnClickListener { calcDisplay.text.append(".")  }
        
        equal.setOnClickListener {
            val y = calcDisplay.text.toString().toDouble()
            val result: Double
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
                    else -> 0.01
                }
                Log.i(result.toString(), "result form equal: $result")
                calcDisplay.text.append(result.toString())
            }
        }
        
        performOperation(minus)
        performOperation(plus)
        performOperation(multiply)
        performOperation(divide)
    }
    
    private fun performOperation(button: Button) {
        button.setOnClickListener {
            x = if (calcDisplay.text.isEmpty()) {
                0.0
            } else {
                calcDisplay.text.toString().toDouble()
            }
            Log.i(x.toString(), "iz perform operation x: $x")
            operand = button.text.toString()
            calcDisplay.text.clear()
            calcDisplay.hint = String.format("%s %s", x.toString(), operand)
        }
    }
    
    private fun Button.appendToDisplayOnClick() {
        calcDisplay.text.append(text)
    }
    
    private fun setupButton(@IdRes resId: Int): Button =
        findViewById<Button>(resId).apply {
            this.setOnClickListener {
                appendToDisplayOnClick()
            }
        }
}