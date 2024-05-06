package org.blacksmith.cronexp.factory

import kotlin.test.Test
import kotlin.test.assertEquals

class CronFactoryTest {
    @Test
    fun shouldCreateDefaultCron() {
        val cronFactory = CronFactory()
        val cron = cronFactory.create("*", "*", "*", "*", "*", "/run")
        /*
        * I don't test info here because default list is long
        * */
        assertEquals(cron.getMinuteList(), IntRange(0, 59).toList())
        assertEquals(cron.getHourList(), IntRange(0, 23).toList())
        assertEquals(cron.getDayOfMonthList(), IntRange(1, 31).toList())
        assertEquals(cron.getMonthList(), IntRange(1, 12).toList())
        assertEquals(cron.getDayOfWeekList(), IntRange(1, 7).toList())
        assertEquals("/run", cron.getCommandText())
    }

    @Test
    fun shouldCreateCronInfo() {
        val cronFactory = CronFactory()
        val cron = cronFactory.create("*/15", "0", "1,15", "*", "1-5", "/run")
        val info = cron.info()
        assertEquals(
            listOf(
                "minute        0 15 30 45",
                "hour          0",
                "day of month  1 15",
                "month         1 2 3 4 5 6 7 8 9 10 11 12",
                "day of week   1 2 3 4 5",
                "command       /run"
            ),
            info
        )
        assertEquals(cron.getMinuteList(), listOf(0, 15, 30, 45))
        assertEquals(cron.getHourList(), listOf(0))
        assertEquals(cron.getDayOfMonthList(), listOf(1, 15))
        assertEquals(cron.getMonthList(), IntRange(1, 12).toList())
        assertEquals(cron.getDayOfWeekList(), IntRange(1, 5).toList())
        assertEquals("/run", cron.getCommandText())
    }

    @Test
    fun shouldCreateCron1() {
        val cronFactory = CronFactory()
        val cron =
            cronFactory.create("1-3,9-11", "0-4,11,19", "1-3,7,8,15-17,23,29", "1,2,3,9-11", "mon-wed,sat-sun", "/run")
        assertEquals(cron.getMinuteList(), listOf(1, 2, 3, 9, 10, 11))
        assertEquals(cron.getHourList(), listOf(0, 1, 2, 3, 4, 11, 19))
        assertEquals(cron.getDayOfMonthList(), listOf(1, 2, 3, 7, 8, 15, 16, 17, 23, 29))
        assertEquals(cron.getMonthList(), listOf(1, 2, 3, 9, 10, 11))
        assertEquals(cron.getDayOfWeekList(), listOf(1, 2, 3, 6, 7))
        assertEquals("/run", cron.getCommandText())
    }
}