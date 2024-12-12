package com.github.jbassett.y2024.d10

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample() {
        assertEquals(81, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/10/sample2.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1805, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/10/input.txt")!!))
    }

}
