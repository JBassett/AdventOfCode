package com.github.jbassett.y2024.d01

import com.github.jbassett.y2024.d01.Part1
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Part1Test {
    val partOne = Part1()

    @Test
    fun solve_sample() {
        assertEquals(11, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/01/sample.txt")!!))
    }

    @Test
    fun solve_input() {
        assertEquals(1189304, partOne.solve(javaClass.getClassLoader().getResourceAsStream("2024/01/input.txt")!!))
    }

}
