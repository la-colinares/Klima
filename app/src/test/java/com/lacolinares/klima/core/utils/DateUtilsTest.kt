package com.lacolinares.klima.core.utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalTime

class DateUtilsTest {

    @Test
    fun `returns false when current time is before 6 PM`() {
        val time = LocalTime.of(17, 59)
        assertFalse(DateUtils.isCurrentTimePastSixPM(time))
    }

    @Test
    fun `returns false when current time is exactly 6 PM`() {
        val time = LocalTime.of(18, 0)
        assertFalse(DateUtils.isCurrentTimePastSixPM(time))
    }

    @Test
    fun `returns true when current time is after 6 PM`() {
        val time = LocalTime.of(18, 1)
        assertTrue(DateUtils.isCurrentTimePastSixPM(time))
    }

}