package com.github.jbassett.y2024.d07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Part2Test {
    val partTwo = Part2()

    @Test
    fun solve_sample() {
        assertEquals(11387, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/07/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(275791737999003, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/07/input.txt")!!))
    }

}
