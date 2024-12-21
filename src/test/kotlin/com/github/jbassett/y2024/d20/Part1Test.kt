package com.github.jbassett.y2024.d20

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample()  {
        assertEquals(44, solver.solve(true, javaClass.getClassLoader().getResourceAsStream("2024/20/sample.txt")!!))
    }

    @Test
    fun solve_sample2()  {
        assertEquals(4, solver.solve(true, javaClass.getClassLoader().getResourceAsStream("2024/20/sample2.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1360, solver.solve(false, javaClass.getClassLoader().getResourceAsStream("2024/20/input.txt")!!))
    }

}
