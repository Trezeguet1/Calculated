package com.calculated.dimanakhrationts.calculated

import com.udojava.evalex.Expression
import java.math.BigDecimal

/**
 * Created by dima on 07.09.17.
 */

class Calculator{

    @Throws
    fun calculate(expressionString: String): BigDecimal {
        val expression = Expression(expressionString)
        val result = expression.eval()
        return result
    }

}