package com.github.jbassett.y2024.d07

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val partOne = Part1()

    @Test
    fun solve_sample() {
        assertEquals(3749, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/07/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1399219271639, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/07/input.txt")!!))
    }

}
