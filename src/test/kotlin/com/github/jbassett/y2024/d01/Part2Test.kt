package com.github.jbassett.y2024.d01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Part2Test {
    val partTwo = Part2()

    @Test
    fun solve_sample() {
        assertEquals(31, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/01/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(24349736, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/01/input.txt")!!))
    }

}
