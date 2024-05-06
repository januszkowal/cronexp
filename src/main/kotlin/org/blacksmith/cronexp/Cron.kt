package org.blacksmith.cronexp

class Cron(
    private val minute: List<Int>,
    private val hour: List<Int>,
    private val dayOfMonth: List<Int>,
    private val month: List<Int>,
    private val dayOfWeek: List<Int>,
    private val command: String
) {
    companion object {
        const val LABELS_CHARS = 14
    }

    init {
        require(minute.isNotEmpty())
        require(hour.isNotEmpty())
        require(dayOfMonth.isNotEmpty())
        require(month.isNotEmpty())
        require(dayOfWeek.isNotEmpty())
        require(command.isNotEmpty())
    }

    fun info(): List<String> {
        return listOf(
            "minute".padEnd(LABELS_CHARS).plus(minute.asString()),
            "hour".padEnd(LABELS_CHARS).plus(getHourList().asString()),
            "day of month".padEnd(LABELS_CHARS).plus(getDayOfMonthList().asString()),
            "month".padEnd(LABELS_CHARS).plus(getMonthList().asString()),
            "day of week".padEnd(LABELS_CHARS).plus(getDayOfWeekList().asString()),
            "command".padEnd(LABELS_CHARS).plus(getCommandText())
        )
    }

    fun getMinuteList(): List<Int> {
        return minute
    }

    fun getHourList(): List<Int> {
        return hour
    }

    fun getDayOfMonthList(): List<Int> {
        return dayOfMonth
    }

    fun getMonthList(): List<Int> {
        return month
    }

    fun getDayOfWeekList(): List<Int> {
        return dayOfWeek
    }

    fun getCommandText(): String {
        return command
    }
}

fun List<Int>.asString(): String {
    return this.joinToString(separator = " ") { it.toString() }
}