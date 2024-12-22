package com.github.jbassett.y2024.d22

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.github.jbassett.y2024.d22.SolverY24D22.nextRandom

class SolverY24D22Test {
    @Test
    fun nextRandom() {
        var random = 123L
        random = random.nextRandom().also { assertEquals(15887950, it) }
        random = random.nextRandom().also { assertEquals(16495136, it) }
        random = random.nextRandom().also { assertEquals(527345, it) }
        random = random.nextRandom().also { assertEquals(704524, it) }
        random = random.nextRandom().also { assertEquals(1553684, it) }
        random = random.nextRandom().also { assertEquals(12683156, it) }
        random = random.nextRandom().also { assertEquals(11100544, it) }
        random = random.nextRandom().also { assertEquals(12249484, it) }
        random = random.nextRandom().also { assertEquals(7753432, it) }
        random = random.nextRandom().also { assertEquals(5908254, it) }
    }

    @Test
    fun part1_sample() {
        val input = """
            1
            10
            100
            2024
        """.trimIndent().split("\n")
        val answer = SolverY24D22.part1(input)

        assertEquals(37327623, answer)
    }

    @Test
    fun part1(){
        val input = javaClass.getClassLoader().getResourceAsStream("2024/22/input.txt")!!.bufferedReader().readLines()
        val answer = SolverY24D22.part1(input)
        assertEquals(14392541715, answer)
    }

    @Test
    fun part2_sample(){
        val input = """
            1
            2
            3
            2024
        """.trimIndent().split("\n")
        val answer = SolverY24D22.part2(input)
        assertEquals(23, answer)
    }

    @Test
    fun part2(){
        val input = javaClass.getClassLoader().getResourceAsStream("2024/22/input.txt")!!.bufferedReader().readLines()
        val answer = SolverY24D22.part2(input)
        assertEquals(1628, answer)
    }
}
