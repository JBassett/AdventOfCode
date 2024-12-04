package com.github.jbassett.y2024.d04

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val partOne = Part1()

    @Test
    fun solve_sample() {
        assertEquals(18, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/04/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(2551, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/04/input.txt")!!))
    }

}
