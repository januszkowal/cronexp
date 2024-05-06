package org.blacksmith.cronexp.cronpart

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CronPartRangeTest {
    @Test
    fun shouldReturnValuesInRange() {
        var part = CronPartRange(IntRange(1, 5))
        assertTrue(part.toList() == listOf(1, 2, 3, 4, 5))
    }
}