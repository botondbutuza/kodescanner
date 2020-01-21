package uk.co.botondbutuza.kodescanner.ui.main

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import uk.co.botondbutuza.kodescanner.ui.helpers.DateTimeFormatter

class DateTimeFormatterTest {

    private lateinit var underTest: DateTimeFormatter

    @Before
    fun setup() {
        underTest = DateTimeFormatter()
    }

    @Test
    fun getTime() {
        val result = underTest.getTime(startDate)
        assertEquals("10:55", result)
    }

    @Test
    fun getDifference() {
        val result = underTest.getDifference(startDate, endDate)
        assertEquals("0h 45m", result)
    }

    companion object {
        private val startDate = "2020-11-01T10:55"
        private val endDate = "2020-11-01T11:40"
    }
}