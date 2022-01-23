package com.dicoding.made.submission.commons.ui.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): String? {
    val inputPattern = "yyyy-MM-dd"
    val outputPattern = "MMM dd, yyyy"

    val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
    val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

    val inputDate = inputFormat.parse(this)

    return inputDate?.let {
        outputFormat.format(it)
    }
}