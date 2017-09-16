package com.calculated.dimanakhrationts.calculated

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , CalculatorFragment.CalculatorFragmentListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculatorViewPager.adapter = CalculatorPagerAdapter(supportFragmentManager)
        calculatorEditText.showSoftInputOnFocus = false
    }

    override fun onExpressionAppend(string: String) {
        when (calculatorEditText.text.toString() == "0" && string.isNumber()){
            true -> {
                calculatorEditText.setText(string)
                calculatorEditText.setSelection(calculatorEditText.text.length)
            }
            false -> {
                val cursorPosition = calculatorEditText.selectionEnd
                calculatorEditText.text.insert(cursorPosition, string)
            }
        }

    }

    override fun onCalculatorAction(action: Calculator.Action?) {
        when (action){
            Calculator.Action.EQUAL -> {
                val expression = calculatorEditText.text.toString()
                val result = Calculator.calculate(expression)
                if (this.calculatorTextView.text != "") {
                    this.calculatorTextView.append("\n")
                }
                this.calculatorTextView.append("$expression=$result")
                this.calculatorEditText.setText("$result")
                this.calculatorEditText.setSelection(calculatorEditText.text.count())
                this.scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
            }
            else -> return
        }
    }

}
