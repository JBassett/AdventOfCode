package com.github.jbassett.y2024.d12

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_sample() {
        assertEquals(80, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/12/sample.txt")!!))
    }

    @Test
    fun solve_sample2() {
        assertEquals(436, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/12/sample2.txt")!!))
    }

    @Test
    fun solve_sample3() {
        assertEquals(1206, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/12/sample3.txt")!!))
    }

    @Test
    fun solve_sample4() {
        assertEquals(236, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/12/sample4.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(893790, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/12/input.txt")!!))
    }

}
