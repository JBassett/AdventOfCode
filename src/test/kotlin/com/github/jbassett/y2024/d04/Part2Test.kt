package com.github.jbassett.y2024.d04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Part2Test {
    val partTwo = Part2()

    @Test
    fun solve_sample() {
        assertEquals(9, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/04/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1985, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/04/input.txt")!!))
    }

}
