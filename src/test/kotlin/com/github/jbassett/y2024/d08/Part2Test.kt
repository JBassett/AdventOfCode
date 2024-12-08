package com.github.jbassett.y2024.d08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample() {
        assertEquals(34, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/08/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1019, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/08/input.txt")!!))
    }

}
