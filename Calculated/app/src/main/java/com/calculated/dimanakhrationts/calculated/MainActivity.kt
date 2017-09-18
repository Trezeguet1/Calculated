package com.calculated.dimanakhrationts.calculated

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal


class MainActivity : AppCompatActivity() , CalculatorFragment.CalculatorFragmentListener{

    var mCalculatorOperations: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculatorViewPager.adapter = CalculatorPagerAdapter(supportFragmentManager)
        calculatorEditText.showSoftInputOnFocus = false
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.getStringArrayList("currentExpression")?.let {
            mCalculatorOperations = it
        }
        savedInstanceState?.getCharSequence("history")?.let {
            calculatorTextView.text = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putStringArrayList("currentExpression", mCalculatorOperations)
        outState?.putCharSequence("history", calculatorTextView.text)
    }

    override fun onExpressionAppend(string: String) {
        mCalculatorOperations.add(string)
        setCalculatorText()
    }

    private fun setCalculatorText() {
        calculatorEditText.setText("")
        for (operation in mCalculatorOperations) {
            calculatorEditText.append(operation)
        }
    }

    override fun onCalculatorAction(action: Calculator.Action?) {
        when (action){
            Calculator.Action.EQUAL -> {
                var expression = calculatorEditText.text.toString()
                try {
                    val result = Calculator.calculate(appendBracketsIfNeed(expression))

                if (calculatorTextView.text != "") {
                    calculatorTextView.append("\n")
                }
                calculatorTextView.append("${appendBracketsIfNeed(expression)}=$result")
                calculatorEditText.setText("$result")
                calculatorEditText.setSelection(calculatorEditText.text.count())
                scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
                mCalculatorOperations.clear()
                if (result != BigDecimal(0)){
                    mCalculatorOperations.add(result.toString())
                }
                }catch (error: Exception){
                    mCalculatorOperations.clear()
                    calculatorEditText.setText(getString(R.string.errorString))
                }
            }
            Calculator.Action.CANCEL -> {
               if (!mCalculatorOperations.isEmpty()) {
                   mCalculatorOperations.removeAt(mCalculatorOperations.lastIndex)
               }
                setCalculatorText()
            }
            else -> return
        }
    }

    override fun isUnaryOperatorEnabled(): Boolean {
        if (mCalculatorOperations.isEmpty()){
            return true
        }else{
            val last = mCalculatorOperations.last()
            return last.isBinaryOperator() || last.isLeftBracket()
        }
    }

    override fun isPointEnabled(): Boolean {
        if (mCalculatorOperations.isEmpty()){
            return false
        }else{
            val fullExpression = StringBuilder()
            for (expression in mCalculatorOperations){
                fullExpression.append(expression)
            }
            val indexOfLastPoint =  fullExpression.toString().indexOfLast { it.toString().isPoint() }
            return indexOfLastPoint <= fullExpression.toString().indexOfLast { it.toString().isBinaryOperator() } &&
                    mCalculatorOperations.last().isNumber()
        }
    }

    override fun isMinusEnabled(): Boolean {
        if (mCalculatorOperations.isEmpty()){
            return true
        }else{
            val last = mCalculatorOperations.last()
            return !last.isMinusOperator() && !last.isPoint()
        }
    }

    override fun isBinaryOperatorEnabled(): Boolean {
        if (mCalculatorOperations.isEmpty()){
            return false
        }else{
            val last = mCalculatorOperations.last()
            return last.isNumber() || last.isRightBracket() || last.isConstant() || last.isBigDecimal()
        }
    }

    override fun isLeftBracketEnabled(): Boolean {
        if (mCalculatorOperations.isEmpty()){
            return true
        }else{
            val last = mCalculatorOperations.last()
            return last.isBinaryOperator() || last.isLeftBracket()
        }
    }

    override fun isRightBracketEnabled(): Boolean {
        if (mCalculatorOperations.isEmpty()){
            return false
        }else{
            val fullExpression = StringBuilder()
            for (expression in mCalculatorOperations){
                fullExpression.append(expression)
            }
            val last = mCalculatorOperations.last()
            return !last.isLeftBracket() && !last.isUnaryOperator() && leftRightDifference(fullExpression.toString()) > 0
        }
    }

    private fun leftRightDifference(expression: String): Int{
        val leftCount = expression.toString().count { it.toString().isLeftBracket() }
        val rightCount =  expression.toString().count { it.toString().isRightBracket() }
        return (leftCount - rightCount)
    }

    private fun appendBracketsIfNeed(expression: String): String{
        val fullExpression = StringBuilder()
        fullExpression.append(expression)
        for (i in 0 until leftRightDifference(expression)){
            fullExpression.append(")")
        }
        return  fullExpression.toString()
    }

    override fun isPowEnabled(): Boolean {
        if (mCalculatorOperations.isEmpty()){
            return false
        }else{
            val last = mCalculatorOperations.last()
            return last.isNumber() || last.isConstant() || last.isRightBracket()
        }
    }

    override fun isNumberEnabled(): Boolean {
        if (mCalculatorOperations.isEmpty()){
            return true
        }else{
            val last = mCalculatorOperations.last()
            val fullExpression = StringBuilder()
            for (expression in mCalculatorOperations){
                fullExpression.append(expression)
            }
            return !last.isRightBracket() &&
                    fullExpression.toString().indexOfLast { !it.toString().isNumber() && !it.toString().isPoint() } > fullExpression.lastIndex -  12
        }
    }

    override fun isConstantEnabled(): Boolean {
        if (mCalculatorOperations.isEmpty()){
            return true
        }else{
            val last = mCalculatorOperations.last()
            return !last.isNumber() && !last.isRightBracket() && !last.isConstant()
        }
    }
}
