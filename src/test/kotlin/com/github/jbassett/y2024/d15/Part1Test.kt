package com.github.jbassett.y2024.d15

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(10092, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/15/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1441031, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/15/input.txt")!!))
    }

}
