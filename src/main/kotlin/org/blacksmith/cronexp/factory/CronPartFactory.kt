package org.blacksmith.cronexp.factory

import org.blacksmith.cronexp.cronpart.CronPart
import org.blacksmith.cronexp.cronpart.CronPartComposite
import org.blacksmith.cronexp.cronpart.CronPartList
import org.blacksmith.cronexp.cronpart.CronPartRange
import org.blacksmith.cronexp.exception.InvalidExpressionException
import org.blacksmith.cronexp.exception.ParseException

class CronPartFactory(
    private val partName: String,
    private val range: IntRange,
    private val aliases: Map<String, Int> = mapOf()
) {
    fun createList(expression: String): List<Int> {
        return create(expression).toList()
    }

    private fun create(expression: String): CronPart {
        val listOfValues = ArrayList<Int>()
        val cronParts = ArrayList<CronPart>()
        try {
            for (subexpression in expression.split(",")) {
                if (subexpression == "*") {
                    cronParts.add(createAllValuesPart())
                } else if (subexpression.contains("-")) {
                    cronParts.add(createRangePart(subexpression))
                } else if (subexpression.contains("/")) {
                    cronParts.add(createIntervalPart(subexpression))
                } else {
                    listOfValues.add(extractSingleValue(subexpression))
                }
            }
        } catch (exception: Exception) {
            throwInvalidExpressionError(expression)
        }

        if (listOfValues.size > 0) {
            cronParts.add(CronPartList(listOfValues))
        }
        if (cronParts.size == 0) {
            throwInvalidExpressionError(expression)
        }
        return CronPartComposite(cronParts)
    }

    private fun createAllValuesPart(): CronPart {
        return CronPartRange(range)
    }

    private fun createRangePart(subexpression: String): CronPart {
        var rangeArgs = subexpression.split("-")
        try {
            val minValue = mapValue(rangeArgs[0])
            val maxValue = mapValue(rangeArgs[1])
            if (minValue < range.first || maxValue > range.last) {
                throw ParseException("Invalid expression - \"$subexpression\"")
            }
            return CronPartRange(IntRange(minValue, maxValue))
        } catch (e: NumberFormatException) {
            throw ParseException("Invalid expression - \"$subexpression\"")
        }
    }

    private fun createIntervalPart(subexpression: String): CronPart {
        var intervalArgs = subexpression.split("/")
        var startExpr = intervalArgs[0]
        var start: Int = if (startExpr.equals("*")) {
            range.first
        } else {
            mapValue(startExpr)
        }
        var step = mapValue(intervalArgs[1])
        val values = ArrayList<Int>()
        IntProgression.fromClosedRange(start, range.last, step).forEach {
            values.add(it)
        }
        return CronPartList(values)
    }

    private fun extractSingleValue(subexpression: String): Int {
        if (subexpression == "") {
            throw ParseException("Invalid expression - empty")
        }
        try {
            val singleValue = mapValue(subexpression)
            if (!range.contains(singleValue)) {
                throw ParseException("Invalid expression - \"$subexpression\"")
            }
            return singleValue
        } catch (e: NumberFormatException) {
            throw ParseException("Invalid value - \"$subexpression\"")
        }
    }

    private fun throwInvalidExpressionError(expression: String) {
        throw InvalidExpressionException("Invalid expression \"$expression\" of $partName")
    }

    private fun mapValue(sourceValue: String): Int {
        return aliases.getOrElse(sourceValue.lowercase()) { Integer.parseInt(sourceValue) }
    }
}