package com.github.jbassett.y2024.d18

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(22, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/18/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(264, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/18/input.txt")!!))
    }

}
