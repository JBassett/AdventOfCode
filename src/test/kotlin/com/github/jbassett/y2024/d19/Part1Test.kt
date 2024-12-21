package com.github.jbassett.y2024.d19

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample()  {
        assertEquals(6, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/19/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(3691, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/19/input.txt")!!))
    }

}
