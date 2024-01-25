package br.com.ascence.anotei.utils.date

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateHelper {

    private val dateFormatter = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
    fun formatDateToString(date: Date): String =
        dateFormatter.format(date)
}