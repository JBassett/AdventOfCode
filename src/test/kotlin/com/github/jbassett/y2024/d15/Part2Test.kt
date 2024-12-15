package com.github.jbassett.y2024.d15

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample() {
        assertEquals(9021, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/15/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1425169, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/15/input.txt")!!))
    }

}
