package org.blacksmith.cronexp.factory

import org.blacksmith.cronexp.exception.InvalidExpressionException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CronPartFactoryTest {
    @Test
    fun shouldCreateCronRangePart() {
        val minutesPartFactory = CronPartFactory("qaz", IntRange(0, 59))
        var result = minutesPartFactory.createList("12-17")
        assertEquals(result, listOf(12, 13, 14, 15, 16, 17))
    }

    @Test
    fun shouldCreateCronListPart() {
        val minutesPartFactory = CronPartFactory("qaz", IntRange(0, 59))
        var result = minutesPartFactory.createList("5,7,8")
        assertEquals(result, listOf(5, 7, 8))
    }

    @Test
    fun shouldCreateCronCompositePart() {
        val minutesPartFactory = CronPartFactory("qaz", IntRange(0, 59))
        var result = minutesPartFactory.createList("5-7,8,9,17-19")
        assertEquals(result, listOf(5, 6, 7, 8,  9, 17, 18, 19))
    }

    @Test
    fun shouldMapWeekDayByNameAbbreviation() {
        val dayOfWeekPartFactory = CronPartFactory(
            "day of week",
            IntRange(1, 7),
            mapOf(
                "mon" to 1, "tue" to 2, "wed" to 3, "thu" to 4, "fri" to 5, "sat" to 6, "sun" to 7
            )
        )
        var result = dayOfWeekPartFactory.createList("WED-FRI")
        assertEquals(result, listOf(3, 4, 5))
    }

    @Test
    fun shouldMapWeekDayMixed() {
        val dayOfWeekPartFactory = CronPartFactory(
            "day of week",
            IntRange(1, 7),
            mapOf(
                "mon" to 1, "tue" to 2, "wed" to 3, "thu" to 4, "fri" to 5, "sat" to 6, "sun" to 7
            )
        )
        var result = dayOfWeekPartFactory.createList("2-4,SAT-SUN")
        assertEquals(result, listOf(2, 3, 4, 6, 7))
    }

    @Test()
    fun shouldFailWhenInvalidList() {
        val minutesPartFactory = CronPartFactory("qaz", IntRange(0, 59))
        val exception = assertFailsWith(
            exceptionClass = InvalidExpressionException::class,
            block = {
                minutesPartFactory.createList("7,")
            }
        )
        assertEquals("Invalid expression \"7,\" of qaz", exception.message)
    }

    @Test()
    fun shouldFailWhenValueOutOfRange1() {
        val minutesPartFactory = CronPartFactory("qaz", IntRange(0, 59))
        val exception = assertFailsWith(
            exceptionClass = InvalidExpressionException::class,
            block = {
                minutesPartFactory.createList("59-60")
            }
        )
        assertEquals("Invalid expression \"59-60\" of qaz", exception.message)
    }

    @Test()
    fun shouldFailWhenValueOutOfRange2() {
        val minutesPartFactory = CronPartFactory("qaz", IntRange(0, 59))
        val exception = assertFailsWith(
            exceptionClass = InvalidExpressionException::class,
            block = {
                minutesPartFactory.createList("7,60")
            }
        )
        assertEquals("Invalid expression \"7,60\" of qaz", exception.message)
    }

    @Test()
    fun shouldFailWhenInvalidValue() {
        val minutesPartFactory = CronPartFactory("qaz", IntRange(0, 59))
        val exception = assertFailsWith(
            exceptionClass = InvalidExpressionException::class,
            block = {
                minutesPartFactory.createList("7,X")
            }
        )
        assertEquals("Invalid expression \"7,X\" of qaz", exception.message)
    }

    @Test()
    fun shouldFailWhenInvalidRange() {
        val minutesPartFactory = CronPartFactory("qaz", IntRange(0, 59))
        val exception = assertFailsWith(
            exceptionClass = InvalidExpressionException::class,
            block = {
                minutesPartFactory.createList("7-")
            }
        )
        assertEquals("Invalid expression \"7-\" of qaz", exception.message)
    }
}