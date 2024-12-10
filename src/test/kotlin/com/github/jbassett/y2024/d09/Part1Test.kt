package com.github.jbassett.y2024.d09

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(1928, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/09/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(6299243228569, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/09/input.txt")!!))
    }

}
