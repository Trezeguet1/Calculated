package com.calculated.dimanakhrationts.calculated

/**
 * Created by dima on 15.09.17.
 */

fun String.isNumber(): Boolean{
    return "1234567890".contains(this) && this.length == 1
}