package org.blacksmith.cronexp.factory

import org.blacksmith.cronexp.Cron

class CronFactory {
    private val minutePartFactory = CronPartFactory("minute", IntRange(0, 59))
    private val hourPartFactory = CronPartFactory("hour", IntRange(0, 23))
    private val dayOfMonthPartFactory = CronPartFactory("day of month", IntRange(1, 31))
    private val monthPartFactory = CronPartFactory(
        "month",
        IntRange(1, 12),
        mapOf(
            "jan" to 1,
            "feb" to 2,
            "mar" to 3,
            "apr" to 4,
            "may" to 5,
            "jun" to 6,
            "jul" to 7,
            "aug" to 8,
            "sep" to 9,
            "oct" to 10,
            "nov" to 11,
            "dec" to 12
        )
    )
    private val dayOfWeekPartFactory = CronPartFactory(
        "day of week",
        IntRange(1, 7),
        mapOf(
            "mon" to 1,
            "tue" to 2,
            "wed" to 3,
            "thu" to 4,
            "fri" to 5,
            "sat" to 6,
            "sun" to 7
        )
    )

    fun create(
        minute: String,
        hour: String,
        dayOfMonth: String,
        month: String,
        dayOfWeek: String,
        command: String
    ): Cron {
        require(minute.isNotEmpty())
        require(hour.isNotEmpty())
        require(dayOfMonth.isNotEmpty())
        require(month.isNotEmpty())
        require(dayOfWeek.isNotEmpty())
        require(command.isNotEmpty())
        return Cron(
            minute = minutePartFactory.createList(minute),
            hour = hourPartFactory.createList(hour),
            dayOfMonth = dayOfMonthPartFactory.createList(dayOfMonth),
            month = monthPartFactory.createList(month),
            dayOfWeek = dayOfWeekPartFactory.createList(dayOfWeek),
            command = command
        )
    }
}