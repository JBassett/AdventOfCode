package com.github.jbassett.y2024.d12

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(140, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/12/sample.txt")!!))
    }

    @Test
    fun solve_sample2() {
        assertEquals(772, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/12/sample2.txt")!!))
    }

    @Test
    fun solve_sample3() {
        assertEquals(1930, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/12/sample3.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1465112, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/12/input.txt")!!))
    }

}
