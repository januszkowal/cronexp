package org.blacksmith.cronexp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertFailsWith

class MainKtTest {
    /*
     * Purpose of this test method is to validate System output produced by commonMain
     * */
//    @Test
    fun shouldWriteCronValuesToStandardOutput() {
        val consoleStream = System.out
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
        System.setOut(printStream)
        main(arrayOf("*/15 0 1,15 * 1-5 /usr/bin/find"))
        val outputText = outputStream.toString()
        System.setOut(consoleStream);
        assertEquals(
            """
                minute        0 15 30 45
                hour          0
                day of month  1 15
                month         1 2 3 4 5 6 7 8 9 10 11 12
                day of week   1 2 3 4 5
                command       /usr/bin/find
            """.trimIndent(), outputText.trimEnd()
        )
    }

//    @Test
    fun shouldThrowErrorWhenArgumentsInvalid() {
        val exception = assertFailsWith(exceptionClass = IllegalArgumentException::class, block = {
            main(arrayOf("*/15 0 1,15 * 1-5"))
        })
        assertTrue(exception.message?.contains("Invalid number of arguments. Expected 6 provided 5") ?: false)
    }
}