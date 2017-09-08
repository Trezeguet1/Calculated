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
            0 -> fragment = BasicCalculatorFragment.newInstance()
            else -> fragment = AdvancedCalculatorFragment.newInstance()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

}
