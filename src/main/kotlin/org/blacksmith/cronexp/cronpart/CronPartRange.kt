package org.blacksmith.cronexp.cronpart

class CronPartRange(private val range: IntRange) : CronPart {
    override fun toList(): List<Int> {
        return range.toList()
    }
}