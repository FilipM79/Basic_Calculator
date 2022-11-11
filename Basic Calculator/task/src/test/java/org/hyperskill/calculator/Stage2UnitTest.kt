package org.hyperskill.calculator

import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Stage2UnitTest {

    private val activityController = Robolectric.buildActivity(MainActivity::class.java)

    @Test
    fun testShouldCheckButtonsWork() {
        val activity = activityController.setup().get()
        val button0 = activity.findViewById<Button>(R.id.button0)
        val button1 = activity.findViewById<Button>(R.id.button1)
        val button2 = activity.findViewById<Button>(R.id.button2)
        val button3 = activity.findViewById<Button>(R.id.button3)
        val button4 = activity.findViewById<Button>(R.id.button4)
        val button5 = activity.findViewById<Button>(R.id.button5)
        val button6 = activity.findViewById<Button>(R.id.button6)
        val button7 = activity.findViewById<Button>(R.id.button7)
        val button8 = activity.findViewById<Button>(R.id.button8)
        val button9 = activity.findViewById<Button>(R.id.button9)
        val editText = activity.findViewById<EditText>(R.id.editText)

        button1.performClick()
        button2.performClick()
        button3.performClick()
        button4.performClick()
        button5.performClick()
        button6.performClick()
        button7.performClick()
        button8.performClick()
        button9.performClick()
        button0.performClick()
        val actualText = editText.text.toString()


        val message = "Clicked all the buttons, but something went wrong"
        assertEquals(message, "1234567890", actualText)
    }

    @Test
    fun testShouldCheckClearButtonWorks() {
        val activity = activityController.setup().get()
        val clearButton = activity.findViewById<Button>(R.id.clearButton)
        val button1 = activity.findViewById<Button>(R.id.button1)
        val editText = activity.findViewById<EditText>(R.id.editText)
        button1.performClick()
        button1.performClick()
        button1.performClick()
        clearButton.performClick()
        val actualText = editText.text.toString()

        val message = "Clicked the Clear button, but the EditText returned something else"
        assertEquals(message, "0", actualText)
    }

    @Test
    fun testShouldCheckZeroButtonLock() {
        val activity = activityController.setup().get()
        val dotButton = activity.findViewById<Button>(R.id.dotButton)
        val button0 = activity.findViewById<Button>(R.id.button0)
        val editText = activity.findViewById<EditText>(R.id.editText)
        val clearButton = activity.findViewById<Button>(R.id.clearButton)
        clearButton.performClick()
        button0.performClick()
        button0.performClick()
        button0.performClick()
        button0.performClick()
        button0.performClick()
        val actualText = editText.text.toString()
        val message = "Clicked the button0 multiple times, got more or less than one 0."
        assertEquals(message, "0", actualText)
    }

    @Test
    fun testShouldCheckDotButtonLock() {
        val activity = activityController.setup().get()
        val dotButton = activity.findViewById<Button>(R.id.dotButton)
        val button0 = activity.findViewById<Button>(R.id.button0)
        val editText = activity.findViewById<EditText>(R.id.editText)
        val clearButton = activity.findViewById<Button>(R.id.clearButton)
        clearButton.performClick()
        button0.performClick()
        dotButton.performClick()
        dotButton.performClick()
        dotButton.performClick()
        val actualText = editText.text.toString()
        val message = "Clicked the dotButton multiple times, got more or less than one dot."
        assertEquals(message, "0.", actualText)
    }

    @Test
    fun testShouldCheckNoZeroesToTheLeft() {
        val activity = activityController.setup().get()
        val button1 = activity.findViewById<Button>(R.id.button1)
        val button0 = activity.findViewById<Button>(R.id.button0)
        val editText = activity.findViewById<EditText>(R.id.editText)
        val clearButton = activity.findViewById<Button>(R.id.clearButton)
        clearButton.performClick()
        button0.performClick()
        button1.performClick()
        val actualText = editText.text.toString()
        val message = "Clicked the zero and button1, got a wrong result."
        assertEquals(message, "1", actualText)
    }

    @Test
    fun testShouldCheckNoDotsWithoutZero() {
        val activity = activityController.setup().get()
        val dotButton = activity.findViewById<Button>(R.id.dotButton)
        val button1 = activity.findViewById<Button>(R.id.button1)
        val editText = activity.findViewById<EditText>(R.id.editText)
        val clearButton = activity.findViewById<Button>(R.id.clearButton)
        clearButton.performClick()
        dotButton.performClick()
        button1.performClick()
        val actualText = editText.text.toString()
        val message = "Clicked the dotButton and button1, got a wrong result."
        assertEquals(message, "0.1", actualText)
    }

}