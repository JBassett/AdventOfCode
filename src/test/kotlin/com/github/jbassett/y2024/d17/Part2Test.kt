package com.github.jbassett.y2024.d17

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample2() {
        assertEquals(117440, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/17/sample2.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(190384609508367, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/17/input.txt")!!))
    }

}
