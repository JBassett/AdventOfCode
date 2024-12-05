package com.github.jbassett.y2024.d05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Part2Test {
    val partTwo = Part2()

    @Test
    fun solve_sample() {
        assertEquals(123, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/05/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(5285, partTwo.solve(javaClass.getClassLoader().getResourceAsStream("2024/05/input.txt")!!))
    }

}
