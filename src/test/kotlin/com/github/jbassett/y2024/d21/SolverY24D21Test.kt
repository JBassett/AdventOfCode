package com.github.jbassett.y2024.d21

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolverY24D21Test {
    @Test
    fun boardOne() {
        assertEquals(
            listOf(SolverY24D21.Move.DOWN, SolverY24D21.Move.CLICK),
            SolverY24D21.keypadOneClick('9', '6')
        )
        assertEquals(
            listOf(SolverY24D21.Move.UP, SolverY24D21.Move.CLICK),
            SolverY24D21.keypadOneClick('6', '9')
        )
        assertEquals(
            listOf(SolverY24D21.Move.LEFT, SolverY24D21.Move.DOWN, SolverY24D21.Move.CLICK),
            SolverY24D21.keypadOneClick('9', '5')
        )
        assertEquals(
            listOf(SolverY24D21.Move.UP, SolverY24D21.Move.RIGHT, SolverY24D21.Move.CLICK),
            SolverY24D21.keypadOneClick('5', '9')
        )
    }

    @Test
    fun part1() {
        val input = """
            029A
            980A
            179A
            456A
            379A
        """.trimIndent().lines()
        val answer = SolverY24D21.part1(input)

        assertEquals(126384, answer)
    }
}
