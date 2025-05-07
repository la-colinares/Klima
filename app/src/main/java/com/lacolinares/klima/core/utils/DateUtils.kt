package com.lacolinares.klima.core.utils

import java.time.LocalTime

object DateUtils {

    fun isCurrentTimePastSixPM(currentTime: LocalTime = LocalTime.now()): Boolean {
        val sixPM = LocalTime.of(18, 0)
        return currentTime.isAfter(sixPM)
    }
}