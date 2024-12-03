package com.github.jbassett.y2024.d02

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val partOne = Part1()

    @Test
    fun solve_sample() {
        assertEquals(2, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/02/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(371, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/02/input.txt")!!))
    }

}
