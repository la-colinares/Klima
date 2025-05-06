package com.lacolinares.klima.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.toFormattedDate(
    pattern: String,
    zoneId: ZoneId = ZoneId.systemDefault(),
    locale: Locale = Locale.ENGLISH
): String {
    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(this), zoneId)
    val formatter = DateTimeFormatter.ofPattern(pattern, locale)
    return dateTime.format(formatter)
}

fun Long.toDayOfWeek(pattern: String): String {
    return this.toFormattedDate(pattern = pattern)
}