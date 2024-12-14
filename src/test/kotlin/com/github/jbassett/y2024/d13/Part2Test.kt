package com.github.jbassett.y2024.d13

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample() {
        assertEquals(0, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/13/sample.txt")!!))
    }

    @Test
    fun solve_sample2() {
        assertNotEquals(0, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/13/sample2.txt")!!))
    }

    @Test
    fun solve_sample3() {
        assertEquals(0, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/13/sample3.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(83232379451012, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/13/input.txt")!!))
    }

}
