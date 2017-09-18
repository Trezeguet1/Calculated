package com.calculated.dimanakhrationts.calculated

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CalculatorFragment.CalculatorFragmentListener] interface
 * to handle interaction events.
 * Use the [CalculatorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class CalculatorFragment : Fragment() {

    private var mLayoutId: Int? = null


    private val mCalculatorArgumentButtonClickListener = View.OnClickListener({
        v ->
        mDelegate?.let {
            val expression = expressionFor(v)
            if (expression.isNumber()){
                if (it.isNumberEnabled()){
                    it.onExpressionAppend(expression)
                }
            }else if(expression.isBinaryOperator()){
                if (it.isBinaryOperatorEnabled()){
                    it.onExpressionAppend(expression)
                }
            }else if(expression.isMinusOperator()){
                if (it.isMinusEnabled()){
                    it.onExpressionAppend(expression)
                }
            }else if (expression.isPoint()){
                if (it.isPointEnabled()) {
                    it.onExpressionAppend(expression)
                }
            }else if(expression.isLeftBracket()){
                if (it.isLeftBracketEnabled()){
                    it.onExpressionAppend(expression)
                }
            } else if(expression.isRightBracket()){
                if (it.isRightBracketEnabled()){
                    it.onExpressionAppend(expression)
                }
            }else if(expression.isPowOperator()){
                if (it.isPowEnabled()){
                    it.onExpressionAppend(expression)
                }
            }else if(expression.isConstant()){
                if (it.isConstantEnabled()){
                    it.onExpressionAppend(expression)
                }
            }
            else{
                if (it.isUnaryOperatorEnabled()){
                    it.onExpressionAppend(expression)
                }
            }
        }

    })

    private val mCalculatorActionButtonClickListener = View.OnClickListener({
        v -> mDelegate?.onCalculatorAction(actionFor(v))
    })

    private var mDelegate: CalculatorFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mLayoutId = arguments.getInt(LAYOUT_ARG)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        var layout: View? = null
        mLayoutId?.let {
            layout = inflater?.inflate(it, container, false)
            layout?.findViewById(R.id.oneBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.twoBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.threeBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.fourBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.fiveBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.sixBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.sevenBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.eightBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.nineBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.zeroBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.pointBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.leftBracketBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.rightBracketBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.plusBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.minusBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.divideBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.multiplyBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.sinBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.cosBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.tgBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.acosBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.asinBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.atgBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.sinhBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.coshBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.powBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.sqrtBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.piBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.eBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.lnBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.expBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.tghBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.modBtn)?.setOnClickListener(mCalculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.equalBtn)?.setOnClickListener(mCalculatorActionButtonClickListener)
            layout?.findViewById(R.id.cancelBtn)?.setOnClickListener(mCalculatorActionButtonClickListener)
        }
        return layout
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CalculatorFragmentListener) {
            mDelegate = context
        } else {
            throw RuntimeException(context.toString() + " must implement CalculatorFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mDelegate = null
    }

    private fun expressionFor(button: View): String{
        return when (button.id){
            R.id.oneBtn -> "1"
            R.id.twoBtn -> "2"
            R.id.threeBtn -> "3"
            R.id.fourBtn -> "4"
            R.id.fiveBtn -> "5"
            R.id.sixBtn -> "6"
            R.id.sevenBtn -> "7"
            R.id.eightBtn -> "8"
            R.id.nineBtn -> "9"
            R.id.zeroBtn -> "0"
            R.id.plusBtn -> "+"
            R.id.minusBtn -> "-"
            R.id.multiplyBtn -> "*"
            R.id.divideBtn -> "/"
            R.id.leftBracketBtn -> "("
            R.id.rightBracketBtn -> ")"
            R.id.pointBtn -> "."
            R.id.sinBtn -> "sin("
            R.id.cosBtn -> "cos("
            R.id.tgBtn -> "tg("
            R.id.eBtn -> "e"
            R.id.asinBtn -> "asin("
            R.id.acosBtn -> "acos("
            R.id.atgBtn -> "atg("
            R.id.sinhBtn -> "sinh("
            R.id.coshBtn -> "cosh("
            R.id.tghBtn -> "tgh("
            R.id.modBtn -> "mod("
            R.id.lnBtn -> "log("
            R.id.powBtn -> "^"
            R.id.expBtn -> "e^"
            R.id.sqrtBtn -> "sqrt("
            R.id.piBtn -> "PI"
            else -> ""
        }
    }

    private fun actionFor(button: View): Calculator.Action?{
        return when (button.id){
            R.id.equalBtn -> Calculator.Action.EQUAL
            R.id.cancelBtn -> Calculator.Action.CANCEL
            else -> null
        }
    }

    interface CalculatorFragmentListener {
        fun onExpressionAppend(string: String)
        fun onCalculatorAction(action: Calculator.Action?)
        fun isUnaryOperatorEnabled(): Boolean
        fun isPointEnabled(): Boolean
        fun isBinaryOperatorEnabled(): Boolean
        fun isLeftBracketEnabled(): Boolean
        fun isRightBracketEnabled (): Boolean
        fun isPowEnabled (): Boolean
        fun isNumberEnabled(): Boolean
        fun isConstantEnabled (): Boolean
        fun isMinusEnabled (): Boolean
    }

    companion object {
        private val LAYOUT_ARG = "layout"

        fun newInstance(layoutId: Int): CalculatorFragment {
            val fragment = CalculatorFragment()
            val args = Bundle()
            args.putInt(LAYOUT_ARG, layoutId)
            fragment.arguments = args
            return fragment
        }
    }
}
