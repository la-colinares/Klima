package com.lacolinares.klima.util

import java.time.LocalTime

object DateUtils {

    fun isCurrentTimePastSixPM(): Boolean {
        val currentTime = LocalTime.now()
        val sixPM = LocalTime.of(18, 0) // 6 PM
        return currentTime.isAfter(sixPM)
    }

    fun getTimeAgo(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp

        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            seconds < 60 -> "Just now"
            minutes < 60 -> "$minutes minute(s) ago"
            hours < 24 -> "$hours hour(s) ago"
            days < 7 -> "$days day(s) ago"
            days < 30 -> "${days / 7} week(s) ago"
            days < 365 -> "${days / 30} month(s) ago"
            else -> "${days / 365} year(s) ago"
        }
    }
}