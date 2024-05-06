package org.blacksmith.cronexp.cronpart

class CronPartComposite(private val parts: List<CronPart>) : CronPart {
    override fun toList(): List<Int> {
        return parts.flatMap { part -> part.toList() }.distinct().sorted()
    }
}