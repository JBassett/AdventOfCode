package com.github.jbassett.y2024.d10

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(1, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/10/sample.txt")!!))
    }

    @Test
    fun solve_sample2() {
        assertEquals(36, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/10/sample2.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(825, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/10/input.txt")!!))
    }

}
