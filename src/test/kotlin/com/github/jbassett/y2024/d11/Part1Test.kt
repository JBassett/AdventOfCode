package com.github.jbassett.y2024.d11

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(55312, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/11/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(172484, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/11/input.txt")!!))
    }

}
