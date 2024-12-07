package com.github.jbassett.y2024.d06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Part2Test {
    val partTwo = Part2()

    @Test
    fun solve_sample() {
        assertEquals(6, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/06/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1604, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/06/input.txt")!!))
    }

}
