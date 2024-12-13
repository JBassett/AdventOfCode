package com.github.jbassett.y2024.d11

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part2Test {
    val solver = Part2()

    @Test
    fun solve_input() = runBlocking {
        assertEquals(205913561055242, solver.solve(javaClass.getClassLoader().getResourceAsStream("2024/11/input.txt")!!))
    }

}
