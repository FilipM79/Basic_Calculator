package org.hyperskill.calculator

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    
    private lateinit var calcDisplay: EditText
    private var x by Delegates.notNull<Double>()
    private var operand by Delegates.notNull<String>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        calcDisplay = findViewById(R.id.editText)
        val zero: Button = setupButton(R.id.button0)
        val one: Button = setupButton(R.id.button1)
        val two: Button = setupButton(R.id.button2)
        val three: Button = setupButton(R.id.button3)
        val four: Button = setupButton(R.id.button4)
        val five: Button = setupButton(R.id.button5)
        val six: Button = setupButton(R.id.button6)
        val seven: Button = setupButton(R.id.button7)
        val eight: Button = setupButton(R.id.button8)
        val nine: Button = setupButton(R.id.button9)
        
        val dot: Button = findViewById(R.id.dotButton)
        val clear : Button= findViewById(R.id.clearButton)
        val equal: Button = findViewById(R.id.equalButton)
        val minus: Button = findViewById(R.id.subtractButton)
        val plus: Button = findViewById(R.id.addButton)
        val multiply: Button = findViewById(R.id.multiplyButton)
        val divide: Button = findViewById(R.id.divideButton)
        
        clear.setOnClickListener { calcDisplay.text.clear() }
        dot.setOnClickListener { calcDisplay.text.append(".")  }
        
        minus.performOperation(R.id.subtractButton)
        plus.performOperation(R.id.addButton)
        multiply.performOperation(R.id.multiplyButton)
        divide.performOperation(R.id.divideButton)
        
        equal.setOnClickListener {
            val y = calcDisplay.text.toString().toDouble()
            val result: Double
    
            if (y.equals(0.0)) {
                calcDisplay.text.clear()
                calcDisplay.text.append("Div by 0!")
            } else {
                calcDisplay.text.clear()
                result = when (operand) {
                    "-" -> x - y
                    "+" -> x + y
                    "*" -> x * y
                    else -> x / y
                }
                calcDisplay.text.append(result.toString())
            }
        }
    }
    
    private fun Button.performOperation(@IdRes resId: Int): Button =
        findViewById<Button>(resId).apply {
            this.setOnClickListener {
                x = calcDisplay.text.toString().toFloat()
                operand = text.toString()
                calcDisplay.text.clear()
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