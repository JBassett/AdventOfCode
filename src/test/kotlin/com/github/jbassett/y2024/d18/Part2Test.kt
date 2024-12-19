package com.github.jbassett.y2024.d18

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample2() {
        assertEquals("6,1", solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/18/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals("41,26", solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/18/input.txt")!!))
    }

}
