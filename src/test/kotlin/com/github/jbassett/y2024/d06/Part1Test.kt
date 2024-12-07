package com.github.jbassett.y2024.d06

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val partOne = Part1()

    @Test
    fun solve_sample() {
        assertEquals(41, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/06/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(4559, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/06/input.txt")!!))
    }

}
