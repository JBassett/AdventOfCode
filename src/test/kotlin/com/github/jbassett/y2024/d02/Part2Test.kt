package com.github.jbassett.y2024.d02

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val partTwo = Part2()

    @Test
    fun solve_sample() {
        assertEquals(4, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/02/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(426, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/02/input.txt")!!))
    }

}
