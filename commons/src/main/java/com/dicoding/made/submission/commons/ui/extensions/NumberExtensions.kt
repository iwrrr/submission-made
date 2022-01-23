package com.dicoding.made.submission.commons.ui.extensions

import java.math.RoundingMode

fun Double.toRate(): String {
    return toBigDecimal().setScale(1, RoundingMode.UP).toFloat().toString()
}
