package com.calculated.dimanakhrationts.calculated

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by dima on 07.09.17.
 */

class CalculatorPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        when (position){
            0 -> fragment = CalculatorFragment.newInstance(layoutId = R.layout.fragment_basic_calculator)
            else -> fragment = CalculatorFragment.newInstance(R.layout.fragment_advanced_calculator)
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

}
