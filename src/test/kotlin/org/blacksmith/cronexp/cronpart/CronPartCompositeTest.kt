package org.blacksmith.cronexp.cronpart

import kotlin.test.Test
import kotlin.test.assertEquals

class CronPartCompositeTest {
    @Test
    fun shouldReturnEmptyListWhenEmptyComposite() {
        var part = CronPartComposite(listOf())
        var expectedList: List<Int> = listOf()
        assertEquals(part.toList(), expectedList)
    }

    @Test
    fun shouldReturnMergedListWhenNonEmptyComposite() {
        var part1 = CronPartList(listOf(7, 1, 13))
        var part2 = CronPartRange(IntRange(15, 18))
        var compositePart = CronPartComposite(listOf(part1, part2))
        assertEquals(compositePart.toList(), listOf(1, 7, 13, 15, 16, 17, 18))
    }
}