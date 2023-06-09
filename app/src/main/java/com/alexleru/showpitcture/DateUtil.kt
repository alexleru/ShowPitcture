package com.alexleru.showpitcture

import org.joda.time.LocalDate


    fun getDate(minus: Int = 0): LocalDate {
        val date = LocalDate()
        return date.minusDays(minus)
    }
