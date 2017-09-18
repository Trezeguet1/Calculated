package com.calculated.dimanakhrationts.calculated

import java.math.BigDecimal

/**
 * Created by dima on 15.09.17.
 */

fun String.isNumber(): Boolean{
    this.toIntOrNull()?.let {
        return it >=0 && it <= 9
    }
    return false
}

fun String.isBigDecimal(): Boolean{
    try {
        val bigDecimal = BigDecimal(this)
        return true
    }catch (error: Exception){
        return false
    }
}

fun String.isConstant(): Boolean{
    return this == "e" || this == "PI"
}

fun String.isPowOperator(): Boolean{
    return this == "^"
}

fun String.isUnaryOperator(): Boolean{
    return this == "sin(" || this == "cos(" || this == "tg("
            || this == "asin(" || this == "acos(" || this == "atg("
            || this == "sinh(" || this == "cosh(" || this == "tgh("
            || this == "log(" || this == "mod(" || this == "sqrt("
}

fun String.isBinaryOperator(): Boolean{
    return "+/*".contains(this) && this.length == 1
}

fun String.isMinusOperator(): Boolean{
    return this == "-"
}

fun String.isLeftBracket(): Boolean{
    return this == "("
}

fun String.isRightBracket(): Boolean{
    return this == ")"
}

fun String.isPoint(): Boolean{
    return this == "."
}