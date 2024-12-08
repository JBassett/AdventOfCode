package com.github.jbassett.y2024.d08

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(14, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/08/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(301, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/08/input.txt")!!))
    }

}
