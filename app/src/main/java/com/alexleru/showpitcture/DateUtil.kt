package com.alexleru.showpitcture

import org.joda.time.LocalDate
import java.text.SimpleDateFormat


fun getDate(minus: Int = 0): LocalDate {
    val date = LocalDate()
    return date.minusDays(minus)
}

fun LocalDate.formatDate(): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy")
    return formatter.format(this.toDate())
}
