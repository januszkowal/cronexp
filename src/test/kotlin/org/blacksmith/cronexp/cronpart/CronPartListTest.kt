package org.blacksmith.cronexp.cronpart

import kotlin.test.Test
import kotlin.test.assertEquals


class CronPartListTest {
    @Test
    fun shouldReturnEmptyList() {
        var part = CronPartList(listOf())
        var expectedList: List<Int> = listOf()
        assertEquals(part.toList(), expectedList)
    }

    @Test
    fun shouldReturnNonEmptySortedList() {
        var part = CronPartList(listOf(7, 1, 13))
        assertEquals(part.toList(), listOf(1, 7, 13))
    }
}