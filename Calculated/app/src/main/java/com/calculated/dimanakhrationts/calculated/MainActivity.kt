package com.calculated.dimanakhrationts.calculated

import android.content.Context
import android.hardware.input.InputManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), BasicCalculatorFragment.OnFragmentInteractionListener, AdvancedCalculatorFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculatorViewPager.adapter = CalculatorPagerAdapter(supportFragmentManager)
        calculatorEditText.showSoftInputOnFocus = false
        Log.v("result", Calculator().calculate("3.4*sin(30)^2").toString())
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

}
