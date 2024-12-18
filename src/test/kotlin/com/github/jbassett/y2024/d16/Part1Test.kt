package com.github.jbassett.y2024.d16

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals(7036, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/16/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(122492, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/16/input.txt")!!))
    }

}
