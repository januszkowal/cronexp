package org.blacksmith.cronexp

import org.blacksmith.cronexp.factory.CronFactory

fun main(args: Array<String>) {
    if (args.size != 1) {
        throw IllegalArgumentException("No arguments provided")
    }
    val cronArgs = args[0].split(" ");
    if (cronArgs.size != 6) {
        throw IllegalArgumentException("Invalid number of argument parts. Expected 6 provided ${cronArgs.size}")
    }
    val cron = CronFactory().create(cronArgs[0], cronArgs[1], cronArgs[2], cronArgs[3], cronArgs[4], cronArgs[5])
    val info = cron.info()
    info.forEach { println(it) }
}