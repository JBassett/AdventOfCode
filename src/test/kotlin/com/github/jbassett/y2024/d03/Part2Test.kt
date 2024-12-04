package com.github.jbassett.y2024.d03

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val partTwo = Part2()

    @Test
    fun solve_sample() {
        assertEquals(48, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/03/sample2.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(90669332, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/03/input.txt")!!))
    }
}
