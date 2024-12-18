package com.github.jbassett.y2024.d17

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val solver = Part1()

    @Test
    fun solve_sample() {
        assertEquals("4,6,3,5,6,3,5,2,1,0", solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/17/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals("2,3,4,7,5,7,3,0,7", solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/17/input.txt")!!))
    }

}
