package uk.co.botondbutuza.kodescanner.ui.main

import java.text.SimpleDateFormat
import java.util.*


class DateTimeFormatter {
    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.UK)

    /**
     * Inpriration from
     * https://stackoverflow.com/questions/3504986/extract-time-from-date-string
     */
    fun getTime(date: String): String {
        return SimpleDateFormat("H:mm", Locale.UK).format(format.parse(date))
    }

    /**
     * Inspiration from
     * https://stackoverflow.com/questions/14110621/calculate-difference-between-two-times-in-android
     */
    fun getDifference(from: String, to: String): String {
        val date1 = format.parse(from)
        val date2 = format.parse(to)

        val difference = date2.time - date1.time
        val days = (difference / (1000 * 60 * 60 * 24)).toInt()
        val hours = ((difference - 1000 * 60 * 60 * 24 * days) / (1000 * 60 * 60)).toInt()
        val min =
            (difference - 1000 * 60 * 60 * 24 * days - 1000 * 60 * 60 * hours).toInt() / (1000 * 60)
        return hours.toString() + "h " + min + "m"
    }
}