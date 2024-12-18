package com.github.jbassett.y2024.d16

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample() {
        assertEquals(45, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/16/sample.txt")!!))
    }

    @Test
    fun solve_sample2() {
        assertEquals(64, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/16/sample2.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(520, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/16/input.txt")!!))
    }

}
