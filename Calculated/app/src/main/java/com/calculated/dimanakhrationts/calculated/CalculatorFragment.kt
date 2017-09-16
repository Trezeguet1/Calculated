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
    private val calculatorArgumentButtonClickListener = View.OnClickListener({
        v -> mListener?.onExpressionAppend(this.expressionFor(v))
    })

    private val calculatorActionButtonClickListener = View.OnClickListener({
        v ->  mListener?.onCalculatorAction(this.actionFor(v))
    })

    private var mListener: CalculatorFragmentListener? = null

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

            layout?.findViewById(R.id.oneBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.twoBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.threeBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.fourBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.fiveBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.sixBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.sevenBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.eightBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.nineBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.zeroBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.pointBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.leftBracketBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.rightBracketBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.plusBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.minusBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.divideBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.multiplyBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.sinBtn)?.setOnClickListener(this.calculatorArgumentButtonClickListener)
            layout?.findViewById(R.id.equalBtn)?.setOnClickListener(this.calculatorActionButtonClickListener)
        }
        return layout
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CalculatorFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement CalculatorFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
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
            R.id.eBtn -> "tg("
            R.id.tgBtn -> "ctg("
            R.id.powBtn -> "π"
            R.id.sqrtBtn -> "e"
            R.id.tghBtn -> "exp("
            R.id.sinBtn -> "sin("
            else -> ""
        }
    }

    private fun actionFor(button: View): Calculator.Action?{
        return when (button.id){
            R.id.equalBtn -> Calculator.Action.EQUAL
            else -> null
        }
    }

    interface CalculatorFragmentListener {
        fun onExpressionAppend(string: String)
        fun onCalculatorAction(action: Calculator.Action?)
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
}// Required empty public constructor
