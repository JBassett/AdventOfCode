package com.github.jbassett.y2024.d13

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(280, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/13/sample.txt")!!))
    }

    @Test
    fun solve_sample2() {
        assertEquals(480, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/13/sample2.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(36250, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/13/input.txt")!!))
    }

}
