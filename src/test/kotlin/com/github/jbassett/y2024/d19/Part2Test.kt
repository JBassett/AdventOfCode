package com.github.jbassett.y2024.d19

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample() {
        assertEquals(16, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/19/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(761826581538190, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/19/input.txt")!!))
    }

}
