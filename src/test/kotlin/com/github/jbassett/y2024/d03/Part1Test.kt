package com.github.jbassett.y2024.d03

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val partOne = Part1()

    @Test
    fun solve_sample() {
        assertEquals(161, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/03/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(173419328, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/03/input.txt")!!))
    }

}
