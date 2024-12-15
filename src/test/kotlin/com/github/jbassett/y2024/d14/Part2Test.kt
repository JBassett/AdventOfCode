package com.github.jbassett.y2024.d14

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_input() {
        assertEquals(8159, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/14/input.txt")!!))
    }

}
