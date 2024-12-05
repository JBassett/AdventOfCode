package com.github.jbassett.y2024.d05

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val partOne = Part1()

    @Test
    fun solve_sample() {
        assertEquals(143, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/05/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(4135, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/05/input.txt")!!))
    }

}
