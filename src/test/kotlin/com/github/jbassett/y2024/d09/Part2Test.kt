package com.github.jbassett.y2024.d09

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample() {
        assertEquals(2858, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/09/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(6326952672104, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/09/input.txt")!!))
    }

}
