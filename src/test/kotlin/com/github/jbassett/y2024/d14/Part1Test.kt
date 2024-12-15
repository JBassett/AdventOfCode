package com.github.jbassett.y2024.d14

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(12, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/14/sample.txt")!!, true))
    }

    @Test
    fun solve_input() {
        assertEquals(231852216, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/14/input.txt")!!))
    }

}
