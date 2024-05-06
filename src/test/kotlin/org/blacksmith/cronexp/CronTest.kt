package org.blacksmith.cronexp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class CronTest {
    @Test
    fun shouldCreateCron() {
        val cron = Cron(
            minute = listOf(1, 3),
            hour = listOf(7, 8, 9),
            dayOfMonth = listOf(15, 17, 31),
            month = listOf(1, 9, 12),
            dayOfWeek = listOf(5, 7),
            command = "/bin/zip"
        )
        assertTrue(cron.getMinuteList() == listOf(1, 3))
        assertTrue(cron.getHourList() == listOf(7, 8, 9))
        assertTrue(cron.getDayOfMonthList() == listOf(15, 17, 31))
        assertTrue(cron.getMonthList() == listOf(1, 9, 12))
        assertTrue(cron.getDayOfWeekList() == listOf(5, 7))
        assertEquals("/bin/zip", cron.getCommandText())
    }

    @Test
    fun failWhenMinuteIsEmpty() {
        val exception = assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                Cron(
                    minute = listOf(),
                    hour = listOf(7, 8, 9),
                    dayOfMonth = listOf(15, 17, 31),
                    month = listOf(1, 9, 12),
                    dayOfWeek = listOf(5, 7),
                    command = "/bin/zip"
                )
            }
        )
    }

    @Test
    fun failWhenHourIsEmpty() {
        val exception = assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                Cron(
                    minute = listOf(1, 3),
                    hour = listOf(),
                    dayOfMonth = listOf(15, 17, 31),
                    month = listOf(1, 9, 12),
                    dayOfWeek = listOf(5, 7),
                    command = "/bin/zip"
                )
            }
        )
    }

    @Test
    fun failWhenDayOfMonthIsEmpty() {
        val exception = assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                Cron(
                    minute = listOf(1, 3),
                    hour = listOf(7, 8, 9),
                    dayOfMonth = listOf(),
                    month = listOf(1, 9, 12),
                    dayOfWeek = listOf(5, 7),
                    command = "/bin/zip"
                )
            }
        )
    }

    @Test
    fun failWhenMonthIsEmpty() {
        val exception = assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                Cron(
                    minute = listOf(1, 3),
                    hour = listOf(7, 8, 9),
                    dayOfMonth = listOf(15, 17, 31),
                    month = listOf(),
                    dayOfWeek = listOf(5, 7),
                    command = "/bin/zip"
                )
            }
        )
    }

    @Test
    fun failWhenDayOfDayOfWeekIsEmpty() {
        val exception = assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                Cron(
                    minute = listOf(1, 3),
                    hour = listOf(7, 8, 9),
                    dayOfMonth = listOf(15, 17, 31),
                    month = listOf(1, 9, 12),
                    dayOfWeek = listOf(),
                    command = "/bin/zip"
                )
            }
        )
    }

    @Test
    fun failWhenCommandIsEmpty() {
        val exception = assertFailsWith(
            exceptionClass = IllegalArgumentException::class,
            block = {
                Cron(
                    minute = listOf(1, 3),
                    hour = listOf(7, 8, 9),
                    dayOfMonth = listOf(15, 17, 31),
                    month = listOf(1, 9, 12),
                    dayOfWeek = listOf(5, 7),
                    command = ""
                )
            }
        )
    }
}