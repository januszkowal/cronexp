package org.blacksmith.cronexp.cronpart

class CronPartList(private val items: List<Int>) : CronPart {
    override fun toList(): List<Int> {
        return items.distinct().sorted()
    }
}